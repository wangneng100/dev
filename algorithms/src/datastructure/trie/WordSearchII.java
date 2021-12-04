package datastructure.trie;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {
	 //搜索方向
    public static final int[] dx = {1, 0, -1, 0};   
    public static final int[] dy = {0, 1, 0, -1};

    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    public List<String> wordSearchII(char[][] board, List<String> words) {
        List<String> res = new ArrayList<>();
        if(board == null|board.length == 0 || board[0].length == 0) {
            return res;
        }

        int r = board.length;
        int c = board[0].length;

        Trie trie = new Trie();
        for(String word : words) {
            trie.insert(word);
        }
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j< board[0].length; j++) {
                search(board, i, j, trie.root, res);
            }
        }
        return res;
    }

    
    public void search(char[][] board,			//在字典树上dfs查找
                       int x,
                       int y,
                       TrieNode root,
                       List<String> results) {
        if (!root.children.containsKey(board[x][y])) {
            return;
        }
        
        TrieNode child = root.children.get(board[x][y]);
        
        if (child.word != null) {      //如果访问到字典树叶子，将字符串压入result即可
            if (!results.contains(child.word)) {
                results.add(child.word);
            }
        }
        
        char tmp = board[x][y];
        board[x][y] = 0;  // mark board[x][y] as used
        
        for (int i = 0; i < 4; i++) {      //向四个方向dfs搜索
            if (!isValid(board, x + dx[i], y + dy[i])) {
                continue;
            }
            search(board, x + dx[i], y + dy[i], child, results);
        }
        
        board[x][y] = tmp;  // revert the mark
    }
    
    private boolean isValid(char[][] board, int x, int y) {     //检测搜索位置合法
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }
        
        return board[x][y] != 0;
    }
}
