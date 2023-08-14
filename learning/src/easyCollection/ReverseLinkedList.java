package easyCollection;

import helper.ListNode;

public class ReverseLinkedList {
    //single pass solution
    public ListNode reverseList(ListNode node) {
        if (node == null) {
            return null;
        }

        ListNode p1 = null;
        ListNode p2 = node, p3 = node;
        while (p3 != null) {
            p3 = p3.next;
            p2.next = p1;
            p1 = p2;
            p2 = p3;
        }
        return p1;
    }
}
