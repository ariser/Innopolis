package database.index;

import java.util.*;
import database.*;
import database.MyFramework.MyMap;

/**
 * Hash-based index implementation
 */
@SuppressWarnings("rawtypes")
public class HashIndex extends Index {
	private MyMap<Integer, List<Row>> index;
	private String column;

	private Integer getHash(Comparable val) {
		if (val instanceof String)
			return database.MyFramework.hash((String)val);
		else if (val instanceof Float)
			return database.MyFramework.hash((Float)val);
		return val.hashCode();
	}
	
	@Override
	public List<Row> find(Comparable one) {
		Integer hash = getHash(one);
		List<Row> list = index.get(hash);
		if (list == null) {
			list = new LinkedList<>();
			index.put(hash, list);
		}
		ArrayList<Row> result = new ArrayList<>();
		for (Row r: list) {
			if (r.get(column).equals(one))
			result.add(r);
		}
		return result;
	}

	@Override
	public void insert(Row row) {
		Integer hash = getHash(row.get(column));
		List<Row> list = index.get(hash);
		if (list == null) {
			list = new LinkedList<>();
			index.put(hash, list);
		}
		list.add(row);
	}

	@Override
	public void update(Row row) {
		delete(row);
		insert(row);
	}

	@Override
	public void delete(Row row) {
		Integer hash = getHash(row.get(column));
		List<Row> list = index.get(hash);
		if (list != null) {
			list.remove(row);
		}
	}

	public HashIndex(Table table, String column) {
		this.column = column;
		if (!table.isEmpty()) {
			for (Row r: table.getRawData().values())
				insert(r);
		}
	}
}
