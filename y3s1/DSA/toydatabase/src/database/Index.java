package database;

import java.util.List;

/**
 * Class representing index for a table.
 * Should provide basic CRUD operations.
 */
public abstract class Index {
	/**
	 * provides all rows that matches exactly this number
	 */
	@SuppressWarnings("rawtypes")
	public abstract List<Row> find(Comparable one);

	/**
	 * Inserts new row into index metadata!
	 * Does not insert data itself! This is done in Table class
	 * @param row - new row
	 */
	public abstract void insert(Row row);

	/**
	 * Updates existing row in index metadata!
	 * Does not update data itself! This is done in Table class
	 * @param row 
	 */
	public abstract void update(Row row);

	/**
	 * Deletes existing row from index metadata!
	 * Does not delete data itself!
	 * @param row
	 */
	public abstract void delete(Row row);

	protected String column;

	/**
	 * Gets the column name index is attributed to
	 * @return column name
	 */
	public String getColumn() {
		return column;
	}
}