package datastructure.linkedlist;

public class ReserveLinkedList {
	
	public ListNode reverseList_recursive(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode second = head.next;
        ListNode tmp = reverseList(second);
        second.next = head;
        head.next = null;
        return tmp;
    }
	
	public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while(curr != null) {
            ListNode nextTmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTmp;
        }
        
        return prev;
    }
}
