package bfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Feixingqi_SPFA {
    class Pair {
        int node;
        int distance;

        public Pair(int value, int dis) {
            node = value;
            distance = dis;
        }
    }

    /**
     * @param length:      the length of board
     * @param connections: the connections of the positions
     * @return: the minimum steps to reach the end
     */
    public int modernLudo(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = buildGraph(length, connections);

        Queue<Pair> queue = new PriorityQueue<>((a, b) -> a.distance - b.distance);

        // start position is 1
        queue.offer(new Pair(1, 0));

        // how many steps do we need from 1 to index
        Map<Integer, Integer> index2step = new HashMap<>();
        index2step.put(1, 0);
        for (int i = 2; i <= length; i++) {
            index2step.put(i, Integer.MAX_VALUE);
        }

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            int distance = p.distance;
            int node = p.node;

            for (int tmp : graph.get(node)) {
                if (index2step.get(tmp) > distance) {
                    index2step.put(tmp, distance);
                    queue.offer(new Pair(tmp, distance));
                }
            }

            int limit = Math.min(node + 6, length);
            // System.out.println("limit: " + limit);
            for (int next = node + 1; next <= limit; next++) {
                if (index2step.get(next) > distance + 1) {
                    index2step.put(next, distance + 1);
                    queue.offer(new Pair(next, distance + 1));
                }
            }
        }
        return index2step.get(length);

    }

    public Map<Integer, Set<Integer>> buildGraph(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= length; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        for (int j = 0; j < connections.length; j++) {
            int key = connections[j][0];
            graph.get(key).add(connections[j][1]);
        }
        return graph;
    }
}
