package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Description
Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.

A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
*/
public class ConcatenatedWord {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        // write your code here
        Set<String> dict = new HashSet<String>();// using hash for acceleration
        for(int i = 0; i < words.length; i++){
            dict.add(words[i]);
        }
        List<String> res = new ArrayList<String>();
        for(String word:words){
            dict.remove(word);
            if(isValid(word, dict)) {
                res.add(word);
            }
            dict.add(word);
        }
        return res;
    }

    private boolean isValid(String word, Set<String> dict){
        int n = word.length();
        boolean[] dp = new boolean[n+1];// store whether w.substr(0, i) can be formed by existing words
        Arrays.fill(dp,false);
        dp[0] = true;// empty string is always valid

        //dp[j] = dp[i] && w.substr(i,j) (j > i)
        for(int i = 0; i < n; i++){
            if(!dp[i]) continue;// cannot start from here

            // check whether w.substr(i, j) is in the dict
            for(int j = i+1; j <= n; j++){
                if(dict.contains(word.substring(i,j))){
                    dp[j]=true;
                }
            }
            if(dp[n]){
                return true;
            }
        }
        return false;
    }
}
