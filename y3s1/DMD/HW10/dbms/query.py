class Filter(object):
    expressions = []

    def __init__(self, table, attrs):
        self.table = table
        self.attrs = attrs
        self.filter = self

    def where(self, attr):
        return _Where(self, attr)


class _Where(object):
    def __init__(self, f, attr):
        self.filter = f
        self.filter_by = attr

    def equals(self, value):
        def qwe(attrs):
            return attrs[self.filter_by] == str(value)

        self.filter.expressions.append(qwe)
        return _Concat(self.filter)

    def not_equals(self, value):
        def qwe(attrs):
            return attrs[self.filter_by] != str(value)

        self.filter.expressions.append(qwe)
        return _Concat(self.filter)

    def lt(self, value):
        def qwe(attrs):
            return attrs[self.filter_by] < str(value)

        self.filter.expressions.append(qwe)
        return _Concat(self.filter)

    def gt(self, value):
        def qwe(attrs):
            return attrs[self.filter_by] > str(value)

        self.filter.expressions.append(qwe)
        return _Concat(self.filter)

    def lte(self, value):
        def qwe(attrs):
            return attrs[self.filter_by] <= str(value)

        self.filter.expressions.append(qwe)
        return _Concat(self.filter)

    def gte(self, value):
        def qwe(attrs):
            return attrs[self.filter_by] >= str(value)

        self.filter.expressions.append(qwe)
        return _Concat(self.filter)


class _Concat(object):
    def __init__(self, f):
        self.filter = f

    def And(self, attr):
        self.filter.expressions.append('and')
        return _Where(self.filter, attr)

    def Or(self, attr):
        self.filter.expressions.append('or')
        return _Where(self.filter, attr)
