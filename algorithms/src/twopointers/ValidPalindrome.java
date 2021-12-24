package twopointers;

import java.util.ArrayList;
import java.util.List;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if(s == null) {
            return false;
        }

        int left =0;
        int right = s.length() - 1;

        while(left < right) {
            while(left < right && !isValid(s.charAt(left))) {
                left++;
            }

            while(left < right && !isValid(s.charAt(right))) {
                right--;
            }

            if(!isEqual(s.charAt(left),s.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isValid(char c){
        return Character.isAlphabetic(c)|| Character.isDigit(c);
    }

    private boolean isEqual(char c1, char c2){
        return Character.toLowerCase(c1) == Character.toLowerCase(c2);
    }

    public boolean isPalindrome1(String s) {
        
        char[] array = s.toCharArray();
        List<Character> validChar = new ArrayList<>();
        for(char c: array) {
            if(isValidChar(c)){
                validChar.add(Character.toLowerCase(c));
            }
        }
        
        int left = 0;
        int right = validChar.size() - 1;
        while(left < right) {
            if(validChar.get(left) != validChar.get(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    private boolean isValidChar(char c){
        if(c>='0' && c<='9' || c >= 'a' && c<='z' || c >= 'A' && c<='Z' ){
            return true;
        }
        return false;
    }

}
