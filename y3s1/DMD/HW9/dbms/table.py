import os

from dbms import delimiters

from dbms.index import Index

current_dir = os.path.dirname(os.path.abspath(__file__))

FREE_SPACE_AT_END = -1
ENCODING = 'utf-8'


def convert_list(src, converter):
    return list(map(converter, src))


def to_string_list(src):
    return convert_list(src, lambda s: s.decode(ENCODING))


def to_int_list(src):
    return convert_list(src, int)


class Table:
    __meta_header_length = 8

    __file_path = None

    __meta_length = None

    __table_schema = None
    __rows_count = None
    __indicies = None
    __free_space_offset = None
    __free_space_limit = None

    def __init__(self, table_name):
        self.__file_path = os.path.join(current_dir, 'tables', '%s.txt' % table_name)

        if not os.path.isfile(self.__file_path):
            raise FileNotFoundError('No such table')

        with open(self.__file_path, 'rb') as table:
            table.seek(-self.__meta_header_length, os.SEEK_END)
            self.__meta_length = int(table.read(self.__meta_header_length))
            table.seek(-(self.__meta_length + self.__meta_header_length), os.SEEK_END)
            meta = table.read().split()
            self.__table_schema = to_string_list(meta[0].split(delimiters.attr_delimiter))
            self.__rows_count = int(meta[1])
            free_space_meta = meta[2].split(delimiters.offset_delimiter)
            self.__free_space_offset = int(free_space_meta[0])
            try:
                self.__free_space_limit = int(free_space_meta[1])
            except ValueError:
                self.__free_space_limit = FREE_SPACE_AT_END
            self.__indicies = []
            indicies = meta[3:-1]
            for index in indicies:
                index_obj = index.split(delimiters.offset_delimiter)
                self.__indicies.append(Index(
                    offset=int(index_obj[0]),
                    limit=int(index_obj[1]),
                    attr_limits=to_int_list(index_obj[2].split(delimiters.attr_delimiter))
                ))

    @staticmethod
    def create(name, attrs):
        file_path = os.path.join(current_dir, 'tables', '%s.txt' % name)

        with open(file_path, 'wb') as table:
            meta = '{schema} {rows_count} {free_offset}:{free_limit} {indicies}'.format(
                schema=','.join(attrs),
                rows_count=0,
                free_offset=0,
                free_limit=FREE_SPACE_AT_END,
                indicies=''
            )
            meta += '{length}'.format(length=len(meta)).rjust(Table.__meta_header_length)
            table.write(bytes(meta, ENCODING))

    def __update_meta(self, data_end_offset):
        with open(self.__file_path, 'rb+') as table:
            table.seek(data_end_offset)
            table.truncate()
            indicies = [str(index) for index in self.__indicies]
            meta = '{schema} {rows_count} {free_offset}:{free_limit} {indicies}'.format(
                schema=','.join(self.__table_schema),
                rows_count=self.__rows_count,
                free_offset=self.__free_space_offset,
                free_limit=self.__free_space_limit,
                indicies=' '.join(indicies)
            )
            self.__meta_length = len(meta)
            meta += '{length}'.format(length=self.__meta_length).rjust(self.__meta_header_length)
            table.write(bytes(meta, ENCODING))

    def insert(self, attrs):
        if len(attrs) != len(self.__table_schema):
            raise TypeError('Invalid tuple schema')

        attrs = list(map(str, attrs))
        attrs_length = sum(len(attr) for attr in attrs)
        prev_free_space_offset = free_space_offset = self.__free_space_offset
        prev_available_space = available_space = self.__free_space_limit

        with open(self.__file_path, 'rb+') as table:
            while attrs_length > available_space != FREE_SPACE_AT_END:
                table.seek(free_space_offset)
                free_space_meta = table.read(available_space).split(delimiters.offset_delimiter)
                prev_free_space_offset = free_space_offset
                prev_available_space = available_space
                free_space_offset = int(free_space_meta[0])
                try:
                    available_space = int(free_space_meta[1])
                except ValueError:
                    available_space = FREE_SPACE_AT_END
            table.seek(free_space_offset)
            next_ = table.read(available_space)
            table.seek(free_space_offset)
            entry = ''.join(attrs) if available_space == FREE_SPACE_AT_END else ''.join(attrs).ljust(available_space)
            table.write(bytes(entry, ENCODING))
            self.__rows_count += 1
            self.__indicies.append(Index(
                offset=free_space_offset,
                limit=attrs_length if available_space == FREE_SPACE_AT_END else available_space,
                attr_limits=list(map(len, attrs))
            ))

            if prev_free_space_offset != free_space_offset:
                pos = table.tell()
                table.seek(prev_free_space_offset)
                if available_space == FREE_SPACE_AT_END:
                    table.write(bytes('{offset}{delimiter}-1'.format(
                        offset=pos,
                        delimiter=delimiters.offset_delimiter.decode(ENCODING)
                    ), ENCODING))
                else:
                    table.write(next_)
                table.seek(pos)
            else:
                if available_space == FREE_SPACE_AT_END:
                    self.__free_space_offset = table.tell()
                else:
                    free_space_meta = next_.split(delimiters.offset_delimiter)
                    self.__free_space_offset = int(free_space_meta[0])
                    self.__free_space_limit = int(free_space_meta[1])

            if available_space != FREE_SPACE_AT_END:
                table.seek(-(self.__meta_length + self.__meta_header_length), os.SEEK_END)

            meta_start = table.tell()

        self.__update_meta(meta_start)

    def delete(self, row):
        if len(row) != len(self.__table_schema):
            raise TypeError('Invalid tuple schema')

        row = tuple(map(lambda a: str(a) if a is not None else None, row))

        with open(self.__file_path, 'rb+') as table:
            for index in self.__indicies:
                table.seek(index.offset)

                attrs = tuple()
                attrs_for_comparison = tuple()

                for i, limit in enumerate(index.attr_limits):
                    attr = (table.read(limit).decode(ENCODING),)
                    attrs = attrs + attr
                    attrs_for_comparison = attrs_for_comparison + ((None,) if row[i] is None else attr)

                if attrs_for_comparison == row:
                    table.seek(index.offset)
                    free_space_meta = '{offset}{delimiter}{limit}'.format(
                        offset=self.__free_space_offset,
                        limit=self.__free_space_limit,
                        delimiter=delimiters.offset_delimiter.decode(ENCODING)
                    ).ljust(index.limit)
                    table.write(bytes(free_space_meta, ENCODING))
                    self.__free_space_limit = index.limit
                    self.__free_space_offset = index.offset
                    self.__indicies.remove(index)
                    self.__rows_count -= 1

            table.seek(-(self.__meta_length + self.__meta_header_length), os.SEEK_END)
            meta_start = table.tell()

        self.__update_meta(meta_start)

    def update(self, row, new_row):
        self.delete(row)
        self.insert(new_row)

    def search(self, row):
        if len(row) != len(self.__table_schema):
            raise TypeError('Invalid tuple schema')

        row = tuple(map(lambda a: str(a) if a is not None else None, row))
        results = []

        with open(self.__file_path, 'rb') as table:
            for index in self.__indicies:
                table.seek(index.offset)

                attrs = tuple()
                attrs_for_comparison = tuple()

                for i, limit in enumerate(index.attr_limits):
                    attr = (table.read(limit).decode(ENCODING),)
                    attrs = attrs + attr
                    attrs_for_comparison = attrs_for_comparison + ((None,) if row[i] is None else attr)

                if attrs_for_comparison == row:
                    results.append(attrs)

        return results
