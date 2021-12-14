package tree;

import java.util.HashMap;


public class CopyLinkedListWithRandomPointer {
    HashMap<Node, Node> visitedHash = new HashMap<Node, Node>();

    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        // If we have already processed the current node, then we simply return the
        // cloned version of
        // it.
        if (this.visitedHash.containsKey(head)) {
            return this.visitedHash.get(head);
        }

        // Create a new node with the value same as old node. (i.e. copy the node)
        Node node = new Node(head.val);

        // Save this value in the hash map. This is needed since there might be
        // loops during traversal due to randomness of random pointers and this would
        // help us avoid
        // them.
        this.visitedHash.put(head, node);

        // Recursively copy the remaining linked list starting once from the next
        // pointer and then from
        // the random pointer.
        // Thus we have two independent recursive calls.
        // Finally we update the next and random pointers for the new node created.
        node.next = this.copyRandomList(head.next);
        node.random = this.copyRandomList(head.random);

        return node;
    }

    public Node copyRandomList_iterator(Node head) {
        if (head == null) {
            return null;
        }

        HashMap<Node, Node> orig2new = new HashMap<>();
        Node dummyHead = new Node(-1);
        Node newCurr = dummyHead;
        Node curr = head;
        while (curr != null) {
            Node node = new Node(curr.val);
            newCurr.next = node;
            orig2new.put(curr, node);
            curr = curr.next;
            newCurr = newCurr.next;
        }

        curr = head;
        newCurr = dummyHead.next;

        while (curr != null) {

            newCurr.random = orig2new.get(curr.random);

            curr = curr.next;
            newCurr = newCurr.next;
        }

        return dummyHead.next;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}