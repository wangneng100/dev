package binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LongestPalindrome {
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

    public List<String> longestPalindrome(String s) {
        // write your code here
        if(s == null || s.length() == 0) {
            return new ArrayList<>(Arrays.asList(s));
        }

        
        LinkedList<String> res = new LinkedList<>();
        char[] chs = s.toCharArray();
        int n = chs.length;

        int[] maxXY = {0,0};
        int i = 0;
        while(i < n) {
            if(!isValid(chs[i++])) continue;
            
            int j = i + 1;
            while(j < n &&!isValid(chs[j])) {
                j++;
            }
            int[] xy = findLongestPalindrome(chs, i, j - 1);
            if(xy[1] - xy[0] > maxXY[1] - maxXY[0]) {
                maxXY = xy;
                addNewString(res, s.substring(maxXY[0], maxXY[1] + 1));
            }
            
            
            if(j < n && chs[i] == chs[j]) {
                xy = findLongestPalindrome(chs, i, j);
                if(xy[1] - xy[0] > maxXY[1] - maxXY[0]) {
                    maxXY = xy;
                    addNewString(res, s.substring(maxXY[0], maxXY[1] + 1));
                }
            }
            
            i = j;
        }
        return res;
    }

    private void addNewString(LinkedList<String> res, String s){
        res.addLast(s);
        while(res.getFirst().length() < s.length()) {
            res.removeFirst();
        }
    }


    private int[] findLongestPalindrome(char[] chs, int left, int right) {
        System.out.println("Before left:" + chs[left] + " right:" + chs[right]);
        while(left >0 && right < chs.length - 1) {

            while(left > 0 &&!isValid(chs[left])) {
                left--;
            }
            System.out.println("left:" + left);
            while(right < chs.length - 1 && !isValid(chs[left])) {
                right++;
            }
            System.out.println("right:" + left);
            if(chs[left] != chs[right]) {
                left++;
                right--;
                break;
            }
            left--;
            right++;
        }
        if(left < 0) {
            left = 0;
        }
        if(right >= chs.length) {
            right = chs.length - 1;
        }
        System.out.println("After left:" + chs[left] + " right:" + chs[right]);
        return new int[]{left, right};
    }

    public boolean isPalindrome(String s) {
        int [] pair = findLongestPalindrome(s.toCharArray(), 0, s.length() - 1);
        return pair[1] - pair[0] == s.length() - 1;
    }

    private boolean isValid(char c){
        return Character.isAlphabetic(c)|| Character.isDigit(c);
    }

    private boolean isEqual(char c1, char c2){
        return Character.toLowerCase(c1) == Character.toLowerCase(c2);
    }

    public static void main(String [] args) {
        // you can write to stdout for debugging purposes, e.g.
        LongestPalindrome so = new LongestPalindrome();

        String s1 = "A man, a plan, a canal: Panama!";
        String s2 = "Eva - can I see bees in a cave?";
        String s3 = "Racecars racing";

        // System.out.println(so.isPalindrome(s1));
        // System.out.println(so.isPalindrome(s2));
        System.out.println(so.longestPalindrome(s3).size());

        System.out.println("This is a debug message");
    }
}
