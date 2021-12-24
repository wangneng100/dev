package datastructure.linkedlist;

public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        while(l1 != null) {
            current.next = l1;
            l1 = l1.next;
            current = current.next;
        }
        while(l2 != null) {
            current.next = l2;
            l2 = l2.next;
            current = current.next;
        }
        return dummy.next;
    }
    /**
     * @param l1: ListNode l1 is the head of the linked list
     * @param l2: ListNode l2 is the head of the linked list
     * @return: ListNode head of linked list
     */
    public ListNode mergeTwoLists_recrusive(ListNode l1, ListNode l2){
        if(l1 == null) {
            return l2;
        }

        if(l2 == null) {
            return l1;
        }

        if(l1.val > l2.val) {
            return mergeTwoLists(l2, l1);
        }

        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
    }
}
