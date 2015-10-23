package database;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * 
 * Class representing database. Can store, create and delete tables
 *
 */
public class ToyDatabase {

	public static Map<String, Table> tables = new HashMap<String, Table>();
	
	/**
	 * Creates new table with column names provided
	 * @param name - table name
	 * @param columns - array of column names
	 * @return new table created
	 */
	public static Table createTable(String name, String... columns) {
		Table t = new Table(columns);
		tables.put(name.toLowerCase(), t);
		return t;
	}
	
	/**
	 * gets table of database by name
	 * @param name table name
	 * @return table object
	 */
	public static Table getTable(String name) {
		return tables.get(name.toLowerCase());
	}
	
	/**
	 * drops table
	 * @param name table name
	 */
	public static void dropTable(String name) {
		tables.remove(name.toLowerCase());
	}
	
	/**
	 * Loads table from text file. Assumes we already know columns, each line
	 * of file is a Row. Each row is separated by delimiter
	 * @param tableName existing table name
	 * @param file existing text file name
	 * @param delimiter char or string that separates values
	 * @throws FileNotFoundException
	 */
	public static void load(String tableName, String file, String delimiter) throws FileNotFoundException {
		Table table = getTable(tableName);
		Scanner scanner = new Scanner(new File(file));
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] parts = line.split(delimiter);
			table.insert(new Row(table, parts));
		}
		scanner.close();
	}
}
