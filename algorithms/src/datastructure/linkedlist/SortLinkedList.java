package datastructure.linkedlist;

import java.util.TreeMap;

public class SortLinkedList {

	public ListNode sortLinkedList(ListNode root) {
		TreeMap<Integer, ListNode> val2Node = new TreeMap<>();

		ListNode curr = root;
		while (curr != null) {
			int val = curr.val;
			val2Node.put(val, curr);

			if (curr.next != null && curr.next.val < curr.val) {
				int nextValue = curr.next.val;
				ListNode nextNode = curr.next;
				ListNode nextNext = curr.next.next;
				
				ListNode floorNode = val2Node.floorEntry(nextValue).getValue();
				nextNode.next = floorNode.next;
				floorNode.next = nextNode;
				curr.next = nextNext;
				val2Node.put(nextValue, nextNode);
			} else {
				curr = curr.next;
			}
		}

		return root;

	}
	
	private void printList(ListNode root) {
		while(root != null) {
			System.out.println(root.val);
			root = root.next;
		}
		
	}

	public static void main(String[] args) {
		SortLinkedList sll = new SortLinkedList();

		ListNode root = new ListNode(1, new ListNode(2, new ListNode(3, 
				new ListNode(6, new ListNode(4,new ListNode(5, null))))));
		
		sll.sortLinkedList(root);
		
		sll.printList(root);
	}

}
