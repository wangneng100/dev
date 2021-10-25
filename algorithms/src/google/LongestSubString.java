package google;

import java.util.HashSet;
import java.util.Set;

public class LongestSubString {
	
	//best solution, set char value as index, string index as value
	public int lengthOfLongestSubstring(String s) {
        
		Integer[] chars = new Integer[128];
        
        int left = 0;
        int right = 0;
        
        int max = 0;
        
        while (right < s.length()) {
            char r = s.charAt(right);
            
            Integer index = chars[r];
            
            if(index != null && index >= left && index < right) {
                left = index +1;
            }
            
            max = Math.max(max, right-left+1);
            
            chars[r] = right;
            right++;
        }
        
        return max;
    }
	
    public static int lengthOfLongestSubstring1(String s) {
        
        Set<Character> subSet = new HashSet<Character>();
        
        int max = 0;
        int left = 0;
        int right = 0;
        
        while(right < s.length()){
            char c = s.charAt(right);
            
            if(subSet.contains(c)){
                max = Math.max(max, subSet.size());
                subSet.clear();
                while(s.charAt(left) != c){
                    left++;
                }
                left++;
                right = left;
            } else {
                subSet.add(c);
                right++;
            }
            
            
        }
        
        return Math.max(max, subSet.size());
        
    }
	
	public static void main(String[] args) {
		String s = "abcbdfgabc";
		System.out.println(lengthOfLongestSubstring1(s));
	}

}
