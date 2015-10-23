package database;

import database.index.SortedIndex;
import database.sql.QueryParser;
import database.sql.QueryResult;

public class Tests {
	
	public static QueryResult x(String q) throws Exception {
		return QueryParser.parse(q).execute();
	}
	
	public static void main(String[] args) {
		QueryResult qr;
		Table students = ToyDatabase.createTable("Students", "Surname", "Name", "Group");
		try {
			// loading data to table
			ToyDatabase.load("Students", "data.txt", "\t");
			
			// create index on the table
			students.addIndex(new SortedIndex(students, "surname"));
			
			// insert some strange data
			x("insert \"ZzZZ\", \"YYYY\", \"XXXX\" into students");
			//insert me
			x("insert \"Protasov\", \"Stanislav\", \"Faculty\" into students");
			//select me exactly
			qr = x("select * from students where Surname = \"Protasov\"");
			for (Row r: qr.RESULT_SET) {
				System.out.println(r.toString());
			}

			//select me by one condition
			qr = x("select * from students where Name between (\"Stan\", \"Stas\")");
			for (Row r: qr.RESULT_SET) {
				System.out.println(r.toString());
			}

			//select me by another condition
			qr = x("select * from students where Name > \"Stas\" and Group = \"Faculty\"");
			for (Row r: qr.RESULT_SET) {
				System.out.println(r.toString());
			}

			qr = x("SELECT * FROM Students WHERE Surname > \"Z\"");
			for (Row r : qr.RESULT_SET) System.out.println(r.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
