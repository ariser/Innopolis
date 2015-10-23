package database.sql;
import java.util.List;
import database.Row;

/**
 * 
 * Class representing query  result: result set + number of rows affected
 *
 */
public class QueryResult {
	public List<Row> RESULT_SET;
	public int ROWS_AFFECTED;
}