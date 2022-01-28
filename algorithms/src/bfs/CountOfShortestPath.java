package bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/*
Given a map of nn rows and mm columns, where 00 represents the road that can be taken and 11 represents the wall, you can start from any position in the first row to any position in the last row. You need return the number of shortest path.

When either of the two paths is different, we call it a different path.
*/
public class CountOfShortestPath {
    private static final int dx[] = {0, 1, 0, -1};
    private static final int dy[] = {1, 0, -1, 0};
    private static final int ROAD = 0;
    // private static final int WALL = 1;

    // 0 1 0 1
    // 0 0 0 0
    // 1 0 1 0
    // 0 0 0 0
    // total path 3

     public int theNumberofShortestPath(int n, int m, int[][] labyrinth) {
        // write your code here
        
        int [][] visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        int res = 0;
        int step = 0;
        Queue<int[]> q = new ArrayDeque<>();
        for (int j = 0; j < m; j++) {
            if(labyrinth[0][j] == ROAD) {
                q.offer(new int[]{0,j});
                visited[0][j] = step;
            }
        }

        while(!q.isEmpty() && res == 0) {
            int size = q.size();
            step++;

            for (int i = 0; i < size; i++) {
                int[] curr  = q.poll();

                for (int j = 0; j < 4; j++) {
                    int x = curr[0] + dx[j];
                    int y = curr[1] + dy[j];

                    if(!isValid(n,m,x,y,labyrinth) || step > visited[x][y]) {
                        continue;
                    }

                    visited[x][y] = step;
                    if(x == n - 1) {
                        res++;
                        break;
                    }
                    q.offer(new int[]{x, y});
                }
            }
            
        }
        return res;
    }

    private boolean isValid(int n, int m, int x, int y, int[][] labyrinth){
        if(x < 0 || x >= n || y <  0 || y >= m) {
            return false;
        }

        return labyrinth[x][y] == ROAD;
    }
}
