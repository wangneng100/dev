package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class CanReachEndpoint {
    static final int[] DELTA_X = {-1, 1, 0, 0};
    static final int[] DELTA_Y = {0, 0, -1, 1};

    /**
     * @param map: the map
     * @return: can you reach the endpoint
     */
    public boolean reachEndpoint(int[][] map) {
        if(map == null || map[0] == null) {
            return false;
        }
        if (map[0][0] == 0) {
            return false;
        }

        int r = map.length;
        int c = map[0].length;

        Queue<Integer> q = new LinkedList<>();

        boolean[][] visited = new boolean[r][c];
        q.offer(0);
        visited[0][0] = true;

        while(!q.isEmpty()) {
            int index = q.poll();
            int r1 = index / c;
            int c1 = index % c;
            
            if(map[r1][c1] == 9) {
                return true;
            }
            System.out.print("??");
            for(int i = 0; i < 4; i++) {
                int r2 = r1 + DELTA_X[i];
                int c2 = c1 + DELTA_Y[i];

                System.out.println(r2 + " " + c2);

                if(!isValid(r, c, r2, c2) || map[r2][c2] == 0 || visited[r2][c2] == true) {
                    continue;
                }
                
                q.offer(r2*c + c2);
                visited[r2][c2] = true;
                
            }
            
            
        }
        return false;
    }

    private boolean isValid(int n,int m, int i,int j) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }
}
