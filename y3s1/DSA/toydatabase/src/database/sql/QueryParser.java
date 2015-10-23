package database.sql;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import database.Condition;
import database.ToyDatabase;
import database.Row;
import database.Table;
import database.service.ConditionType;
import database.service.QueryType;

/**
 * class that parses string SQL queries
 */
public class QueryParser {

	@SuppressWarnings("rawtypes")
	private static Condition[] parseConditions(List<String> tokensList, int start) throws SQLSyntaxErrorException {
		int idx = start;
		List<Condition> result = new ArrayList<>();
		while (idx < tokensList.size()) {
			String col = tokensList.get(idx);
			idx++;
			ConditionType ct;
			Comparable[] values = new Comparable[2];
			String operator = tokensList.get(idx).toLowerCase();
			values[0] = getValue(tokensList.get(++idx));
			switch (operator) {
			case ">":
				ct = ConditionType.MORE;
				break;
			case ">=":
				ct = ConditionType.MORE_EQ;
				break;
			case "<":
				ct = ConditionType.LESS;
				break;
			case "<=":
				ct = ConditionType.LESS_EQ;
				break;
			case "=":
				ct = ConditionType.EQ;
				break;
			case "between":
				ct = ConditionType.BETWEEN;
				values[1] = getValue(tokensList.get(++idx));
				break;
			default:
				throw new SQLSyntaxErrorException("Unknown condition operator " + operator);
			}
			result.add(new Condition(ct, col, values));
			idx++;
			if (idx == tokensList.size())
				break;
			else if (!tokensList.get(idx).equalsIgnoreCase("and"))
				throw new SQLSyntaxErrorException("'And' expected");
			else idx++;
		}
		return result.toArray(new Condition[0]);
	}

	private static Query parseSelect(List<String> tokensList) throws SQLSyntaxErrorException {
		Condition[] conditions;
		int fromToken = -1, c = 0;
		while (++c < tokensList.size()) {
			if (tokensList.get(c).equalsIgnoreCase("from")) {
				fromToken = c;
				break;
			}
		}
		if (fromToken == -1)
			throw new SQLSyntaxErrorException("'From' expected");

		List<String> cols = new ArrayList<>();
		Table t = ToyDatabase.getTable(tokensList.get(fromToken + 1));
		if (fromToken == 2 && tokensList.get(fromToken - 1).equals("*")) {
			cols.addAll(t.getColumns());
		} else {
			for (int i = 1; i < fromToken; i++)
				cols.add(tokensList.get(i));
		}

		int whereToken = -1;
		c = fromToken;
		while (++c < tokensList.size()) {
			if (tokensList.get(c).equalsIgnoreCase("where")) {
				whereToken = c;
				break;
			}
		}
		if (whereToken == -1)
			conditions = null;
		else
			conditions = parseConditions(tokensList, whereToken + 1);

		return new Query(t, QueryType.SELECT, conditions, cols, null);
	}

	@SuppressWarnings("rawtypes")
	private static Comparable getValue(String v) {
		if (v.charAt(0) == '"')
			return (v.substring(1, v.length() - 1));
		else
			return (Double.parseDouble(v));
	}

	@SuppressWarnings("rawtypes")
	private static Query parseInsert(List<String> tokensList) throws SQLSyntaxErrorException {
		int intoToken = -1, c = 0;
		while (++c < tokensList.size()) {
			if (tokensList.get(c).equalsIgnoreCase("into")) {
				intoToken = c;
				break;
			}
		}
		if (intoToken == -1)
			throw new SQLSyntaxErrorException("'Into' expected");

		List<Comparable> vals = new ArrayList<>();
		Table t = ToyDatabase.getTable(tokensList.get(intoToken + 1));
		for (int i = 1; i < intoToken; i++) {
			vals.add(getValue(tokensList.get(i)));
		}
		Row r = new Row(t, (Comparable[]) vals.toArray(new Comparable[0]));
		return new Query(t, QueryType.INSERT, null, null, r);
	}

