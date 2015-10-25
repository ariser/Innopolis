import os

from dbms.utils import *
from dbms.metadata import Metadata
from dbms.vendor.btree import BPlusTree


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

            self.read_index()

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

    def insert(self, attrs):
        if len(attrs) != len(self.meta.table_schema):
            raise TypeError('Invalid tuple schema')

        attrs = list(map(str, attrs))
        attrs_length = sum(len(attr) for attr in attrs)
        prev_free_space_offset = free_space_offset = self.meta.free_space_offset
        available_space = self.meta.free_space_limit

        with open(self.__file_path, 'rb+') as table:
            while attrs_length > available_space != FREE_SPACE_AT_END:
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

            entry = ''.join(attrs) if available_space == FREE_SPACE_AT_END else ''.join(attrs).ljust(available_space)
            table.write(bytes(entry, DBMS_ENCODING))

            self.meta.rows_count += 1
            self.meta.indicies.append(Metadata.Index(
                offset=free_space_offset,
                limit=attrs_length if available_space == FREE_SPACE_AT_END else available_space,
                attr_limits=list(map(len, attrs))
            ))

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

    def search(self, row):
        if len(row) != len(self.meta.table_schema):
            raise TypeError('Invalid tuple schema')

        
        indexes = self.btree.getlist()
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

    def read_index(self):
        btree = BPlusTree(2)

        current_dir = os.path.dirname(os.path.abspath(__file__))
        index_file = os.path.join(current_dir, 'indexes', '%s.txt' % self.table_name)

        if not os.path.isfile(index_file):
            raise FileNotFoundError("Table wasn't indexed. Run .index() first.")

        with open(index_file, 'rb') as index:
            for line in index.readlines():
                tmp = line.split()
                btree.insert(tmp[0], tmp[1])

        self.btree = btree

    @staticmethod
    def index(table_name, by_attr):
        current_dir = os.path.dirname(os.path.abspath(__file__))

        table_file = os.path.join(current_dir, 'tables', '%s.txt' % table_name)

        if not os.path.isfile(table_file):
            raise FileNotFoundError('No such table')

        btree = BPlusTree(2)

        with open(table_file, 'rb') as table:
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
                        btree.insert(attr, index)
                        break

        index_file = os.path.join(current_dir, 'indexes', '%s.txt' % table_name)
        with open(index_file, 'wb') as index:
            for q in btree:
                index.write(q)
                index.write(b' ')
                index.write(bytes(str(btree.get(q)), DBMS_ENCODING))
                index.write(b'\n')
