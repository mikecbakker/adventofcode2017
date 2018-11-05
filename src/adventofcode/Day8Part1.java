package adventofcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Day8Part1 {
	static HashMap<String, Integer> register = new HashMap<String, Integer>();

	/**
	 * Main entry point
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		File file = new File("input/Day8_input.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;

		int maxValue = 0;
		while ((line = bufferedReader.readLine()) != null) {

			String[] lineInputs = line.split(" ");
			String operand = lineInputs[0];
			String operator = lineInputs[1];
			Integer delta = Integer.valueOf(lineInputs[2]);
			String conditionalRegister = lineInputs[4];
			String condition = lineInputs[5];
			Integer conditionalValue = Integer.valueOf(lineInputs[6]);

			Integer currentValue = getRegisterValue(operand);
			Integer conditionalCurrentValue = getRegisterValue(conditionalRegister);

			if (exerciseCondition(conditionalCurrentValue, condition, conditionalValue)) {
				if (Operator.INC.equals(operator)) {
					currentValue = currentValue + delta;
				} else {
					currentValue = currentValue - delta;
				}
				if (currentValue > maxValue) {
					maxValue = currentValue;
				}
				register.put(operand, currentValue);
			}
		}

		System.out.println(maxValue);
	}

	public static boolean exerciseCondition(Integer conditionalCurrentValue, String condition,
			Integer conditionalValue) {
		int result = conditionalCurrentValue.compareTo(conditionalValue);
		switch (condition) {
		case Condition.LT:
			return result < 0;
		case Condition.LTE:
			return result <= 0;
		case Condition.GT:
			return result > 0;
		case Condition.GTE:
			return result >= 0;
		case Condition.EQ:
			return result == 0;
		case Condition.NE:
			return result != 0;
		default:
			break;
		}
		return false;
	}

	public static Integer getRegisterValue(String registerName) {
		Integer value = register.get(registerName);
		if (value == null) {
			value = 0;
			register.put(registerName, Integer.valueOf(value));
		}
		return value;
	}

	public static class Operator {
		public static final String INC = "inc";
		public static final String DEC = "dec";

	}

	public static class Condition {
		public static final String LT = "<";
		public static final String LTE = "<=";
		public static final String GT = ">";
		public static final String GTE = ">=";
		public static final String EQ = "==";
		public static final String NE = "!=";
	}
}
