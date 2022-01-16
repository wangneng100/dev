package companys.wayfair;

/*
// Basic Test
assertIsPalindrome("", false);
assertIsPalindrome("lotion", false);
assertIsPalindrome("racecar", true);
 
// Case and space tests
assertIsPalindrome("Racecar", true);
assertIsPalindrome("Step on no pets", true);
assertIsPalindrome("No lemon no melons", false);
 
// Special character tests
assertIsPalindrome("Eva - can I see bees in a cave?", true);
assertIsPalindrome("A man, a plan, a canal: Panama!", true);


assertGetLongestPalindromes("Aaaaa", ["Aaaaa"]);
assertGetLongestPalindromes("A racecar", ["racecar"]);
assertGetLongestPalindromes("No lemon no melons", ["No lemon no melon"]);
assertGetLongestPalindromes("", []);
assertGetLongestPalindromes("Racecars racing", ["Racecar", "cars rac"]);
assertGetLongestPalindromes("abcAb", ["a", "b", "c", "A"]);

*/


// you can also use imports, for example:
import java.util.*;

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


    public String[] getLongestPalindromes(String s){
        Stack<String> res = new Stack<>();
        if(s == null || s.length() == 0) {
            return new String[]{};
        }

        int max = 0;
        
        for(int i = 0; i < s.length(); i++) {
            for(int j = i + 1; j < s.length(); j++) {
                if(isPalindrome(s.substring(i,j))) {
                    if(j - i + 1 >= max) {
                        max = j - i + 1;
                        addResult(s.substring(i,j), res);
                    }
                }
            }
        }

        
        // if dp[i][j] = true, s[i-1] == s[j+1], dp[i-1][j+1] = true
        // for(int j = s.length() - 1; j >=0 ; j--) {
        //     for(int i = j - 1; i >= 0; i--) {
        //         if(dp[i + 1][j - 1] == true && s.charAt(i) == s.charAt(j)) {
        //             dp[i][j] = true;
        //             if(max < j - i + 1) {
        //                 max = j - i + 1;
        //             }
        //             result = s.substring(i,j+1);
        //         }
        //     }
        // }
        String[] finalRes = new String[res.size()];
        int idx = 0;
        while(!res.isEmpty()){
            finalRes[idx++] = res.pop();
        }
        return finalRes;
    }

    private void addResult(String result, Stack<String> res){

        while(!res.isEmpty() && result.length() > res.peek().length()) {
            res.pop();
        }
        res.push(result);

    }

    public boolean isPalindrome(String s){
        if(s == null || s.length() == 0) {
            return false;
        }
        int l = 0, r = s.length() -1;

        while(l < r) {
            
            while(l < r && !isValid(s.charAt(l))){
                l++;
            }

            while(l < r && !isValid(s.charAt(r))){
                r--;
            }

            if(Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            }
            l++;
            r--;
        }

        return true;
    }

    private boolean isValid(char c){
        return Character.isAlphabetic(c);
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

    public static void main(String [] args) {
        // you can write to stdout for debugging purposes, e.g.
        LongestPalindrome so = new LongestPalindrome();

        String s1 = "A man, a plan, a canal: Panama!";
        String s2 = "Eva - can I see bees in a cave?";
        String s3 = "Racecars racing";

        System.out.println(so.isPalindrome(s1));
        System.out.println(so.isPalindrome(s2));
        System.out.println(so.longestPalindrome(s3));

        System.out.println("This is a debug message");
    }
}

