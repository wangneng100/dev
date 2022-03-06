package companys.oracle;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/time-based-key-value-store/
public class TimeMapLinkedList {
    Map<String, ListNode> maps;

    public TimeMapLinkedList() {
        maps = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        ListNode newNode = new ListNode(value, timestamp);

        ListNode prev = maps.put(key, newNode);
        newNode.next = prev;
    }

    public String get(String key, int timestamp) {
        ListNode prev = maps.getOrDefault(key, null);

        while (prev != null && timestamp < prev.timestamp) {
            prev = prev.next;
        }

        return prev == null ? "" : prev.value;
    }
}

class ListNode {
    String value;
    int timestamp;
    ListNode next;

    public ListNode(String value, int timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }
}
