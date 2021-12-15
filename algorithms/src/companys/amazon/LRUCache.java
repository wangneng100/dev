package companys.amazon;

import java.util.HashMap;

public class LRUCache {
	
	class Node{
		int key;
		int value;
		Node next;
		Node prev;
		
	}
	
	HashMap<Integer, Node> key2Node;
	Node head;
	Node tail;
	int capacity;
	
	public LRUCache(int cap) {
		capacity = cap;
		
		key2Node = new HashMap<>();
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.prev = head;
	}
	
	public int get(int key) {
		Node node = key2Node.get(key);
		
		if(node != null) {
			removeNode(node);
			addToFront(node);
			return node.value;
		}
		return -1;
	}
	
	public void put(int key, int value) {
		Node node = key2Node.get(key);
		if(node != null) {
			removeNode(node);
			addToFront(node);
			node.value = value;
		} else {
			if(key2Node.size() == capacity) {
				//must remove from map first then remove from linkedlist
				key2Node.remove(tail.prev.key);
				removeNode(tail.prev);
			}
			node = new Node();
			node.key = key;
			node.value = value;
			
			addToFront(node);
			key2Node.put(key, node);
		}
	}
	
	private void removeNode(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}
	
	private void addToFront(Node node) {
		node.next = head.next;
		head.next.prev = node;
		
		head.next = node;
		node.prev = head;
	}
}
