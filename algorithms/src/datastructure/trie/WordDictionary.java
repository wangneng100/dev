package datastructure.trie;

public class WordDictionary {

    TrieNode root= new TrieNode();
    /*
     * @param word: Adds a word into the data structure.
     * @return: nothing
     */
    public void addWord(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()) {
            if(node.children.get(c) == null) {
                node.children.put(c, new TrieNode());
            } 
            node = node.children.get(c);
        }
        node.isWord = true;
        node.word = word;
    }

    /*
     * @param word: A word could contain the dot character '.' to represent any one letter.
     * @return: if the word is in the data structure.
     */
    public boolean search(String word) {
        return dfs(root, word, 0);
    }

    public boolean dfs(TrieNode node, String word, int index) {

        if(index == word.length()) {
            return node.isWord;
        }

        char c = word.charAt(index);
        if(c == '.') {
            for(char child : node.children.keySet()) {
                if(dfs(node.children.get(child), word, index + 1)){
                    return true;
                }
            }
            return false;
        }

        if(node.children.containsKey(c)) {
            return dfs(node.children.get(c), word, index+1);
        }
        return false;
    }
}