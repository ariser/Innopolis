from dbms import delimiters


class Index:
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
            attr_limit=delimiters.attr_delimiter.decode('utf-8').join(list(map(str, self.attr_limits))),
            delimiter=delimiters.offset_delimiter.decode('utf-8')
        )