package stringandnum.abbr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://www.lintcode.com/problem/639
// 639 · Word Abbreviation
public class WordAbbreviation {
 /**
     * @param dict: an array of n distinct non-empty strings
     * @return: an array of minimal possible abbreviations for every word
     */
   // if string length<=3, no change
     
   public String[] wordsAbbreviation(String[] dict) {
    Map<String,Set<String>> abbr2OriginalStringSet = new HashMap<>();

    for(String word: dict) {
        String abbr = getAbbr(word);

        if(!abbr2OriginalStringSet.containsKey(abbr)) {
            abbr2OriginalStringSet.put(abbr, new HashSet<>());
        }
        abbr2OriginalStringSet.get(abbr).add(word);
    }

    int n = dict.length;
    String[] res = new String[n];
    
    for (int i = 0; i < n; i++) {
        String abbr = getAbbrWithTrie(dict[i], abbr2OriginalStringSet);
        res[i] = abbr;

    }
    return res;
}

private String getAbbr(String word){
    int n = word.length();
    if(n <= 3) {
        return word;
    }

    return word.substring(0,1) + (n - 2) + word.substring(n-1,n);
}

private String getAbbrWithTrie(String word, Map<String,Set<String>> abbr2OriginalStringSet){
    String abbr = getAbbr(word);
    Set<String> set = abbr2OriginalStringSet.get(abbr);

    // no same abbr
    if(set.size() == 1) {
        return abbr;
    }

    Trie t = new Trie();
    for(String s : set) {
        t.add(s);
    }

    int l = word.length();
    int firstUnique = t.findFirstUniqueLevel(word);
    if(l - firstUnique > 3){
        abbr = word.substring(0,firstUnique) + (l - 1 - firstUnique) + word.substring(l-1,l);
    } else {
        abbr = word;
    }

    return abbr;
}


}

class TrieNode{
Map<Character, TrieNode> children;
int visited;
int level;

public TrieNode(){
    children = new HashMap<>();
    visited = 0;
    level = 0;
}
}

class Trie{
TrieNode root;

public Trie(){
    root = new TrieNode();
}

public void add(String word) {
    TrieNode curr = root;
    for (char c : word.toCharArray()) {
        if(!curr.children.containsKey(c)){
            TrieNode node = new TrieNode();
            node.level = curr.level +1 ;
            curr.children.put(c, node);
        }
        curr = curr.children.get(c);
        curr.visited++;
    }
    // curr.isWord = true;
}

public int findFirstUniqueLevel(String word){
    TrieNode curr = root;
    for (char c : word.toCharArray()) {
        if(!curr.children.containsKey(c)) {
            break;
        }
        curr = curr.children.get(c);
        if(curr.visited == 1) {
            break;
        }
    }
    return curr.level;
}
}
