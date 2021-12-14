package stringandarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.



Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */
public class GroupAnagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        for (String s : strs) {
            char[] content = s.toCharArray();
            Arrays.sort(content);
            String key = new String(content);
            
            if(!ans.containsKey(key)){
                List l = new ArrayList<String>();
                l.add(s);
                ans.put(key, l);
            } else {
                ans.get(key).add(s);
            }
        }
        return new ArrayList(ans.values());
    }
}
