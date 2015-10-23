def _lsd_sort(source: list):
    buckets = []

    max_len = 0
    index = -1

    while max_len != abs(index):
        for item in source:
            item_str = str(item)
            item_len = len(item_str)

            if item_len > max_len:
                max_len = item_len

            char_code = ord(item_str[index]) if item_len >= abs(index) else 0

            if len(buckets) - 1 < char_code:
                __fill_array(buckets, char_code, [])

            buckets[char_code].append(item)

        del source[:]
        for bucket in buckets:
            source += bucket
        buckets = []
        index -= 1


def _msd_sort(source: list, index: int=0):
    buckets = []

    if len(source) == 1:
        return source

    for item in source:
        item_str = str(item)
        item_len = len(item_str)

        char_code = ord(item_str[index]) if item_len - 1 >= index else 0

        if len(buckets) - 1 < char_code:
            __fill_array(buckets, char_code)

        buckets[char_code].append(item)

    index += 1
    del source[:]
    for bucket in buckets:
        source += _msd_sort(bucket, index)

    return source


def __fill_array(arr: list, index: int, val=None):
    for i in range(len(arr), index + 1):
        arr.append([])


class RadixSort:
    @staticmethod
    def sort_lsd(source: list):
        _lsd_sort(source)

    @staticmethod
    def sort_msd(source: list):
        _msd_sort(source)