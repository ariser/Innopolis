from math import ceil

from countingsort import CountingSort


class BucketSort:
    @staticmethod
    def sort(source, buckets_number=1):
        max_v = max(source)
        step = ceil(max_v / buckets_number)

        buckets = [[] for i in range(buckets_number)]

        for item in source:
            buckets[ceil(item / step) - 1].append(item)

        for bucket in buckets:
            CountingSort.sort(bucket)

        del source[:]

        for bucket in buckets:
            source += bucket
