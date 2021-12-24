package dfs.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
Description
Given a string, find all permutations of it without duplicates.
*/
public class StringPermutationII {
    public List<String> stringPermutation2(String str) {
        // write your code here
        List<String> result = new ArrayList<>();
        boolean[] visitedNode = new boolean[str.length()];
        char[] strArray = str.toCharArray();
        Arrays.sort(strArray);
        dfs(0, new String(strArray), result, new char[str.length()], visitedNode);
        return result;
    }

    private void dfs(int index, String str, List<String> result, char[] path, boolean[] visitedNode) {
        if (index == str.length()) {
            result.add(new String(path));
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            if (visitedNode[i]) {
                continue;
            }
            if (i > 0 && str.charAt(i - 1) == str.charAt(i) && !visitedNode[i - 1]) {
                continue;
            }
            path[index] = str.charAt(i);
            visitedNode[i] = true;
            dfs(index + 1, str, result, path, visitedNode);
            visitedNode[i] = false;
        }
    }
}
