package stringandnum;

public class LongestCommonSubstring {

	int LongestCommonSubstr(String X, String Y) {

		int m = X.length();
		int n = Y.length();
		int[][] dp = new int[m + 1][n + 1];

		// initialization
		for (int i = 0; i <= m; i++)
			dp[i][0] = 0; // Eg LCS of "abc" & "" = 0
		for (int j = 0; j <= n; j++)
			dp[0][j] = 0; // Eg LCS of "" & "abc" = 0

		int maxLen = 0; // Now finding the max element
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (X.charAt(i - 1) == Y.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
					maxLen = Math.max(maxLen, dp[i][j]);
				}
				else
					dp[i][j] = 0; // ONLY THIS CHANGE
			}
		}
		
		return maxLen;
	}

	public static void main(String[] args) {
		LongestCommonSubstring lc = new LongestCommonSubstring();
		
		String a = "aaabbbbccddd";
		String b = "11bbccd";
		System.out.println(lc.LongestCommonSubstr(a, b));
	}

}
