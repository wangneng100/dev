package companys.tekion;

import java.util.HashMap;
import java.util.Map;

public class CloneLinkedListWithRandomPointer {
	
	public static Node copyList(Node oriNode) {
		Map<Node, Node> orgNode2copyNode = new HashMap<>();
		Node orgCurr = oriNode, copyNode = null;
		
		while(orgCurr!=null) {
			copyNode = new Node(orgCurr.val);
			orgNode2copyNode.put(orgCurr, copyNode);
			orgCurr = orgCurr.next;
		}
		
		orgCurr = oriNode;
		
		while(orgCurr!=null) {
			copyNode = orgNode2copyNode.get(orgCurr);
			copyNode.next = orgNode2copyNode.get(orgCurr.next);
			copyNode.random = orgNode2copyNode.get(orgCurr.random);
			orgCurr = orgCurr.next;
		}
		
		return orgNode2copyNode.get(oriNode);
	}
	
	public static void main(String[] args) {
		Node node4 = new Node(4, null, null);
		Node node3 = new Node(3, node4, node4);
		
		Node node2 = new Node(2, node3, node3);
		Node node1 = new Node(1, node2, node3);
		
		Node copyNode = copyList(node1);
		System.out.println(copyNode.val);
		System.out.println(copyNode.next.random.val);
	}
}



class Node{
	int val;
	Node next;
	Node random;
	
	public Node(int value) {
		val = value;
		next = null;
		random = null;
	}
	
	public Node(int value, Node n, Node r) {
		val = value;
		next = n;
		random = r;
	}
}