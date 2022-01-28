package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge {
    /**
     * use bfs find the 2 islands and use bfs to get the shortest path between 2 island
     * @param grid
     * @return
     */
    public int shortestBridge_dfs_bfs(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        Queue<Node> qu = new LinkedList<Node>();
        boolean flag = false;
        for(int r = 0; r < row && !flag; r++) {
            for(int c = 0; c < col; c++) {
                if(grid[r][c] == 1) {
                    dfs(qu, grid, r, c, row, col);
                    flag = true;
                    break;
                }
            }
        }
        int out = 0;
        int[] nei_r = new int[]{0, 0, 1, -1};
        int[] nei_c = new int[]{1, -1, 0, 0};
        while(!qu.isEmpty()) {
            int size = qu.size();
            for(int i = 0; i < size; i++) {
                Node temp = qu.poll();
                for(int p = 0; p < 4; p++) {
                    int t_r = temp.x + nei_r[p];
                    int t_c = temp.y + nei_c[p];
                    if(valid(t_r, t_c, row, col) && grid[t_r][t_c] != 2) {
                        if(grid[t_r][t_c] == 1) {
                            return out;
                        }else {
                            qu.offer(new Node(t_r, t_c));
                            grid[t_r][t_c] = 2;
                        }
                    }
                }
            }
            out++;
        }
        return out;
    }

    /**
     * use 2 direction bfs to get shortest path
     * @param grid
     * @return
     */
    public int shortestBridge_dfs_2dbfs(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        Queue<Node> qu = new LinkedList<Node>();
        boolean flag = false;
        for(int r = 0; r < row && !flag; r++) {
            for(int c = 0; c < col; c++) {
                if(grid[r][c] == 1) {
                    dfs(qu, grid, r, c, row, col);
                    flag = true;
                    break;
                }
            }
        }
        int out = 0;
        int[] nei_r = new int[]{0, 0, 1, -1};
        int[] nei_c = new int[]{1, -1, 0, 0};
        while(!qu.isEmpty()) {
            int size = qu.size();
            for(int i = 0; i < size; i++) {
                Node temp = qu.poll();
                for(int p = 0; p < 4; p++) {
                    int t_r = temp.x + nei_r[p];
                    int t_c = temp.y + nei_c[p];
                    if(valid(t_r, t_c, row, col) && grid[t_r][t_c] != 2) {
                        if(grid[t_r][t_c] == 1) {
                            return out;
                        }else {
                            qu.offer(new Node(t_r, t_c));
                            grid[t_r][t_c] = 2;
                        }
                    }
                }
            }
            out++;
        }
        return out;
    }
    
    public void dfs(Queue<Node> qu, int[][] grid, int r, int c, int row, int col) {
        if(!valid(r, c, row, col) || grid[r][c] != 1) {
            return;
        }
        grid[r][c] = 2;
        qu.offer(new Node(r, c));
        dfs(qu, grid, r, c + 1, row, col);
        dfs(qu, grid, r, c - 1, row, col);
        dfs(qu, grid, r + 1, c, row, col);
        dfs(qu, grid, r - 1, c, row, col);
    }
    
    public boolean valid(int r, int c, int row, int col) {
        if(r < 0 || c < 0 || r >= row || c >= col) {
            return false;
        }
        return true;
    }
}

class Node {
    int x, y;
    Node(int i, int j) {
        x = i;
        y = j;
    }
}
