package datastructure.linkedlist;

public class DeleteElementInLinkedList {
	public ListNode removeElements(ListNode head, int val) {

		while (head != null && head.val == val) {
			head = head.next;
		}

		ListNode curr_node = head;
		while (curr_node != null && curr_node.next != null) {
			// System.out.println("val "+ curr_node.next.val);
			if (curr_node.next.val == val) {
				curr_node.next = curr_node.next.next;
			} else {
				curr_node = curr_node.next;
			}
		}
		return head;
	}
}
