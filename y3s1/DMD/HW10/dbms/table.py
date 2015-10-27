import os

from dbms.utils import *
from dbms.metadata import Metadata
from dbms.query import Filter, _Concat, _Where
from dbms.vendor.btree import SBplusTree, caching_SBPT


class Table(object):
    __slots__ = ['meta', 'table_name', '__file_path', 'btree']

    def __init__(self, table_name):
        self.table_name = table_name

        current_dir = os.path.dirname(os.path.abspath(__file__))
        self.__file_path = os.path.join(current_dir, 'tables', '%s.txt' % table_name)

        if not os.path.isfile(self.__file_path):
            raise FileNotFoundError('No such table')

        with open(self.__file_path, 'rb') as table:
            # read metadata size
            table.seek(-Metadata.header_length, os.SEEK_END)
            meta_length = int(table.read(Metadata.header_length))

            # read all metadata
            table.seek(-(meta_length + Metadata.header_length), os.SEEK_END)
            meta = table.read().split()

            table_schema = to_string_list(meta[0].split(Delimiters.attr))

            rows_count = int(meta[1])

            free_space_meta = meta[2].split(Delimiters.offset)
            free_space_offset = int(free_space_meta[0])
            try:
                free_space_limit = int(free_space_meta[1])
            except ValueError:
                free_space_limit = FREE_SPACE_AT_END

            indicies = []
            for index in meta[3:-1]:
                index_obj = index.split(Delimiters.offset)
                indicies.append(Metadata.Index(
                    offset=int(index_obj[0]),
                    limit=int(index_obj[1]),
                    attr_limits=to_int_list(index_obj[2].split(Delimiters.attr))
                ))

            self.meta = Metadata(
                table_schema=table_schema,
                rows_count=rows_count,
                free_space_offset=free_space_offset,
                free_space_limit=free_space_limit,
                indicies=indicies
            )
            self.meta.length = meta_length

    @staticmethod
    def create(name: str, attrs: tuple):
        current_dir = os.path.dirname(os.path.abspath(__file__))
        file_path = os.path.join(current_dir, 'tables', '%s.txt' % name)

        with open(file_path, 'wb') as table:
            meta = Metadata(
                table_schema=list(attrs),
                rows_count=0,
                free_space_offset=0,
                free_space_limit=FREE_SPACE_AT_END,
                indicies=[]
            )
            table.write(bytes(str(meta), DBMS_ENCODING))

    def __update_meta(self, data_end_offset):
        with open(self.__file_path, 'rb+') as table:
            table.seek(data_end_offset)
            table.truncate()
            table.write(bytes(str(self.meta), DBMS_ENCODING))

    def __update_index(self, by_attr, key, value):
        current_dir = os.path.dirname(os.path.abspath(__file__))
        index_file_path = os.path.join(current_dir, 'indexes', '%s_%s.txt' % (self.table_name, by_attr))
        with open(index_file_path, 'r+b') as index_file:
            btree = SBplusTree(index_file)
            btree.open()
            btree[key] = value


    def insert(self, attrs, values=None):
        if values is None:
            values = attrs
            attrs = self.meta.table_schema

        if len(attrs) != len(values):
            raise TypeError('Malformed insert query: attributes and values counts do not match')

        for attr in attrs:
            if attr not in self.meta.table_schema:
                raise TypeError("Malformed insert query: can't find attribute `%s`" % attr)

        new_tuple = tuple()
        for attr in self.meta.table_schema:
            index = attrs.index(attr) if attr in attrs else -1
            new_tuple = new_tuple + ((str(values[index]),) if index >= 0 and values[index] is not None else ('',))

        new_tuple_length = sum(len(attr) for attr in new_tuple)
        prev_free_space_offset = free_space_offset = self.meta.free_space_offset
        available_space = self.meta.free_space_limit

        with open(self.__file_path, 'rb+') as table:
            while new_tuple_length > available_space != FREE_SPACE_AT_END:
                table.seek(free_space_offset)
                free_space_meta = table.read(available_space).split(Delimiters.offset)
                prev_free_space_offset = free_space_offset
                free_space_offset = int(free_space_meta[0])
                try:
                    available_space = int(free_space_meta[1])
                except ValueError:
                    available_space = FREE_SPACE_AT_END

            table.seek(free_space_offset)
            next_pointer = table.read(available_space)
            table.seek(free_space_offset)

            entry = ''.join(new_tuple) if available_space == FREE_SPACE_AT_END else ''.join(new_tuple).ljust(available_space)
            table.write(bytes(entry, DBMS_ENCODING))

            new_index = Metadata.Index(
                offset=free_space_offset,
                limit=new_tuple_length if available_space == FREE_SPACE_AT_END else available_space,
                attr_limits=list(map(len, new_tuple))
            )

            self.__update_index('name', new_tuple[self.meta.table_schema.index('name')], new_index.as_dict())

            self.meta.rows_count += 1
            self.meta.indicies.append(new_index)

            if prev_free_space_offset != free_space_offset:
                # we moved away from the metadata pointer and have to keep pointers consistent
                pos = table.tell()
                # go to the previous pointer position
                table.seek(prev_free_space_offset)
                if available_space == FREE_SPACE_AT_END:
                    # we haven't found a gap big enough and appended to the end.
                    # update the previous pointer so that it still point to the end
                    table.write(bytes('{offset}{delimiter}{limit}'.format(
                        offset=pos,
                        limit=FREE_SPACE_AT_END,
                        delimiter=Delimiters.offset.decode(DBMS_ENCODING)
                    ), DBMS_ENCODING))
                else:
                    # we have found a gap big enough to store the new tuple
                    # update the previous pointer so that it point to the next gap
                    table.write(next_pointer)
                # revert cursor
                table.seek(pos)
            else:
                # the metadata pointer suits
                if available_space == FREE_SPACE_AT_END:
                    # there were no gaps, just appended to the end
                    # update the metadata pointer so that it still point to the end
                    self.meta.free_space_offset = table.tell()
                else:
                    # replace the metadata pointer with the pointer that was stored in the gap we just overwrote
                    free_space_meta = next_pointer.split(Delimiters.offset)
                    self.meta.free_space_offset = int(free_space_meta[0])
                    self.meta.free_space_limit = int(free_space_meta[1])

            # if we weren't appending to the end then data length didn't change.
            # We need to go to the end to update metadata.
            # otherwise the current position is already at the end
            if available_space != FREE_SPACE_AT_END:
                table.seek(-(self.meta.length + self.meta.header_length), os.SEEK_END)

            meta_start = table.tell()

        self.__update_meta(meta_start)

    def delete(self, row):
        if len(row) != len(self.meta.table_schema):
            raise TypeError('Invalid tuple schema')

        # convert all attributes to string, save the Nones
        row = tuple(map(lambda a: str(a) if a is not None else None, row))

        with open(self.__file_path, 'rb+') as table:
            for index in self.meta.indicies:
                table.seek(index.offset)

                attrs = tuple()
                attrs_for_comparison = tuple()

                for i, limit in enumerate(index.attr_limits):
                    attr = (table.read(limit).decode(DBMS_ENCODING),)
                    attrs = attrs + attr
                    attrs_for_comparison = attrs_for_comparison + ((None,) if row[i] is None else attr)

                if attrs_for_comparison == row:
                    table.seek(index.offset)
                    free_space_meta = '{offset}{delimiter}{limit}'.format(
                        offset=self.meta.free_space_offset,
                        limit=self.meta.free_space_limit,
                        delimiter=Delimiters.offset.decode(DBMS_ENCODING)
                    ).ljust(index.limit)

                    if len(bytes(free_space_meta, DBMS_ENCODING)) <= index.limit:
                        table.write(bytes(free_space_meta, DBMS_ENCODING))

                        self.meta.free_space_limit = index.limit
                        self.meta.free_space_offset = index.offset
                    else:
                        table.write(b' ' * index.limit)

                    self.meta.indicies.remove(index)
                    self.meta.rows_count -= 1

            table.seek(-(self.meta.length + self.meta.header_length), os.SEEK_END)
            meta_start = table.tell()

        self.__update_meta(meta_start)

    def update(self, row, new_row):
        self.delete(row)
        self.insert(new_row)

    def select(self, *attrs):
        return Filter(self, attrs)

    def execute(self, query):
        if isinstance(query, _Where):
            raise SyntaxError("Malformed query")

        if query.filter.table != self:
            raise SyntaxError("Wrong table")

        results = []

        with open(self.__file_path, 'rb') as table:
            for index in self.meta.indicies:
                table.seek(index.offset)

                attrs = {}
                result_tuple = tuple()

                for i, limit in enumerate(index.attr_limits):
                    attr = table.read(limit).decode(DBMS_ENCODING)
                    attrs[self.meta.table_schema[i]] = attr

                for attr in query.filter.attrs:
                    if attr not in attrs.keys():
                        raise TypeError("Malformed insert query: can't find attribute `%s`" % attr)
                    result_tuple = result_tuple + (attrs[attr],)

                test = True

                if len(query.filter.expressions) > 0:
                    test = query.filter.expressions[0](attrs)

                    for i in range(1, len(query.filter.expressions), 2):
                        if query.filter.expressions[i] == 'and':
                            test = test and query.filter.expressions[i+1](attrs)
                        elif query.filter.expressions[i] == 'or':
                            test = test or query.filter.expressions[i+1](attrs)

                if test:
                    results.append(result_tuple)

        return results

    def search(self, row):
        if len(row) != len(self.meta.table_schema):
            raise TypeError('Invalid tuple schema')

        # convert all attributes to string, save the Nones
        row = tuple(map(lambda a: str(a) if a is not None else None, row))
        results = []

        with open(self.__file_path, 'rb') as table:
            for index in self.meta.indicies:
                table.seek(index.offset)

                attrs = tuple()
                attrs_for_comparison = tuple()

                for i, limit in enumerate(index.attr_limits):
                    attr = (table.read(limit).decode(DBMS_ENCODING),)
                    attrs = attrs + attr
                    attrs_for_comparison = attrs_for_comparison + ((None,) if row[i] is None else attr)

                if attrs_for_comparison == row:
                    results.append(attrs)

        return results

    @staticmethod
    def index(table_name, by_attr):
        current_dir = os.path.dirname(os.path.abspath(__file__))

        table_file_path = os.path.join(current_dir, 'tables', '%s.txt' % table_name)
        index_file_path = os.path.join(current_dir, 'indexes', '%s_%s.txt' % (table_name, by_attr))

        if not os.path.isfile(table_file_path):
            raise FileNotFoundError('No such table')

        with open(index_file_path, 'w+b') as index_file:
            btree = SBplusTree(index_file, 0, 20, 42)
            btree.startup()

            with open(table_file_path, 'rb') as table:
                # read metadata size
                table.seek(-Metadata.header_length, os.SEEK_END)
                meta_length = int(table.read(Metadata.header_length))

                # read all metadata
                table.seek(-(meta_length + Metadata.header_length), os.SEEK_END)
                meta = table.read().split()

                table_schema = to_string_list(meta[0].split(Delimiters.attr))

                attr_index = table_schema.index(by_attr)

                indicies = []
                for index in meta[3:-1]:
                    index_obj = index.split(Delimiters.offset)
                    indicies.append(Metadata.Index(
                        offset=int(index_obj[0]),
                        limit=int(index_obj[1]),
                        attr_limits=to_int_list(index_obj[2].split(Delimiters.attr))
                    ))

                for index in indicies:
                    table.seek(index.offset)

                    for i, limit in enumerate(index.attr_limits):
                        if i < attr_index:
                            table.seek(limit, os.SEEK_CUR)
                        else:
                            attr = table.read(limit)
                            btree[attr.decode(DBMS_ENCODING)] = index.as_dict()
                            break
