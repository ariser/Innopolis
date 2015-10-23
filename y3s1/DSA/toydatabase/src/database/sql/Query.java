package database.sql;

import java.util.List;
import database.Condition;
import database.Row;
import database.Table;
import database.service.QueryType;

/**
 * class representing parsed query
 */
public class Query {
	Table table;
	QueryType queryType;
	Condition[] conditions;
	List<String> columns;
	Row row;

	/**
	 * SELECT, UPDATE, DELETE, INSERT
	 */
	public QueryType getQueryType() {
		return queryType;
	}

	/**
	 * All conditions considered as AND
	 */
	public Condition[] getConditions() {
		return conditions == null ? new Condition[0] : conditions;
	}

	public void setConditions(Condition... conds) {
		conditions = conds;
	}

	/**
	 * @return the row that is used for insertion or update
	 */
	public Row getRow() {
		return row;
	}

	public List<String> getColumns() {
		return columns;
	}

	public QueryResult execute() {
		switch (queryType) {
		case INSERT:
			return table.insert(getRow());
		case SELECT:
			return table.select(getConditions());
		case UPDATE:
			return table.update(getConditions(), getRow());
		case DELETE:
			return table.delete(getConditions());
		default:
			return null;
		}

	}

	/**
	 * Creating a query representation
	 * 
	 * @param t
	 *            table on which query is executed
	 * @param qt
	 *            SELECT, UPDATE, DELETE, INSERT
	 * @param conds
	 *            different conditions
	 * @param columns
	 *            columns that should be selected
	 * @param row
	 *            optional data for update and insert queries
	 */
	public Query(Table t, QueryType qt, Condition[] conds, List<String> columns, Row row) {
		table = t;
		queryType = qt;
		conditions = conds;
		this.columns = columns;
		this.row = row;
	}
}
