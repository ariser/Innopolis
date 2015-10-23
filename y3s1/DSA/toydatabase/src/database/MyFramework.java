/**
 * @author Nikolay Troshkov
 * @email n.troshkov@innopolis.ru
 * @date 29 Sep, 2015
  * In accordance with the academic honor, I (Nikolay Troshkov) certify that
  * the answers here are my own work without copying of others and
  * solutions from Internet or other sources.
 */

package database; //TODO: remove this line when submitting

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.Function;

/**
 * TODO: This code should be implemented by a student
 */
public class MyFramework {

	/**
	 * TODO implement your own HashMap class!
	 *
	 * @param <K>
	 *            key type
	 * @param <V>
	 *            value type
	 */
	public static class MyMap<K, V> implements Map<K, V> {

		@Override
		public void clear() {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean containsKey(Object arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean containsValue(Object arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Set<java.util.Map.Entry<K, V>> entrySet() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public V get(Object arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Set<K> keySet() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public V put(K arg0, V arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void putAll(Map<? extends K, ? extends V> arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public V remove(Object arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Collection<V> values() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	/**
	 * @param data
	 *            - list of data to search. Assume data is sorted
	 * @param map
	 *            - method that converts object from the list to Comparable
	 *            object. Most likely this is a method that takes value from the
	 *            row. use map.apply(value) to start
	 * @param value
	 *            - value we are searching for
	 * @return index of the element in array if present, else -1
	 */
	@SuppressWarnings({ "rawtypes" })
	public static <T> int binarySearch(List<T> data, Function<Object, Comparable> map, Comparable value) {
		int result = binarySearchOrNext(data, map, value);
		if (result < 0 || !data.get(result).equals(value)) {
			result = -1;
		}
		return result;
	}

	/**
	 * @param data
	 *            - list of data to search. Assume data is sorted
	 * @param map
	 *            - method that converts object from the list to Comparable
	 *            object. Most likely this is a method that takes value from the
	 *            row. use map.apply(value) to start
	 * @param value
	 *            - value we are searching for
	 * @return index of the element in array if present, or index of next
	 *         greater element
	 */
	@SuppressWarnings({ "rawtypes" })
	public static <T> int binarySearchOrNext(List<T> data, Function<Object, Comparable> map, Comparable value) {
		int low = 0;
        int mid = -1;
        int high = data.size() - 1;

        Comparable midValue;

        while (low <= high) {
            mid = (low + high) / 2;
			midValue = map.apply(data.get(mid));

            if (value.equals(midValue)) {
                return mid;
            }

            if (value.compareTo(midValue) < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

		return low >= data.size() ? data.size() - 1 : low;
	}

	/**
	 * @param data
	 *            data to sort
	 * @param map
	 *            - method that converts object from the list to Comparable
	 *            object. Most likely this is a method that takes value from the
	 *            row. use map.apply(value) to start
	 */
	@SuppressWarnings("rawtypes")
	public static <T> void sort(List<T> data, Function<Object, Comparable> map) {
		// insertion sort
		for (int i = 0; i < data.size(); i++) {
			T currentVal = data.get(i);
			Comparable currentValComparable = (Comparable)currentVal;
			int j = i;
			while (j > 0 && currentValComparable.compareTo(map.apply(data.get(j-1))) < 0) {
				data.set(j, data.get(j - 1));
				j--;
			}
			data.set(j, currentVal);
		}
	}

	/**
	 * TODO Your own hash function with uniform distribution for input strings
	 *
	 * @param string
	 *            any string
	 * @return hash for the string
	 */
	public static int hash(String string) {
		int hash = 0;

		int string_length = string.length();
		for (int index = 0; index < string_length; index++) {
			char c = string.charAt(index);
			hash += Math.pow(index + 1, 2) * (int)c;
		}

		return hash;
	}

	/**
	 * TODO Your own hash function with uniform distribution for floats
	 *
	 * @param flt
	 *            floating point number
	 * @return hash code
	 */
	public static int hash(Float flt) {
		// TODO: implement
		return 0;
	}

	/**
	 * TODO Your own hash function with uniform distribution for integers
	 *
	 * @param intg
	 *            integer number
	 * @return hash code
	 */
	public static int hash(Integer intg) {
		// TODO: implement
		return intg * 184124 >>> 1;
	}

	/**
	 * TODO entry point for assignment #2 task
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		String dataType;
		String dataLine;
		String[] data;
		List<Float> dataFloat;
		List<Integer> dataInteger;
		List<String> dataString;
		String valueToFind;

		try {
			Scanner in = new Scanner(new File("sort.in"), "utf-8");

			dataType = in.next();
			dataLine = in.nextLine();
			valueToFind = in.next();

			data = dataLine.split("\\s+");

			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Input file not found.");
			return;
		}

		switch (dataType) {
			case "Float":
				dataFloat = new ArrayList<>(data.length);
				for (String item : data) {
					dataFloat.add(Float.parseFloat(item));
				}
				sort(dataFloat);
				break;
			case "Integer":
				dataInteger = new ArrayList<>(data.length);
				for (String item : data) {
					dataInteger.add(Integer.parseInt(item));
				}
				break;
			case "String":
				dataString = new ArrayList<>(data.length);
				for (String item : data) {
					dataString.add(item);
				}
				break;
		}

		writeResult(binarySearch(data, valueToFind));


		//TODO: remove code below at assignment submission
		MyFrameworkTests.testYourBinarySearch();
//		MyFrameworkTests.testYourSorting();
		MyFrameworkTests.testYourHashmap();
		MyFrameworkTests.testYourIntHash();
		MyFrameworkTests.testYourFloatHash();
		MyFrameworkTests.testYourStringHash();
	}

	private static void writeResult(String result) {
		File out = new File("sort.out");
		if (!out.exists()) {
			try {
				out.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream fos = new FileOutputStream(out, false);
			OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("utf-8"));
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(result);
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
