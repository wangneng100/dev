package stringandnum.hash;

public class StringReplace {
    static final int power = 33;
    static final int mod = Integer.MAX_VALUE/33;
    /**
     * @param a: The A array
     * @param b: The B array
     * @param s: The S string
     * @return: The answer
     */
    public String stringReplace(String[] a, String[] b, String s) {
        int n = s.length();
        int m = a.length;
        long[] hashA = new long[a.length];
        int maxLen = 0;

        for (int i = 0; i < m; i++) {
            if(a[i].length() > maxLen) {
                maxLen = a[i].length();
            }
            hashA[i] = getHash(a[i]);
        }

        StringBuilder sb = new StringBuilder();

        long hash = 0;
        int lastIdx = 0;
        int currIdx = 0;
        int lastFoundInA = -1;
        
        while(currIdx < n) {
            
            hash = (hash * power + (1 + s.charAt(currIdx) - 'a'))%mod;
            int findIdx = findMatchIdxInA(hash, hashA); 
            if(findIdx == -1) {
                if(lastFoundInA != -1) {
                    sb.append(b[lastFoundInA]);
                    hash = 1 + s.charAt(currIdx) - 'a';
                    lastFoundInA = -1;
                }
            } else {
                lastFoundInA = findIdx;
            }

            if(currIdx - sb.length() >= maxLen) {
                sb.append(s.substring(sb.length(), currIdx));
                hash = 1+ s.charAt(currIdx) - 'a';
            }

            currIdx++;
        }
        if(lastFoundInA != -1) {
            sb.append(b[lastFoundInA]);
        }

        if(sb.length() < n) {
            sb.append(s.substring(sb.length(),n));
        }
        return sb.toString();
    }

    private long getHash(String s) {
        long hash = 0;
        for (char c : s.toCharArray()) {
            hash = (hash * power + 1+ (c - 'a'))%mod;
        }
        return hash;
    }

    private int findMatchIdxInA(long hash, long[] hashA) {
        for (int i = 0; i < hashA.length; i++) {
            if(hashA[i] == hash) {
                return i;
            }  
        }

        return -1;
    }
    
    public static void main(String[] args) {
    	StringReplace so = new StringReplace();
    	String[] a = {"ab","aba"};
    	String[] b = {"cc","ccc"};
    	String s = so.stringReplace(a, b, "ddaba");
    	System.out.println(s);
	}
}
