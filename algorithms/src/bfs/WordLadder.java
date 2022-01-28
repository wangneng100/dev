package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        
        Map<String, LinkedList<String>> word2NextWords = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();

        //bfs find the shortest distance and build word to nextWords map
        bfs(start, end, dict, word2NextWords, distance);

        List<List<String>> res =  new ArrayList<>();
        LinkedList<String> path = new LinkedList<>();
        path.add(start);

        //dfs get all the shortest path 
        dfs(res, dict, distance, word2NextWords, path, end, 0);

        return res;
    }

    private void bfs(String start, String end, Set<String> dict, Map<String, LinkedList<String>> word2NextWords, Map<String, Integer> distance){
        dict.add(end);
        for(String s: dict) {
            word2NextWords.put(s, new LinkedList<>());
        }
        word2NextWords.put(start, new LinkedList<>());

        Queue<String> q = new ArrayDeque<>();
        q.offer(start);
        distance.put(start, 0);

        boolean found = false;
        while(!q.isEmpty() && !found) {
            int size = q.size();
            for(int i = 0; i< size; i++) {
                String word = q.poll();
                
                List<String> nextWords = getNextWords(word, dict);
                for (String next : nextWords) {
                    word2NextWords.get(word).add(next);
                    if(word.equals(end)) {
                        found = true;
                    }
                    if(!distance.containsKey(next)) {
                        
                        q.offer(next);
                        distance.put(next, distance.get(word) + 1);
                    }
                }
            }
        }
    }

    private void dfs(List<List<String>> res, Set<String> dict, Map<String, Integer> distance, Map<String, LinkedList<String>> word2NextWords, LinkedList<String> path, String target, int step) {
        if(path.getLast().equals(target)) {
            List<String> newRes = new LinkedList<>(path);
            res.add(newRes);
            return;
        }
   
        String word = path.getLast();        
        for (String next : word2NextWords.get(word)) {
            if(distance.get(next) == step + 1) {
                path.add(next);
                dfs(res, dict, distance, word2NextWords, path, target, step + 1);
                path.removeLast();
            }
        }
    }

    private List<String> getNextWords(String word, Set<String> dict){
        List<String> res = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            char[] chars = word.toCharArray();
            for (char ch = 'a'; ch <= 'z'; ch++) {                
                if(word.charAt(i) == ch)
                    continue;
                chars[i] = ch;
                String newWord = new String(chars);
                
                if(dict.contains(newWord)) {
                    res.add(newWord);
                }
            }
        }
        return res;
    }
}
