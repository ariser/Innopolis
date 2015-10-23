package database.index;

import java.util.*;
import java.util.function.Function;

import database.*;

@SuppressWarnings("rawtypes")
public class SortedIndex extends Index {
	ArrayList<Row> idx_list = new ArrayList<>();

	private int getByUuid(UUID u) {
		for (int i = 0; i < idx_list.size(); i++)
			if (u.equals(idx_list.get(i).getRowId()))
				return i;
		return -1;
	}

	/**
	 * Searches element in the list of indices
	 * @param value value we search
	 * @return index of value if found, -1 if none
	 */
	private int binarySearch(Comparable value) {
		Function<Object, Comparable> map = new Function<Object, Comparable>() {
			@Override
			public Comparable apply(Object t) {
				return ((Row)t).get(getColumn());
			}
		};
		return MyFramework.binarySearch(idx_list, map, value);
	}

	/**
	 * Searches element in the list of indices, or next bigger
	 * @param value value we search
	 * @return index of value if found, or next greater value
	 */

	private int binarySearchOrNext(Comparable value) {
		Function<Object, Comparable> map = new Function<Object, Comparable>() {
			@Override
			public Comparable apply(Object t) {
				return ((Row)t).get(getColumn());
			}
		};
		return MyFramework.binarySearchOrNext(idx_list, map, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Row> find(Comparable one) {
		List<Row> result = new ArrayList<>();
		int i = one == null ? 0 : binarySearch(one);
		if (i > -1) {
			int i2 = i;
			while (idx_list.get(i2).get(getColumn()).compareTo(one) == 0) {
				result.add(idx_list.get(i2));
				if (++i2 >= idx_list.size())
					break;
			}
			i2 = i - 1;
			if (i2 >= 0)
				while (idx_list.get(i2).get(getColumn()).compareTo(one) == 0) {
					result.add(idx_list.get(i2));
					if (--i2 < 0)
						break;
				}
		}
		return result;
	}

	/**
	 * Extra method for SortedIndex that allows to get sorted range
	 * @param less value that is less then all selected element. Or null if none
	 * @param more value that is more then all selected element. Or null if none
	 * @return the list
	 */
	public List<Row> findBetween(Comparable less, Comparable more) {
		List<Row> result = new ArrayList<>();
		int iStart = less == null ? 0 : binarySearchOrNext(less);
		int iEnd = more == null ? idx_list.size() : binarySearchOrNext(less);
		for (int i = iStart; i < iEnd; i++)
			result.add(idx_list.get(i));
		return result;
	}

	@Override
	public void insert(Row row) {
		int i = -1;
		Comparable val = row.get(getColumn());
		if ((i = binarySearch(val)) > -1) {
			idx_list.add(i, row);
		} else {
			idx_list.add(binarySearchOrNext(val), row);
		}
	}

	@Override
	public void update(Row row) {
		delete(row);
		insert(row);
	}

	@Override
	public void delete(Row row) {
		int id = getByUuid(row.getRowId());
		if (id >= 0)
			idx_list.remove(id);
	}

	public SortedIndex(Table table, String column) {
		this.column = column;
		if (!table.isEmpty()) {
			idx_list.addAll(table.getRawData().values());
			idx_list.sort(new Comparator<Row>() {
				@SuppressWarnings("unchecked")
				@Override
				public int compare(Row o1, Row o2) {
					return o1.get(getColumn()).compareTo(o2.get(getColumn()));
				}
			});
		}
	}
}
