package stringandnum.hash;
/*
 * 
 * Leetcode 2156 
The hash of a 0-indexed string s of length k, given integers p and m, is computed using the following function:

hash(s, p, m) = (val(s[0]) * p0 + val(s[1]) * p1 + ... + val(s[k-1]) * pk-1) mod m.
Where val(s[i]) represents the index of s[i] in the alphabet from val('a') = 1 to val('z') = 26.

You are given a string s and the integers power, modulo, k, and hashValue. Return sub, the first substring of s of length k such that hash(sub, power, modulo) == hashValue.

The test cases will be generated such that an answer always exists.

A substring is a contiguous non-empty sequence of characters within a string.

 
Example 1:

Input: s = "leetcode", power = 7, modulo = 20, k = 2, hashValue = 0
Output: "ee"
Explanation: The hash of "ee" can be computed to be hash("ee", 7, 20) = (5 * 1 + 5 * 7) mod 20 = 40 mod 20 = 0. 
"ee" is the first substring of length 2 with hashValue 0. Hence, we return "ee".
 */
public class SubStrHash {
	public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        int n = s.length();
        long base = 1;
        long hash = 0;
        for(int i = n - 1; i >= n - k; i--) {
            base = (base * power) % modulo;
            hash = (hash * power + getNum(s, i)) % modulo;
        }
        
        long[] subHash = new long[n - k + 1];
        subHash[n - k] = hash;
        
        
        for(int i = n - k - 1; i >= 0; i--) {
        	// h = h * power
        	// h = h - tail * power^k   -> power^k现在已经转成base
        	// h = h + head
//            hash = subHash[i + 1] * power;
//            hash = hash - getNum(s, i + k) * base;
//            hash = hash + getNum(s, i);
//            hash = ((hash % modulo) + modulo) % modulo;
//            subHash[i] = hash;
            
            subHash[i] = ((subHash[i + 1] * power - getNum(s, i + k) * base + getNum(s, i)) % modulo + modulo)% modulo;
        }
        
        for(int i = 0; i <= n - k; i++) {
        	if(subHash[i] == hashValue) {
        		return s.substring(i, i + k);
        	}
        }
        
        return s.substring(0, k);
    }
    
    private int getNum(String s, int idx){
        return (int)(s.charAt(idx) - 'a' + 1);
    }
    
    public static void main(String[] args) {
//    	Input: s = "fbxzaad", power = 31, modulo = 100, k = 3, hashValue = 32
//    			Output: "fbx"
//    			Explanation: The hash of "fbx" can be computed to be hash("fbx", 31, 100) = (6 * 1 + 2 * 31 + 24 * 312) mod 100 = 23132 mod 100 = 32. 
//    			The hash of "bxz" can be computed to be hash("bxz", 31, 100) = (2 * 1 + 24 * 31 + 26 * 312) mod 100 = 25732 mod 100 = 32. 
//    			"fbx" is the first substring of length 3 with hashValue 32. Hence, we return "fbx".
//    			Note that "bxz" also has a hash of 32 but it appears later than "fbx".
    	SubStrHash sh = new SubStrHash();
    	
    	String res = sh.subStrHash("badcba", 7, 10, 2, 9);
    	System.out.println(res);
	}
}
