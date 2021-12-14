package twopointers;

import java.util.HashMap;
import java.util.Map;

public class LongestNoRepeatSubString {
    public int lengthOfLongestSubstring(String s) {
        return lengthOfLongestSubstring_optimized(s);
    }
    
    public int lengthOfLongestSubstring_intarray(String s) {
        if(s == null){
            return 0;
        }
        
        // use to save the index of the char. charCount[(int)c] = index of c
        Integer [] charCount = new Integer[128];
        
        // double pointer, right go first
        int left = 0, right = 0;
        int max = 0;
        
        while(right < s.length()) {
            char c = s.charAt(right);
            
            // get the index of char c at right point location
            Integer index =  charCount[c];
            
            // if index not null, and index>=left, left jump to index+1
            if(index != null && index >= left) {
                left = index + 1;
            } 
            
            // reset index of char c
            charCount[c] = right;
            
            // compare max and right-left+1 and get the bigger one
            max = Math.max(max, right - left + 1);
            
            // right move one step
            right++;
        }
        
        return max;
    }
    
     public int lengthOfLongestSubstring_hashmap(String s) {
         if(s == null) {
             return 0;
         }
         
         int maxCount = 0;
         Map<Character, Integer> ch2Index = new HashMap<>(); 
         
         int left = 0, right = 0;
         
         while(right < s.length()){
             char c = s.charAt(right);
             if(ch2Index.containsKey(c)) {
                 int index = ch2Index.get(c);
                 
                 if(left <= index) {
                     left = index + 1;
                 }
                 
             }
             maxCount = Math.max(maxCount, right - left + 1);
             ch2Index.put(c, right);
             right++;
         }
         
         // maxCount = Math.max(maxCount, right - left);
         
         return maxCount;
         
     }
    
    public int lengthOfLongestSubstring_optimized(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)) + 1, i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j );
        }
        return ans;
    }
}
