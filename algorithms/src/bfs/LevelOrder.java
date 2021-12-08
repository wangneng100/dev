package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder {
    /**
     * @param root: the tree root
     * @return: the order level of this tree
     */
    public List<List<Integer>> levelOrder(UndirectedGraphNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<UndirectedGraphNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                UndirectedGraphNode node = q.poll();
                tmp.add(node.label);
                for (UndirectedGraphNode tmpNode : node.neighbors) {
                    q.offer(tmpNode);
                }
            }
            res.add(tmp);
        }
        return res;
    }
}

class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
