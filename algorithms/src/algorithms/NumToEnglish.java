package algorithms;

public class NumToEnglish {

	public String numberToWords(int num) {
		StringBuilder res = new StringBuilder();

		int billionNum = num / 1000000000;
		if (billionNum > 1) {
			res.append(translatUnits(billionNum) + " Billion ");
		}

		int millionNum = (num % 1000000000) / 1000000;
		if (millionNum > 0) {
			res.append(translateThreeNumber(millionNum) + " Million ");
		}

		int thousand = (num % 1000000) / 1000;
		if (millionNum > 0) {
			res.append(translateThreeNumber(thousand) + " Thousand ");
		}

		int last3Digs = num % 1000;
		res.append(translateThreeNumber(last3Digs));

		String result = res.toString();
		if (result.endsWith("")) {
			return result.substring(0, result.length() - 1);
		}

		return result;
	}

	private String translateThreeNumber(int threeDig) {
		int[] threeNum = new int[3];

		threeNum[0] = threeDig / 100;
		threeNum[1] = (threeDig % 100) / 10;
		threeNum[2] = threeDig % 10;

		StringBuilder sb = new StringBuilder();
		if (threeNum[0] != 0) {
			sb.append(translatUnits(threeNum[0]) + " Hundred " + translatTwoNums(threeNum[1], threeNum[2]));
		} else {
			sb.append(translatTwoNums(threeNum[1], threeNum[2]));
		}

		return sb.toString();
	}

	private String translatUnits(int i) {
		switch (i) {
		case 1:
			return "One";
		case 2:
			return "Two";
		case 3:
			return "Three";
		case 4:
			return "Four";
		case 5:
			return "Five";
		case 6:
			return "Six";
		case 7:
			return "Seven";
		case 8:
			return "Eight";
		case 9:
			return "Nine";
		default:
			return "";
		}
	}

	private String translatTwoNums(int tens, int units) {
		switch (tens) {
		case 0:
			return translatUnits(units);
		case 1:
			switch (units) {
			case 0:
				return "Ten";
			case 1:
				return "Eleven";
			case 2:
				return "Twelve";
			case 3:
				return "Thirteen";
			case 4:
				return "Fourteen";
			case 5:
				return "Fifteen";
			case 6:
				return "Sixteen";
			case 7:
				return "Seventeen";
			case 8:
				return "Eighteen";
			case 9:
				return "Nineteen";
			}
		case 2:
			return "Tweenty " + translatUnits(units);
		case 3:
			return "Thirty " + translatUnits(units);
		case 4:
			return "Forty " + translatUnits(units);
		case 5:
			return "Fifty " + translatUnits(units);
		case 6:
			return "Sixty " + translatUnits(units);
		case 7:
			return "Seventy " + translatUnits(units);
		case 8:
			return "Eighty " + translatUnits(units);
		case 9:
			return "Ninety " + translatUnits(units);
		default:
			return "";
		}
	}

}
