package stringandnum;

import java.util.HashMap;
import java.util.Map;

public class NeedleInHaystack {
	
	public boolean checkInclusion1(String s1, String s2) {
		if (s1 == null || s2 == null) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s1.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int cnt = map.size();
        
        int start = 0, end = 0;
        while (end < s2.length()) {
            char chE = s2.charAt(end);
            if (map.containsKey(chE)) {
                map.put(chE, map.get(chE) - 1);
                if (map.get(chE) == 0) {
                    cnt--;
                }
            }
            end++;
            
            while (cnt == 0) {
                if (end - start == s1.length()) {
                    return true;
                }
                
                char chS = s2.charAt(start);
                if (map.containsKey(chS)) {
                    map.put(chS, map.get(chS) + 1);
                    if (map.get(chS) > 0) {
                        cnt++;
                    }
                }
                start++;
            }
        }
        
        return false;
    }
	
	public static void checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) return;
        
        int[] count = new int[26];
        for (int i = 0; i < len1; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        if (allZero(count)) System.out.println(s2.substring(0,len1));
        
        for (int i = len1; i < len2; i++) {
            count[s2.charAt(i) - 'a']--;
            count[s2.charAt(i - len1) - 'a']++;
            // len1=1 i=3
            if (allZero(count)) System.out.println(s2.substring(i-len1 + 1,i + 1));
        }
        
    }
    
    private static boolean allZero(int[] count) {
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) return false;
        }
        return true;
    }
    
    
    public static void main(String[] args) {
    	checkInclusion("abc","aacabbbcannabc");
	}

}
