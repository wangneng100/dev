package companys.wayfair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestPalindrome {
	public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        int start = 0, len = 0, longest = 0;
        for (int i = 0; i < s.length(); i++) {
            len = findLongestPalindromeFrom(s, i, i);
            if (len > longest) {
                longest = len;
                start = i - len / 2;
            }
            
            len = findLongestPalindromeFrom(s, i, i + 1);
            if (len > longest) {
                longest = len;
                start = i - len / 2 + 1;
            }
        }
        
        return s.substring(start, start + longest);
    }
    
    private int findLongestPalindromeFrom(String s, int left, int right) {
        int len = 0;
        while (left >= 0 && right < s.length()) {
            while(left >= 0 && !Character.isAlphabetic(s.charAt(left))) {
                left--;
            }
            while(right < s.length() && !Character.isAlphabetic(s.charAt(right))) {
                right++;
            }
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            len += left == right ? 1 : 2;
            left--;
            right++;
        }
        
        return len;
    }

    public String longestPalindrome_dp(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        
        int longest = 1, start = 0;
        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < n - 1; i++) {
            isPalindrome[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            if (isPalindrome[i][i + 1]) {
                start = i;
                longest = 2;
            }
        }
        
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                isPalindrome[i][j] = isPalindrome[i + 1][j - 1] && 
                    s.charAt(i) == s.charAt(j);
                    
                if (isPalindrome[i][j] && j - i + 1 > longest) {
                    start = i;
                    longest = j - i + 1;
                }
            }
        }
        
        return s.substring(start, start + longest);
    }


    public List<String> getLongestPalindromes(String s){
        List<String> res =  new ArrayList<>();
        if(s.length() <= 1) {
            return new ArrayList<>(Arrays.asList(s));
        }

        int max = 0;
        int n = s.length();
        char chs[] = s.toCharArray();

        int [][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
            dp[i][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            if(Character.isAlphabetic(chs[i - 1]) && Character.isAlphabetic(chs[i])) {
                dp[i-1][i] = isEqual(chs[i-1],chs[i]) ? 2 : -1;
            } else {
                dp[i-1][i] = 2;
            }
        }

        // for (int j = 2; j < n; j++) {
        //     for (int i = 0; i < n - j; i++) {
        //         if()
        //         if(Character.isAlphabetic(chs[i]) && Character.isAlphabetic(chs[i + j])) {
        //             dp[i][i + j] = 
        //         }
                
        //     }
        // }
        return res;
    }

    public boolean isPalindrome(String s){
        if(s == null || s.length() == 0) {
            return false;
        }
        int l = 0, r = s.length() -1;

        while(l < r) {
            
            while(l < r && !Character.isAlphabetic(s.charAt(l))){
                l++;
            }

            while(l < r && !Character.isAlphabetic(s.charAt(r))){
                r--;
            }

            if(!isEqual(s.charAt(l), s.charAt(r))) {
                return false;
            }
            l++;
            r--;
        }

        return true;
    }

    private boolean isEqual(char c1, char c2){
        return Character.toLowerCase(c1) == Character.toLowerCase(c2);
    }

    private boolean isPalindrome1(String s){
        if(s == null || s.length() == 0) {
            return false;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.reverse();
        return s.equalsIgnoreCase(sb.toString());
    }

    public List<String> getLongestPalindrome(String s) {
        int len = s.length();
        // max palindrome length
        int maxPLen = 1; 
        List <String> res = new ArrayList <>();

        if(len < 2) {
            res.add(s);
            return res;
        }

        int left = 0;
        while(left < len) {
        	while(left < len && !Character.isLetterOrDigit(s.charAt(left))) {
        		left++;
        	}
            //handle odd palindrome
            Result r = expand(s, left, left);
            maxPLen =  processResult(s, r, maxPLen, res);
            
            if(left == len - 1) break;
            int right = left + 1;
            while(right < len && !Character.isLetterOrDigit(s.charAt(right))) {
            	right++;
        	}
            //handle even palindrome
            r = expand(s, left, right);
            maxPLen =  processResult(s, r, maxPLen, res);
            
            left = right;
        }
        // for(int i = 0; i < len - 1; i++) {
        //     Result r = expand(s, i, i);
        //     maxPLen =  processResult(s, r, maxPLen, res);
            
        //     r = expand(s, i, i + 1);
        //     maxPLen =  processResult(s, r, maxPLen, res);
        // }
        return res;
    }

    // add result to res list and return maxPLen
    private int processResult(String s, Result r, int maxPLen, List<String> res){

        if (r.palindromeLen > maxPLen) { //比较的是回文的长度不是子串的长度
            maxPLen = r.palindromeLen;
            res.clear();
            res.add(s.substring(r.left, r.right + 1));
        } else if (r.palindromeLen == maxPLen) {
            res.add(s.substring(r.left, r.right + 1));
        }

        return maxPLen;
    }

    private Result expand(String s, int left, int right) {
        int palindromeLen = 0;

        while (left >= 0 && right <= s.length() - 1) {
            if (Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right))) {
                palindromeLen = left == right? 1 : palindromeLen + 2;
                left--;
                right++;
            } else if (!Character.isLetterOrDigit(s.charAt(left))) {
                left--;
            } else if (!Character.isLetterOrDigit(s.charAt(right))) {
                right++;
            } else {
                break;
            }
        }
        
        if(left == -1 && right <= s.length() - 1){
            //both valid but not equal
        	if(Character.toLowerCase(s.charAt(left+1)) == Character.toLowerCase(s.charAt(right -1))
        			||!Character.isLetterOrDigit(s.charAt(left + 1)) && !Character.isLetterOrDigit(s.charAt(right - 1))) {
        		while(right <= s.length() - 1 && !Character.isLetterOrDigit(s.charAt(right))) {
        			right++;
        		}
        	}
        }
        
        if(right == s.length() && left >= 0){
            //both valid but not equal
        	if(Character.toLowerCase(s.charAt(left+1)) == Character.toLowerCase(s.charAt(right -1))
        			||!Character.isLetterOrDigit(s.charAt(left + 1)) && !Character.isLetterOrDigit(s.charAt(right - 1))) {
        		while(left >=0 && !Character.isLetterOrDigit(s.charAt(left))) {
        			left--;
        		}
        	}
        }
        
        return new Result(left + 1, right - 1, palindromeLen);
    }


    public static void main(String [] args) {
        // you can write to stdout for debugging purposes, e.g.
        LongestPalindrome so = new LongestPalindrome();

        String s1 = "A man, a plan, a canal: Panama!";
        String s2 = "Eva - can I see bees in a cave?";
        String s3 = "Racecars racing";

        // System.out.println(so.isPalindrome(s1));
        // System.out.println(so.isPalindrome(s2));
        // System.out.println(so.longestPalindrome(s3));
// assertGetLongestPalindromes("Aaaaa", ["Aaaaa"]);
// assertGetLongestPalindromes("A racecar", ["racecar"]);
// assertGetLongestPalindromes("No lemon no melons", ["No lemon no melon"]);
// assertGetLongestPalindromes("", []);
// assertGetLongestPalindromes("Racecars racing", ["Racecar", "cars rac"]);
// assertGetLongestPalindromes("abcAb", ["a", "b", "c", "A"]);
        LongestPalindrome solution = new LongestPalindrome();

//        String s = "Racecars racing";
//        String s = "abb!!ad";
       String s ="!!abba?cddc";
        // String s = "!!aa!cd?aa?";
//        String s = "a";
//        String s = "No lemon no melons";
        // List<String> ret = solution.getLongestPalindrome("No lemon no melons");
        List<String> res = solution.getLongestPalindrome(s);
        System.out.println(res);


        System.out.println("This is a debug message");
    }
}

class Result{
    int left;
    int right;
    int palindromeLen;

    public Result(int left, int right, int palindromeLen) {
        this.left = left;
        this.right = right;
        this.palindromeLen = palindromeLen;
    }
}
