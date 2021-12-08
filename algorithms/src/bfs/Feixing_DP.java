package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Feixing_DP {
    /**
     * @param length: the length of board
     * @param connections: the connections of the positions
     * @return: the minimum steps to reach the end
     */
    public int modernLudo(int length, int[][] connections) {
        Map<Integer,Set<Integer>> graph = buildGraph(length, connections);
        //build connection graph
        int[] dp = new int[length + 1];
        
        for(int i = 1; i <= length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[length]=0;
        

        for(int i = length - 1; i > 0; i--) {
            // handle all postions with 1 step
            int limit = Math.min(i + 7, length + 1);
            for(int j = i + 1; j < limit; j++) {
                if(dp[j] == Integer.MAX_VALUE) {
                    continue;
                }
                dp[i] = Math.min(dp[j] + 1, dp[i]);
            }
            //handle all the connected position
            for(int j: graph.get(i)) {
                dp[i] = Math.min(dp[i], dp[j]);
            } 
        }
        
        return dp[1];
    }

    public Map<Integer,Set<Integer>> buildGraph(int length, int[][] connections){
        Map<Integer,Set<Integer>> graph =  new HashMap<>();
        for(int i = 1; i <= length; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        for (int j = 0; j < connections.length; j++) {
            int key = connections[j][0];
            graph.get(key).add(connections[j][1]);
        }
        return graph;
    }
}
