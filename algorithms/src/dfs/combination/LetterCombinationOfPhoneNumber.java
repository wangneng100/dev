package dfs.combination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // use map and StringBuilder
        // Map<Character, char[]> map = new HashMap<Character, char[]>();
        // map.put('2', new char[] { 'a', 'b', 'c' });
        // map.put('3', new char[] { 'd', 'e', 'f' });
        // map.put('4', new char[] { 'g', 'h', 'i' });
        // map.put('5', new char[] { 'j', 'k', 'l' });
        // map.put('6', new char[] { 'm', 'n', 'o' });
        // map.put('7', new char[] { 'p', 'q', 'r', 's' });
        // map.put('8', new char[] { 't', 'u', 'v'});
        // map.put('9', new char[] { 'w', 'x', 'y', 'z' });

        // StringBuilder sb = new StringBuilder();
        // helper(map, digits, sb, res);

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

    private void helper(Map<Character, char[]> map, String digits, 
        StringBuilder sb, List<String> result) {
        if (sb.length() == digits.length()) {
            result.add(sb.toString());
            return;
        }

        for (char c : map.get(digits.charAt(sb.length()))) {
            sb.append(c);
            helper(map, digits, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
