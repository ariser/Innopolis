def _sort(source, min_v, max_v):
    if source is None or len(source) == 0:
        return

    if min_v > max_v:
        raise ValueError("min can't be bigger than max")

    counter = [0 for i in range(min_v, max_v + 1)]

    for item in source:
        counter[item - min_v] += 1

    write_index = 0
    for i in range(min_v, max_v + 1):
        for j in range(counter[i - min_v]):
            source[write_index] = i
            write_index += 1


class CountingSort(object):
    @staticmethod
    def sort(source):
        if source is None or len(source) == 0:
            return

        _sort(source, min(source), max(source))