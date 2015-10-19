from dbms.const import *


class Metadata:
    header_length = 8

    length = None

    table_schema = None
    rows_count = None
    free_space_offset = None
    free_space_limit = None
    indicies = None

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
