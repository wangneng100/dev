package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class ModernLudo {
    //dp方案，从尾部开始往前推理。
    // 1.依次比较dp[i+6] -- dp[i+1]，最小值赋给dp[i]. 
    // 2.如果i的直连点有比dp[i]小的，更新dp[i]
    public int modernLudo_dp(int length, int[][] connections) {
        //dp[i]  means the min steps from i  to end
        int [] dp = new int[length + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[length] = 0;

        Map<Integer, Set<Integer>> node2Neighbors = buildGraph(connections);

        for(int i = length - 1; i >= 1; i--) {
            for (int j = i + 1; j <= Math.min(length, i + 6); j++) {
                // if(dp[j] == Integer.MAX_VALUE) {
                //     continue;
                // }
                dp[i] = Math.min(dp[i], dp[j] + 1);
            }
            if(node2Neighbors.containsKey(i)) {
                for (int neighbor : node2Neighbors.get(i)) {
                    dp[i] = Math.min(dp[i],dp[neighbor]);
                }
            }
            
        }

        return dp[1];
    }

    //双队列(列表)交替，存本次和下次的所有点，每次距离+1
    public int modernLudo(int length, int[][] connections) {
        Map<Integer, Set<Integer>> node2Neighbors = buildGraph(connections);

        Map<Integer, Integer> node2dis = new HashMap<>();

        List<Integer> q1 = new ArrayList<>();
        q1.add(1);
        node2dis.put(1, 0);

        while(!q1.isEmpty()) {

            for (int i = 0; i < q1.size(); i++) {
                int curr = q1.get(i);
                if(node2Neighbors.containsKey(curr)) {
                    for(int neighbor : node2Neighbors.get(curr)) {
                        if(!node2dis.containsKey(neighbor)) {
                            q1.add(neighbor);
                            node2dis.put(neighbor, node2dis.get(curr));
                        }
                    }
                }
            }

            List<Integer> q2 = new ArrayList<>();
            for (int i = 0; i < q1.size(); i++) {
                int curr = q1.get(i);
                for(int j = curr + 1; j <= Math.min(length, curr+6); j++) {
                    if(!node2dis.containsKey(j)) {
                        q2.add(j);
                        node2dis.put(j, node2dis.get(curr) + 1);
                    }
                }
            }
            q1 = q2;
        }

        return node2dis.get(length); 

    }

    //spfa里面用PriorityQueue作为优化
    // 1.遍历直连点，如果distance[neighbor] > distance[curr], 丢入队列，distance[neighbor] = distance[curr]
    // 2.遍历i+1 to i+6, 如果distance[next] > distance[curr], 丢入队列，distance[next] = distance[curr]+1
    public int modernLudo_spfa(int length, int[][] connections) {
        Map<Integer, Set<Integer>> node2Neighbors = buildGraph(connections);
        Map<Integer, Integer> node2dis = new HashMap<>();
        
        //int[2] pair, int[0] node number, int[2] min distance
        Queue<int[]> q1 = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        q1.add(new int[]{1,0});
        node2dis.put(1, 0);


        while(!q1.isEmpty()) {

            // System.out.println("q1:" + q1);
            int curr = q1.poll()[0];

            addDirectConnectNodes(q1, node2Neighbors, node2dis, curr);

            for (int j = 1; j <= 6; j++) {
                int next = curr + j;
                // System.out.println("res: " + res + " curr:" + curr);
                if(next > length) continue;
                
                int distance = node2dis.get(curr) + 1;
                if(!node2dis.containsKey(next) || node2dis.get(next) > distance) {
                    q1.add(new int[]{next, distance});
                    node2dis.put(next, distance);
                }
            }
        }

        return node2dis.get(length);
    }

    
    
    private void addDirectConnectNodes(Queue<int[]> q, Map<Integer, 
            Set<Integer>> node2Neighbors, Map<Integer, Integer> node2dis, int curr) {
        if(!node2Neighbors.containsKey(curr))
            return;
        for(int neighbor : node2Neighbors.get(curr)) {

            // !!!!!!!!!!!! do not miss the second condition !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            // !!!! add to distance map if the value is not there, or the value is larger than dis(curr) + 1
            if(!node2dis.containsKey(neighbor) || node2dis.get(neighbor) > node2dis.get(curr)) {
                q.add(new int[]{neighbor,node2dis.get(curr)});
                node2dis.put(neighbor, node2dis.get(curr));
            }
        }
    }

    //用双重BFS， 外层BFS求最短路径，内层BFS判断连通性
    public int modernLudo_double_bfs(int length, int[][] connections) {
        
        Map<Integer, Set<Integer>> node2Neighbors = buildGraph(connections);

        Queue<Integer> q = new ArrayDeque<>();
        Map<Integer, Integer> node2dis = new HashMap<>();

        q.add(1);
        node2dis.put(1, 0);
        
        while(!q.isEmpty()) {

            int curr = q.poll();
            
            for (int j = 1; j <= 6; j++) {
                int next = curr + j;
                if(next > length) continue;

                List<Integer> connectedNodes = getUnVisitedNodes(node2Neighbors, node2dis, next);
                for(int connectedNode : connectedNodes) {
                    node2dis.put(connectedNode, node2dis.get(curr) + 1);
                    q.offer(connectedNode);
                }
                
            }
            
        }

        return node2dis.get(length);
    }

    private List<Integer> getUnVisitedNodes(Map<Integer, Set<Integer>> node2Neighbors, Map<Integer, Integer> node2dis, int node){
        List<Integer> nodes = new ArrayList<>();

        Queue<Integer> q = new ArrayDeque<>();
        q.add(node);

        while(!q.isEmpty()) {
            int curr = q.poll();
            if(node2dis.containsKey(curr)) {
                continue;
            }

            nodes.add(curr);

            if(node2Neighbors.containsKey(curr)) {
                for(int neighbor : node2Neighbors.get(curr)) {
                    if(!node2dis.containsKey(neighbor)) {
                        q.offer(neighbor);
                        nodes.add(neighbor);
                    }
                }
            }
        }

        return nodes;
    }

    private Map<Integer, Set<Integer>> buildGraph(int[][] connections){

        Map<Integer, Set<Integer>> node2Neighbors = new HashMap<>();
        for(int[] edge : connections) {
            if(!node2Neighbors.containsKey(edge[0])) {
                Set<Integer> set = new HashSet<>();
                set.add(edge[1]);
                node2Neighbors.put(edge[0], set);
            } else {
                node2Neighbors.get(edge[0]).add(edge[1]);
            }
        }
        return node2Neighbors;
    }
}
