package database; //TODO: remove this line when submitting

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
	 * TODO Implement your own binary search algorithms
	 * 
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
		// Comparable fieldval = map.apply(row);
		//TODO implement
		return -1;
	}

	/**
	 * TODO implement your own binary search algorithms that returns either
	 * element index OR index of the next greater element
	 * 
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
		//TODO implement
		return -1;
	}

	/**
	 * TODO Implement your own sort that is VERY fast
	 * 
	 * @param data
	 *            data to sort
	 * @param map
	 *            - method that converts object from the list to Comparable
	 *            object. Most likely this is a method that takes value from the
	 *            row. use map.apply(value) to start
	 */
	@SuppressWarnings("rawtypes")
	public static <T> void sort(List<T> data, Function<Object, Comparable> map) {
		//TODO implement
	}

	/**
	 * TODO Your own hash function with uniform distribution for input strings
	 * 
	 * @param string
	 *            any string
	 * @return hash for the string
	 */
	public static int hash(String string) {
		// TODO: implement
		return 0;
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
	 * TODO Your own hash function with uniform distribution for floats
	 * 
	 * @param flt
	 *            floating point number
	 * @return hash code
	 */
	public static int hash(Integer flt) {
		// TODO: implement
		return 0;
	}
	/**
	 * TODO entry point for assignment #2 task
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO: here you will implement code at assignment #2
		// you should submit this file excluding package declaration
		
		//TODO: remove code below at assignment submission
		MyFrameworkTests.testYourBinarySearch();
		MyFrameworkTests.testYourSorting();
		MyFrameworkTests.testYourHashmap();
		MyFrameworkTests.testYourIntHash();
		MyFrameworkTests.testYourFloatHash();
		MyFrameworkTests.testYourStringHash();
	}
}
