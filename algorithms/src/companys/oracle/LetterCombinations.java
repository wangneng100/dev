package companys.oracle;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinations {
    static final String[] numpad = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        
        char[] chs = new char[digits.length()];
        dfs(digits, 0, chs, res);
        
        return res;
    }
    
    private void dfs(String digits, int idx, char[] chs, List<String> res){
        int n = digits.length();
        if(idx == n){
            res.add(new String(chs));
            return;
        }
        

        int num = digits.charAt(idx) - '0';
        String opts = numpad[num];

        for(int j = 0; j< opts.length(); j++) {
            chs[idx] = opts.charAt(j);
            dfs(digits, idx + 1, chs, res);
        }
        
    }
}
