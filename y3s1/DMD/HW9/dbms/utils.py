def convert_list(src, converter):
    return list(map(converter, src))


def to_string_list(src):
    return convert_list(src, lambda s: s.decode(DBMS_ENCODING))


def to_int_list(src):
    return convert_list(src, int)
