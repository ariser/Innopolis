from radix_sort import RadixSort

vals = [1, 543, 'qwe', 'dd', 'd', 'hello world', 76, 2, 34, 6, 8, 5, 342, 35, 68, 7543]
vals1 = [1, 543, 76, 2, 34, 6, 8, 5, 342, 35, 68, 7543]

RadixSort.sort_msd(vals)
RadixSort.sort_lsd(vals1)

print(vals)
print(vals1)