	private static Query parseDelete(List<String> tokensList) throws SQLSyntaxErrorException {
		if (!tokensList.get(1).equalsIgnoreCase("from"))
			throw new SQLSyntaxErrorException("'From' expected");
		String table = tokensList.get(2);
		Condition[] cond = null;
		if (tokensList.size() > 4 && tokensList.get(4).equalsIgnoreCase("where")) {
			cond = parseConditions(tokensList, 5);
		}
		Query q = new Query(ToyDatabase.getTable(table), QueryType.DELETE, cond, null, null);
		return q;
	}

	private static Query parseUpdate(List<String> tokensList) throws SQLSyntaxErrorException {
		String tableName = tokensList.get(1);
		Table table = ToyDatabase.getTable(tableName);
		if (!tokensList.get(2).equalsIgnoreCase("set"))
			throw new SQLSyntaxErrorException("'From' expected");
		int whereToken = -1, c = 2;
		while (++c < tokensList.size()) {
			if (tokensList.get(c).equalsIgnoreCase("where")) {
				whereToken = c;
				break;
			}
		}
		Condition[] cond = null;
		if (whereToken > -1) {
			cond = parseConditions(tokensList, whereToken + 1);
			whereToken = tokensList.size();
		}
		Row r = new Row(table, new Comparable[table.getColumns().size()]);
		for (int i = 3; i < whereToken; i += 3) {
			if (tokensList.get(i + 1) != "=")
				throw new SQLSyntaxErrorException("'=' expected");
			String column = tokensList.get(i);
			@SuppressWarnings("rawtypes")
			Comparable value = getValue(tokensList.get(i + 2));
			r.update(column, value);
		}
		return new Query(table, QueryType.UPDATE, cond, null, r);
	}

	public static List<String> getTokens(String q) throws SQLSyntaxErrorException {
		Character[] allDelimiters = new Character[] { ' ', '\t', '\n', '\r', ',', '(', ')' };
		Character[] comparators = new Character[] { '<', '>', '=' };
		List<Character> ads = Arrays.asList(allDelimiters);
		List<Character> cms = Arrays.asList(comparators);
		List<String> result = new ArrayList<>();
		int idx = 0;
		while (idx < q.length()) {
			// skip delimiters
			while (idx < q.length() && ads.contains(q.charAt(idx)))
				idx++;
			if (idx < q.length()) {
				StringBuilder sb = new StringBuilder();
				switch (q.charAt(idx)) {
				case '\"':
					sb.append(q.charAt(idx++));
					while (idx < q.length() && q.charAt(idx) != '\"') {
						sb.append(q.charAt(idx++));
					}
					if (idx < q.length()) {
						sb.append('\"');
						idx++;
					} else
						throw new SQLSyntaxErrorException("Quotes are not closed");
					result.add(sb.toString());
					break;
				case '<':
				case '=':
				case '>':
					while (idx < q.length() && cms.contains(q.charAt(idx))) {
						sb.append(q.charAt(idx++));
					}
					if (idx == q.length())
						throw new SQLSyntaxErrorException("Comparison should have right parameter");
					result.add(sb.toString());
					break;
				default:
					while (idx < q.length() && !ads.contains(q.charAt(idx)) && !cms.contains(q.charAt(idx))) {
						sb.append(q.charAt(idx++));
					}
					result.add(sb.toString());
					break;
				}
			}
		}
		return result;
	}

	public static Query parse(String q) throws SQLSyntaxErrorException {

		List<String> tokensList = getTokens(q);

		switch (tokensList.get(0).toLowerCase()) {
		case "select":
			return parseSelect(tokensList);
		case "update":
			return parseUpdate(tokensList);
		case "delete":
			return parseDelete(tokensList);
		case "insert":
			return parseInsert(tokensList);
		default:
			return null;
		}

	}
}
