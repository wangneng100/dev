package stringandnum;

import java.util.ArrayList;

public class MinimumWindowSubstring {
	
	public String minWindow(String s, String t) {

    int[] cc = new int[128];
    for (char c : t.toCharArray()) {
        cc[c]++;
    }
    
    int startIndex = 0;
    int minLen = s.length()+1;
    int target = t.length();
    
    int left = 0;
    int right = 0;
    
    while(right< s.length()){
        char c = s.charAt(right);
        
        if(cc[c]-- > 0){
            target--;
        }
        
        while(target==0){
            if(right-left+1 < minLen){
                startIndex = left;
                minLen = right-left+1;
            }
            if (++cc[s.charAt(left++)] > 0) {
                target++;
            }
            
        }
        
        right++;
    }
        
    if(startIndex+minLen > s.length()) return "";
    return s.substring(startIndex, startIndex+minLen);
        
}
	
	public static void main(String[] args) {
		ArrayList<Character> l = new ArrayList<>();
		l.add('a');
		String s = "02";
		System.out.println(l.remove(new Character('a')));
		System.out.println(l.remove(new Character('b')));
		System.out.println(s.substring(0, 2));
	}

}
