package wayfair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LongestCommonSubArray {
	
	//only works when there is no duplicate record
	public static String[] allCommonString(String [] a1, String [] a2) {
		int len1 = a1.length;
		int len2  = a2.length;
		
		if(len1 > len2) {
			return allCommonString(a2, a1);
		}
		
		List<String>  res = new ArrayList<>();
		int maxCount = 0;
		
		HashMap<String, Integer> set1 = new HashMap<>();
		for(int i = 0; i < a1.length; i++) {
			set1.put(a1[i], i);
		}
		
		for(int i = 0; i < a2.length; i++) {
			String key =  a2[i];
			if(!set1.containsKey(key)) {
				continue;
			}
			
			ArrayList<String> commonSubArray =  new ArrayList<>();
			commonSubArray.add(key);
			int index2 = i + 1;
			while(index2 < len2) {
				int index = set1.get(key);
				key = a2[index2];
				if(!set1.containsKey(key)|| set1.get(key)!= index+1) {
					break;
				}
				commonSubArray.add(key);
				index2++;
			}
			if(commonSubArray.size() > res.size()) {
				res = new ArrayList(commonSubArray);
			}
		}

		String[] resA = new String[res.size()];
		resA = res.toArray(resA);
		
		return resA;
	}
	
	public static String[] getLongestCommonSubArray(String [] a1, String [] a2) {
		int len1 = a1.length;
		int len2  = a2.length;

		// from previous i in a1 and previous j in a2, how many elements are common
		int[][] dp = new int[len1+1][len2+1];
		
		dp[0][0] = 0;
		
		int maxCount = 0;
		String[] res = null;
		
		for(int i = 1; i <= len1; i++) {
            for(int j = 1; j <= len2; j++) {
                if(a1[i - 1] == a2[j - 1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    if(dp[i][j] > maxCount) {
                    	maxCount = dp[i][j];
                    	res = Arrays.copyOfRange(a1, i - maxCount, i);
                    }
                } 
            }
        } 
		
		
		return res;
	}
	
	public static void main(String[] args) {
		String[] a1 = {"abb1","abc1","aaa","bbb","ccc","ddd"};
		String[] a2 = {"abb","abc","aaa","bbb","bbb","ccc","ddd"};
		
		String [] res = getLongestCommonSubArray(a1, a2);
		
		for(String str: res) {
			System.out.print(str + ",");
		}
		
	}

}
