package datastructure.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class MergeKSortedLists {
    public ListNode mergeKLists(List<ListNode> lists) {  
        if(lists == null || lists.size() == 0) {
            return null;
        }

        while(lists.size() > 1) {
            List<ListNode> newLists = new ArrayList<>();
            for(int i = 0; i < lists.size() - 1 ; i+=2) {
                ListNode node = mergeTwoLists(lists.get(i), lists.get(i + 1));
                newLists.add(node);
            }
            if(lists.size() % 2 == 1){
                newLists.add(lists.get(lists.size() - 1));
            }
            lists = newLists;
        }
        return lists.get(0);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
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
