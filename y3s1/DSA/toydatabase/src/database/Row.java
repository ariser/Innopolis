package database;

import java.util.UUID;

/**
 * this is a table row implementation
 */
@SuppressWarnings("rawtypes")
public class Row {
	private Table table;
	protected Comparable[] data;
	private UUID rowID = UUID.randomUUID();

	/**
	 * @return unique identifier of the row
	 */
	public UUID getRowId() {
		return rowID;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Row) && ((Row)o).getRowId().equals(this.getRowId());
	}

	/**
	 * returns a value at specified column
	 * @param column index
	 * @return column value
	 */
	public Comparable get(int column) {
		return data[column];
	}

	/**
	 * returns a value at specified column
	 * @param column name
	 * @return column value
	 */
	public Comparable get(String column) {
		return data[table.getIndex(column)];
	}

	/**
	 * Update the data in the row in specified column
	 * @param column name
	 * @param value
	 */
	public void update(String column, Comparable value) {
		data[table.getIndex(column)] = value;
	}

	/**
	 * Update the data in the row in specified column
	 * @param column index
	 * @param value
	 */
	public void update(int column, Comparable value) {
		data[column] = value;
	}

	/**
	 * Constructor takes a table and array of values to place in the row 
	 * @param table database table
	 * @param vals values to store
	 */
	public Row(Table table, Comparable... vals) {
		this.table = table;
		data = vals;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < data.length - 1; i++) {
			sb.append(data[i]);
			sb.append(", ");
		}
		sb.append(data[data.length - 1]);
		sb.append("]");
		return sb.toString();
	}
}
