package stringandnum.abbr;
/*
*https://www.lintcode.com/problem/637/description
637 · Valid Word Abbreviation
*/
public class ValidWordAbbreviation {
    public boolean validWordAbbreviation1(String word, String abbr) {
        char [] wc = word.toCharArray();
        char [] ac = abbr.toCharArray();

        int wi = 0, ai = 0;
        while(wi < wc.length && ai < ac.length) {
            if(Character.isDigit(ac[ai])) {
                if(ac[ai] - '0' == 0) {
                    return false;
                }
                int num = 0;
                while(ai < ac.length && Character.isDigit(ac[ai])){
                    num = num*10 + (ac[ai++] - '0');
                }
                wi += num;
            } else {
                if(wc[wi++]!=ac[ai++]){
                    return false;
                }
            }
        }

        return wi == wc.length&& ai== ac.length;
    }

    public boolean validWordAbbreviation2(String word, String abbr) {
        // write your code here
        int i = 0, j =0;
        char[] s = word.toCharArray();
        char[] t = abbr.toCharArray();
        
        while (i < s.length && j < t.length) {
            if (Character.isDigit(t[j])) {
                if (t[j] == '0') {
                    return false;
                }
                int val = 0;
                while (j < t.length && Character.isDigit(t[j])) {
                    val = val * 10 + t[j] - '0';
                    j++;
                }
                i += val;
            } else {
                if (s[i++] != t[j++]) {
                    return false;
                }
            }
        }
        return i == s.length && j == t.length;
    }

    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;

        while(i < word.length() && j < abbr.length()) {
            if(word.charAt(i) == abbr.charAt(j)){
                i++;
                j++;
            } else if(Character.isDigit(abbr.charAt(j)) && abbr.charAt(j)!='0'){
                
                int num = 0;
                while(j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                    num = num * 10 + (abbr.charAt(j) - '0');
                    j++;
                }
                i+=num;
            } else {
                return false;
            }

        }

        return i == word.length() && j == abbr.length();
    }
    
}
