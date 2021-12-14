package datastructure.linkedlist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 给定一个双向链表和一个节点数组。如果节点彼此连接(这意味着我们可以通过其中任何一个节点访问任何节点)，
 * 那么我们可以将它们视为一个块。查找给定数组中的块数。
 * 输入：1<->2<->3<->4,[1,3,4]输出：2
 * 解释：在节点数组中，没有与1连接的节点，节点3与4相连。所以块的数量是2。
 * @author nwang
 *
 */
public class BlockInLinkedList {
	public int blockNumber(DoublyListNode head, int[] nodes) {
        int len = nodes.length;
        
        int count = 0;

        Map<Integer, Boolean> visited = new HashMap<>();
        for (int i = 0; i < len; i++) {
            visited.put(nodes[i], false);
        }

        Map<Integer, Set<Integer>> map = adjacentMap(head);
        for (int i = 0; i < len; i++) {

            int val = nodes[i];
            if(visited.containsKey(nodes[i]) && !visited.get(nodes[i])) {
                bfs(nodes, i, map, visited);
                count++;
            }
        }

        return count;
    }

    public Map<Integer, Set<Integer>> adjacentMap(DoublyListNode head){
        Map<Integer, Set<Integer>> map = new HashMap<>();
        while(head != null) {
            int val = head.val;
            Set<Integer> set = new HashSet<>();
            if(head.prev != null) {
                set.add(head.prev.val);
            }
            if(head.next != null) {
                set.add(head.next.val);
            }
            map.put(val, set);
            head = head.next;
        }
        // System.out.println("map size" + map.size());
        return map;
    }

    public void bfs(int[] nodes, int i, Map<Integer, Set<Integer>> map, Map<Integer, Boolean> visited){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(nodes[i]);
        visited.put(nodes[i], true);
        // System.out.println("i:" + i);

        while(!queue.isEmpty()) {
            Integer node = queue.poll();
            // System.out.println("node:" + node);
            // System.out.println("set:" + map.get(node));
            
            if(!map.containsKey(node)) {
                continue;
            }
            for(int neigbo: map.get(node)) {
                if(visited.containsKey(neigbo) && !visited.get(neigbo)) {
                    // System.out.println("neigbo:" + neigbo);
                    queue.offer(neigbo);
                    visited.put(neigbo, true);
                } 
            }
        }
    }
}

class DoublyListNode {
     int val;
	   DoublyListNode prev;
     DoublyListNode next;
     DoublyListNode(int x) {
         val = x;
         prev = next = null;
     }
 }
