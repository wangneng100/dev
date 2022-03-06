package system.design;

import java.util.HashMap;
import java.util.Map;

/*
https://www.lintcode.com/problem/134/
Description
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
Finally, you need to return the data from each get.
*/
public class LRUCache {
    Map<Integer,Node> key2Node;
    Node head;
    Node tail;
    int capacity;
    /*
    * @param capacity: An integer
    */public LRUCache(int capacity) {
        key2Node = new HashMap<>();
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        int res = -1;
        if(key2Node.containsKey(key)){
            Node node = key2Node.get(key);
            res = node.val;

            removeNode(node);
            addToFront(node);
        }

        return res;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        if(key2Node.containsKey(key)) {
            //set value and add to front
            Node node = key2Node.get(key);
            node.val = value;
            removeNode(node);
            addToFront(node);
        } else {
            if(key2Node.size() == capacity) {
                //remove last one
                Node last = tail.prev;
                removeNode(last);
                key2Node.remove(last.key);
            }
            // create new node and add to front
            Node node = new Node();
            node.val = value;
            node.key = key;
            key2Node.put(key, node);

            addToFront(node);
        }
        
    }

    private void addToFront(Node node){
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev = head;
    }

    private void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
}

class Node{
    int key;
    int val;
    Node next;
    Node prev;	
}