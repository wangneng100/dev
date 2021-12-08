package bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class IslandNum3{
    static final int[] DELTA_X = {-1, 1, 0, 0};
    static final int[] DELTA_Y = {0, 0, -1, 1};
    /**
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
    public int numIslands(boolean[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int r = grid.length;
        int c = grid[0].length;
        boolean [] visited = new boolean[r*c];
        int num = 0;
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(!visited[i *c +j] && grid[i][j]) {
                    findIsland(grid, visited, i, j);
                    num++;
                }
            }
        }

        return num;
    }

    private void findIsland(boolean[][] grid, boolean [] visited, int i, int j) {
        int r = grid.length;
        int c = grid[0].length;
        Queue<Integer> queue = new ArrayDeque<>();

        int index = i * c + j;
        queue.offer(index);
        visited[index] = true;
        // System.out.println("i: " + i + "  j:" + j);


        while(!queue.isEmpty()) {
            int value = queue.poll();
            int index_i = value / c;
            int index_y = value % c;

            // System.out.println("ii: " + index_i + "  ij:" + index_y);

            for(int k = 0; k < 4; k++) {
                int i1 = index_i + DELTA_X[k];
                int j1 = index_y + DELTA_Y[k];
                // System.out.println("i1: " + i1 + "  j1:" + j1);
                int index1 = i1*c+j1;
                if(isValid(r, c, i1, j1) && !visited[index1] && grid[i1][j1]){
                    
                    queue.offer(index1);
                    visited[index1] = true;
                }
            }
        }
        
    }

    private boolean isValid(int r, int c, int i, int j) {
        if(i >= r || i < 0 || j >= c || j < 0) {
            return false;
        }
        return true;
    }
}
