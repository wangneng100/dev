package dfs.combination;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationOfPhoneNumber {
    public static final String[] KEYBOARD = {"","","abc"
                                ,"def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    /**
     * @param digits: A digital string
     * @return: all posible letter combinations
     */
    public List<String> letterCombinations(String digits) {
        

        List<String> res = new ArrayList<>();

        if(digits == null || digits.length() < 1) {
            return res;
        }

        char[] charA = digits.toCharArray();

        char[] comb = new char[charA.length];

        dfs(0, charA, comb, res);

        return res;
    }

    private void dfs(int index, char[] charA, char[] comb, List<String> res) {
        if(index == charA.length) {
            String s = new String(comb);
            res.add(s);
            return;
        }

        int digit = charA[index] - '0';
        for (int i = 0; i < KEYBOARD[digit].length(); i++) {
            comb[index] = KEYBOARD[digit].charAt(i);
            dfs(index + 1, charA, comb, res);
        }
    }
}
