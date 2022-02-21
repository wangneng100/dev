package stringandnum.hash;

public class FindFirstSubString {
	
	static final int power = 31;
    static final int mod = 26;
	
	public int strStr2(String source, String target) {
        // write your code here
        int n = source.length();
        int m = target.length();
        if(n == 0 || n < m) {
            return -1;
        }

        long targetHash = hashcode(target);
        String base = source.substring(0,m);
        long baseHash = hashcode(base);
        if(targetHash == baseHash && target.equals(base)){
            return 0;
        }

        int seed = 1;
        for(int i = 0; i < m; i++) {
            seed = seed * power % mod;
        }

        for(int i = m; i < n; i++) {
            baseHash = (baseHash * power - (source.charAt(i - m) - 'a') * seed + (source.charAt(i) - 'a'));
            baseHash = (baseHash % mod + mod)% mod;
            if(baseHash == targetHash && target.equals(source.substring(i - m + 1, i + 1))){
            	return i - m + 1;
            }
        }

        return -1;
    }

    private long hashcode(String s){

        long hash = 0;
        for(char c : s.toCharArray()) {
            hash = (hash * power + (c - 'a')) % mod;
        }
        return hash;
    }
    
    public static void main(String[] args) {
    	FindFirstSubString so = new FindFirstSubString();
    	int i = so.strStr2("tartarget", "target");
    	System.out.println(i);
	}

}
