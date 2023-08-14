package easyCollection;

import helper.ListNode;

public class DeleteNodeInALinkedList {
    public void deleteNode(ListNode node) {
        ListNode nextNode  = node.next;
        node.next = nextNode.next;
        node.val = nextNode.val;
    }
}
