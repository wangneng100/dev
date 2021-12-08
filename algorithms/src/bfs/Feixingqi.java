package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Feixingqi {
    /**
     * @param length: the length of board
     * @param connections: the connections of the positions
     * @return: the minimum steps to reach the end
     */
    public int modernLudo(int length, int[][] connections) {
        //build connection graph
        Map<Integer,Set<Integer>> graph = buildGraph(length, connections);

        //start position is 1
        List<Integer> queue = new ArrayList<>();
        queue.add(1);

        //how many steps do we need from 1 to index
        Map<Integer, Integer> index2step = new HashMap<>();
        index2step.put(1,0);

        while(queue.size() > 0) {
            
            for(int i = 0;  i < queue.size(); i++) {
                int node = queue.get(i);
                // System.out.println("node: " + node);
                for(int tmp: graph.get(node)) {
                    if(!index2step.containsKey(tmp)){
                        queue.add(tmp);
                        index2step.put(tmp, index2step.get(node));
                    }
                }

            }

            List<Integer> newQueue = new ArrayList<>();
            for(int i = 0;  i < queue.size(); i++) {
                int node = queue.get(i);
                // System.out.println("node1: " + node);
                // from current position to get all positions in next step
                int limit = Math.min(node+6, length);
                // System.out.println("limit: " + limit);
                for(int next = node +1; next <= limit; next++) {
                    if(!index2step.containsKey(next)){
                        newQueue.add(next);
                        index2step.put(next, index2step.get(node) + 1);
                    }
                }

            }
            queue = newQueue;
        }

        return index2step.get(length);

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
