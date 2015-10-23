package database;

import java.util.*;
import database.index.SortedIndex;
import database.sql.QueryResult;

/**
 * Class representing a database table
 */
public class Table {
	/**
	 * column names
	 */
	private List<String> columns;

	/**
	 * @return get column names
	 */
	public List<String> getColumns() {
		return Collections.unmodifiableList(columns);
	}

	/**
	 * Find index of the column by name
	 * @param col column name
	 * @return index
	 */
	public int getIndex(String col) {
		return columns.indexOf(col.toLowerCase());
	}

	/**
	 * Indices on the table
	 */
	private List<Index> indices;

	public void addIndex(Index i) {
		indices.add(i);
	}

	public void removeIndex(Index i) {
		indices.remove(i);
	}

	/**
	 * removes all indices from the specifier column
	 * @param column name
	 */
	public void removeIndicesForColumn(String column) {
		for (Index i : getRelatedIndices(column))
			removeIndex(i);
	}
	
	
	/**
	 * Get all indices that are attributed to a column
	 * @param column name
	 * @return indices list
	 */
	private List<Index> getRelatedIndices(String column) {
		List<Index> result = new ArrayList<>();
		for (Index i : indices)
			if (i.getColumn().equalsIgnoreCase(column))
				result.add(i);
		return result;
	}

	/**
	 * table data
	 */
	protected Map<UUID, Row> data;

	/**
	 * return table data as is
	 * @return
	 */
	public Map<UUID, Row> getRawData() {
		return data;
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

	/**
	 * Inserts data into the table, updates indices
	 * @param row new row
	 * @return number or rows affected
	 */
	public QueryResult insert(Row row) {
		data.put(row.getRowId(), row);
		for (Index i : indices)
			i.insert(row);

		QueryResult qr = new QueryResult();
		qr.RESULT_SET = new ArrayList<>(1);
		qr.RESULT_SET.add(row);
		qr.ROWS_AFFECTED = 1;
		return qr;
	}

	/**
	 * Selects rows according to condition
	 * @param c conditions
	 * @return Rows that match
	 */
	public QueryResult select(Condition[] c) {
		QueryResult qr = new QueryResult();
		qr.RESULT_SET = new ArrayList<>();

		Condition condition = c.length > 0 ? c[0] : null;
		List<Index> li = condition != null ? getRelatedIndices(condition.COLUMN) : null;
		Collection<Row> preset = null;

		if (condition != null && li != null && !li.isEmpty()) {
			switch (condition.CONDITION) {
			case EQ:
				preset = li.get(0).find(condition.PARAMS[0]);
				break;
			case MORE:
				for (Index i: li)
					if (i instanceof SortedIndex) {
						preset = ((SortedIndex)i).findBetween(condition.PARAMS[0], null);
						break;
					}
				break;
			case LESS:
				for (Index i: li)
					if (i instanceof SortedIndex) {
						preset = ((SortedIndex)i).findBetween(null, condition.PARAMS[0]);
						break;
					}
				break;
			case BETWEEN:
				for (Index i: li)
					if (i instanceof SortedIndex) {
						preset = ((SortedIndex)i).findBetween(condition.PARAMS[0], condition.PARAMS[1]);
						break;
					}
				break;
			default:
				break;
			}			
		}
		if (preset == null) preset = data.values();

		for (Row r : preset) {
			boolean match = true;
			if (c != null)
				for (Condition cond : c)
					if (!(match &= cond.match(r)))
						break;
			if (match)
				qr.RESULT_SET.add(r);
		}

		qr.ROWS_AFFECTED = qr.RESULT_SET.size();
		return qr;
	}

	/**
	 * Updates data in the table for all rows that match conditions
	 * @param c conditions
	 * @param newvals new values
	 * @return rows affected
	 */
	public QueryResult update(Condition[] c, Row newvals) {
		QueryResult qr = new QueryResult();
		qr.RESULT_SET = new ArrayList<>();

		for (Row r : data.values()) {
			boolean match = true;
			if (c != null)
				for (Condition cond : c)
					if (!(match &= cond.match(r)))
						break;
			if (match) {
				for (String column : getColumns()) {
					if (newvals.get(column) != null) {
						r.update(column, newvals.get(column));
					}
				}
				for (Index i : indices)
					i.update(r);
			}

		}

		qr.ROWS_AFFECTED = qr.RESULT_SET.size();
		return qr;
	}

	/**
	 * Deletes all rows that match conditions
	 * @param c conditions
	 * @return affected rows
	 */
	public QueryResult delete(Condition[] c) {
		QueryResult qr = new QueryResult();
		qr.RESULT_SET = new ArrayList<>();
		for (Row r : data.values()) {
			boolean match = true;
			if (c != null)
				for (Condition cond : c)
					if (!(match &= cond.match(r)))
						break;
			if (match) {
				qr.RESULT_SET.add(r);
				data.remove(r);
				for (Index i : indices)
					i.delete(r);
			}
		}

		qr.ROWS_AFFECTED = qr.RESULT_SET.size();
		return qr;
	}

	/**
	 * Creates new table with column names provided
	 * @param columns column names
	 */
	public Table(String... columns) {
		this.columns = new ArrayList<>();
		for (String s : columns)
			this.columns.add(s.toLowerCase());
		data = new HashMap<>();
		indices = new ArrayList<>();
	}
}
