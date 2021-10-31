package google;

import java.util.ArrayList;

public class StringToInt {

	public int myAtoi(String s) {
		ArrayList<Integer> list = new ArrayList<>();
		boolean start = false;
		char op = '+';
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!start && c == ' ') {
				continue;
			} else if (c == '+' || c == '-') {
				op = c;
			} else if (c >= '0' && c <= '9') {
				list.add((int) (c - '0'));
				start = true;
			} else {
				break;
			}

		}

		if (list.size() > 10 || list.size() == 10 && list.get(0) > 2) {
			if (op == '+') {
				return Integer.MAX_VALUE;
			} else {
				return Integer.MIN_VALUE;
			}
		} else if (list.size() == 10 && list.get(0) == 2) {
			int lower = (int) (Math.pow(2, 31) - 1 - Math.pow(10, 9) * 2);
			int secondToEnd = 0;
			for (int i = 1; i < 10; i++) {
				secondToEnd += Math.pow(10, 9 - i) * list.get(i);
			}
			if (secondToEnd > lower) {
				if (op == '+') {
					return Integer.MAX_VALUE;
				} else {
					return Integer.MIN_VALUE;
				}
			} else {
				if (op == '+') {
					return secondToEnd + (int) Math.pow(10, 9) * 2;
				} else {
					return -1 * (secondToEnd + (int) Math.pow(10, 9) * 2);
				}
			}
		} else {
			int res = 0;
			for (int i = list.size() - 1; i >= 0; i--) {
				res += Math.pow(10, list.size() - 1 - i) * list.get(i);
			}

			if (op == '+') {
				return res;

			} else {
				return -1 * res;
			}

		}
	}

	public int toInt(String str) {
		int i = 0;
		int sign = 1;
		int result = 0;
		if (str.length() == 0)
			return 0;

		// Discard whitespaces in the beginning
		while (i < str.length() && str.charAt(i) == ' ')
			i++;

		// Check if optional sign if it exists
		if (i < str.length() && (str.charAt(i) == '+' || str.charAt(i) == '-'))
			sign = (str.charAt(i++) == '-') ? -1 : 1;

		// Build the result and check for overflow/underflow condition
		while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
			if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > 7)) {
				return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
			result = result * 10 + (str.charAt(i++) - '0');
		}
		return result * sign;
	}

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(500 / 1000);
	}

}
