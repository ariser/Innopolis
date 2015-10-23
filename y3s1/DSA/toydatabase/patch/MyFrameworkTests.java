package database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

public class MyFrameworkTests {
	public static void testYourHashmap() {
		try {
			System.out.println("=============== TEST HashMap ===============");
			// Arrange
			Map<String, String> map = new MyFramework.MyMap<>();
			map.put("one", "one");
			map.put("two", "two");
			map.clear();

			if (!map.isEmpty()) {
				System.out.println("[ERR] List is expected to be empty");
				System.out.flush();
			} else {
				System.out.println("[OK] List is empty");
				System.out.flush();
			}

			map.put("one", "one");
			map.put("two", "two");
			map.put("three", "three");
			map.put("one", "four");
			map.put("two", "five");
			map.put("One", "six");
			map.remove("three");

			if (map.size() != 3) {
				System.out.println("[ERR] List size expected to be 3, not " + map.size());
			} else {
				System.out.println("[OK] List size is 3");
			}

			if (map.get("one") != "four") {
				System.out.println("[ERR] map['one'] should be 'four', not '" + map.get("one") + "'");
			} else {
				System.out.println("[OK] map['one'] is 'four'");
			}

			if (map.get("three") != null) {
				System.out.println("[ERR] map['three'] should be removed");
			} else {
				System.out.println("[OK] map['three'] is missing as expected");
			}

			if (map.get("One") != "six") {
				System.out.println("[ERR] map['One'] should be 'six', not '" + map.get("One") + "'");
			} else {
				System.out.println("[ERR] map['One'] is 'six'");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public static void testYourSorting() {
		try {
			System.out.println("=============== TEST Sorting ===============");
			Integer[] data = new Integer[] { 1, 5, 9, 0, 4, 7, 3, 8, 6, 2 };
			List<Integer> dataList = Arrays.asList(data);
			Function<Object, Comparable> map = new Function<Object, Comparable>() {
				@Override
				public Comparable apply(Object t) {
					return (Comparable) t;
				}
			};
			MyFramework.sort(dataList, map);
			int i;
			for (i = 0; i < dataList.size() - 1; i++) {
				if (dataList.get(i) > dataList.get(i + 1)) {
					System.out.println("[ERR] array is not ordered");
					break;
				}
			}
			if (i == dataList.size() - 1) {
				System.out.println("[OK] sorted");
				System.out.flush();

				int sizes[] = new int[] { 10000, 10000, 20000, 100000, 1000000 };
				double results[] = new double[5];
				int max = 10000000;
				int runs = 10;
				Random r = new Random();
				for (int x = 0; x < sizes.length; x++) {
					for (int run = 0; run < runs; run++) {
						List<Integer> list = new ArrayList<Integer>(sizes[x]);
						for (int z = 0; z < sizes[x]; z++)
							list.add(r.nextInt(max));

						long start = System.nanoTime();
						MyFramework.sort(list, map);
						long end = System.nanoTime();
						results[x] += (end - start);
					}
					results[x] /= runs;
					System.out.println("[INFO] sorted for " + sizes[x] + " in avg " + results[x] / 1e9 + " seconds");
				}

				int sizesRat = sizes[4] / sizes[3];
				double resultRat = results[4] / results[3];
				if (resultRat > Math.pow(sizesRat, 3)) {
					System.out.println("[ERR] your implementation is worse than O(n^3)");
				} else if (resultRat >= Math.pow(sizesRat, 2)) {
					System.out.println("[ERR] your implementation O(n^2) or worse");
				} else if (resultRat < Math.pow(sizesRat, 2)) {
					System.out.println("[OK] your implementation is better than O(n^2)! Well done!");
					if (resultRat < 2 * results[3] / results[1]) {
						System.out.println("[OK] your implementation is linear! Mother of Gods!");
					}
				}
			} else {
				System.out.println("[-->] Skipping performance test then");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void testYourStringHash() {
		System.out.println("============== TEST StringHash =============");
		int runs = 1000000;
		int maxHash = 1001;
		int[] hashes = new int[maxHash];
		Random r = new Random();
		for (int run = 0; run < runs; run++) {
			byte[] bytes = new byte[1 + r.nextInt(50)];
			r.nextBytes(bytes);
			String s = new String(bytes);
			hashes[MyFramework.hash(s) % maxHash]++;
		}
		Arrays.sort(hashes);
		if ((double) (hashes[maxHash - 1]) / hashes[0] > 1.2) {
			System.out.println("[ERR] seems like your string hash is not uniform :(");
		}
	}

	public static void testYourFloatHash() {
		System.out.println("============== TEST FloatHash ==============");
		int runs = 1000000;
		int maxHash = 1001;
		int[] hashes = new int[maxHash];
		Random r = new Random();
		for (int run = 0; run < runs; run++) {
			hashes[MyFramework.hash(r.nextFloat()) % maxHash]++;
		}
		Arrays.sort(hashes);
		if ((double) (hashes[maxHash - 1]) / hashes[0] > 1.2) {
			System.out.println("[ERR] seems like your float hash is not uniform :(");
		}
	}

	public static void testYourIntHash() {
		System.out.println("=============== TEST IntHash ===============");
		int runs = 1000000;
		int maxHash = 1001;
		int[] hashes = new int[maxHash];
		Random r = new Random();
		for (int run = 0; run < runs; run++) {
			hashes[MyFramework.hash(r.nextInt()) % maxHash]++;
		}
		Arrays.sort(hashes);
		if ((double) (hashes[maxHash - 1]) / hashes[0] > 1.2) {
			System.out.println("[ERR] seems like your int hash is not uniform :(");
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void testYourBinarySearch() {
		System.out.println("============== TEST BinSearch ==============");
		Integer[] data = new Integer[] { 1, 3, 4, 5, 6, 7, 8, 8, 14, 56, 67, 654 };
		List<Integer> datalist = Arrays.asList(data);
		Function<Object, Comparable> map = new Function<Object, Comparable>() {
			@Override
			public Comparable apply(Object t) { return (Comparable)t; }
		};
		
		int idx = MyFramework.binarySearch(datalist, map, 7);
		if (idx == 5) System.out.println("[OK] found as expected");
		else System.out.println("[ERR] wrong value");
		
		idx = MyFramework.binarySearch(datalist, map, 10);
		if (idx == -1) System.out.println("[OK] found as expected");
		else System.out.println("[ERR] wrong value");

		idx = MyFramework.binarySearchOrNext(datalist, map, 14);
		if (idx == 8) System.out.println("[OK] found as expected");
		else System.out.println("[ERR] wrong value");

		idx = MyFramework.binarySearchOrNext(datalist, map, 15);
		if (idx == 9) System.out.println("[OK] found as expected");
		else System.out.println("[ERR] wrong value");	
	}
}
