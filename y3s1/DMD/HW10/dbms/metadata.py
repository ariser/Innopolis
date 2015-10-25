from dbms.const import *


class Metadata(object):
    __slots__ = ['length', 'table_schema', 'rows_count', 'free_space_offset', 'free_space_limit', 'indicies']
    header_length = 8

    def __init__(self, table_schema: list, rows_count: int, free_space_offset: int, free_space_limit: int,
                 indicies: list):
        self.table_schema = table_schema
        self.rows_count = rows_count
        self.free_space_offset = free_space_offset
        self.free_space_limit = free_space_limit
        self.indicies = indicies

    def __str__(self):
        indicies = [str(index) for index in self.indicies]
        meta = '{schema} {rows_count} {free_offset}{delimiter}{free_limit} {indicies}'.format(
            schema=','.join(self.table_schema),
            rows_count=self.rows_count,
            free_offset=self.free_space_offset,
            free_limit=self.free_space_limit,
            indicies=' '.join(indicies),
            delimiter=Delimiters.offset.decode(DBMS_ENCODING)
        )
        self.length = len(meta)
        meta += '{length}'.format(length=self.length).rjust(self.header_length)
        return meta

    class Index(object):
        offset = None
        limit = None
        attr_limits = None

        def __init__(self, offset: int, limit: int, attr_limits: list):
            self.offset = offset
            self.limit = limit
            self.attr_limits = attr_limits

        def __str__(self):
            return '{offset}{delimiter}{limit}{delimiter}{attr_limit}'.format(
                offset=self.offset,
                limit=self.limit,
                attr_limit=Delimiters.attr.decode(DBMS_ENCODING).join(list(map(str, self.attr_limits))),
                delimiter=Delimiters.offset.decode(DBMS_ENCODING)
            )

        def as_dict(self):
            return {
                'offset': self.offset,
                'limit': self.limit,
                'attr_limits': self.attr_limits
            }
