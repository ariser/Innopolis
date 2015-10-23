package database;

import database.service.ConditionType;

/**
 * 
 * Class representing condition in a query
 * Example: Column1 > "431"
 * means COLUMN == "Column1", CONDITION == MORE, PARAMS = ["431"]
 * 
 * Example: Column2 between ("431", "zaq")
 * means COLUMN == "Column2", CONDITION == BETWEEN, PARAMS = ["431", "zaq"]
 */
@SuppressWarnings("rawtypes")
public class Condition {
	public String COLUMN;
	public ConditionType CONDITION;
	public Comparable[] PARAMS;

	public Condition(ConditionType ct, String col, Comparable... params) {
		CONDITION = ct;
		PARAMS = params;
		COLUMN = col;
	}

	/**
	 * 
	 * @param r - Row we check for this condition
	 * @return whether row fits condition or not
	 */
	@SuppressWarnings("unchecked")
	public boolean match(Row r) {
		Comparable val = r.get(COLUMN);
		switch (CONDITION) {
		case EQ:
			return val.compareTo(PARAMS[0]) == 0;
		case LESS:
			return val.compareTo(PARAMS[0]) < 0;
		case LESS_EQ:
			return val.compareTo(PARAMS[0]) <= 0;
		case MORE:
			return val.compareTo(PARAMS[0]) > 0;
		case MORE_EQ:
			return val.compareTo(PARAMS[0]) >= 0;
		case BETWEEN:
			int more = val.compareTo(PARAMS[0]);
			int less = val.compareTo(PARAMS[1]);
			
			return more > 0 && less < 0;
		default:
			return false;
		}
	}
}