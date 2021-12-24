package stringandnum;

public class LongestNoneRepeatSubString {
	
	public int lengthOfLongestSubstring(String s) {
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
	
	public static String longestSubstring(String s) {
        if(s == null){
            return null;
        }
        
        // use to save the index of the char. charCount[(int)c] = index of c
        Integer [] charCount = new Integer[128];
        
        // double pointer, right go first
        int left = 0, right = 0;
        String max = "";
        
        while(right < s.length()) {
            char c = s.charAt(right);
            
            // get the index of char c at right point location
            Integer index =  charCount[c];
            
            // if index not null, and index>=left, left jump to index+1
            if(index != null && index >= left) {
//            	String tmp = s.substring(left, right - left);
//            	
//            	if(right - left > max.length()) {
//            		max = s.substring(left, right - left);
//            	}
                left = index + 1;
            }
            
            // reset index of char c
            charCount[c] = right;
            
            // compare max and right-left+1 and get the bigger one
            if(right - left + 1 >= max.length())
            	max = s.substring(left, right + 1);
            
            // right move one step
            right++;
        }
        
        return max;
    }
	
	static int indexOf(char[] source, char[] target) {
		int sourceCount = source.length;
		int targetCount = target.length;
		int fromIndex = 0;
        if (fromIndex >= sourceCount) {
            return (targetCount == 0 ? sourceCount : -1);
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (targetCount == 0) {
            return fromIndex;
        }

        char first = target[0];
        int max = (sourceCount - targetCount);

        for (int i = fromIndex; i <= max; i++) {
            /* Look for first character. */
            if (source[i] != first) {
                while (++i <= max && source[i] != first);
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = 1; j < end && source[j]
                        == target[k]; j++, k++);

                if (j == end) {
                    /* Found whole string. */
                    return i;
                }
            }
        }
        return -1;
    }
	
	public static void main(String[] args) {
		String s = "abc";
		System.out.println(s.indexOf("bc"));
		System.out.println(longestSubstring(s));
	}

}
