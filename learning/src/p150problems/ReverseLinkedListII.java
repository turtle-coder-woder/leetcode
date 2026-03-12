package p150problems;

import p150problems.helper.ListNode;

public class ReverseLinkedListII {
        /*
    MENTAL MODEL — Reverse Linked List II (left..right)

    Think of the problem in TWO phases:

    --------------------------------------------------
    1) Move until the start of the reversal
    --------------------------------------------------

    If left != 1:
        current node should remain unchanged.

    So we recursively move forward until we reach
    the node where reversal should start.

    Conceptually:

        head.next = reverseBetween(head.next, left-1, right-1)

    Meaning:
    - nodes before "left" remain untouched
    - recursion shifts the window forward


    --------------------------------------------------
    2) Once at the start (left == 1)
    --------------------------------------------------

    Now the problem becomes:

        "reverse the first N nodes"

    where:

        N = right - left + 1

    Standard recursive trick:

        head.next.next = head    // flip link
        head.next = successor    // reconnect tail


    --------------------------------------------------
    Clean Interview Version (simpler to remember)
    --------------------------------------------------

    reverseBetween:
        if left == 1:
            return reverseFirstN(head, right)

        head.next = reverseBetween(head.next, left-1, right-1)

    reverseFirstN:
        store successor = node after reversed part
        reverse links using:
            head.next.next = head
        reconnect using:
            head.next = successor


    --------------------------------------------------
    How MY Implementation Maps to This Idea
    --------------------------------------------------

    Instead of recursion to move left boundary,
    I manually locate the start node.

    Steps used in this code:

    1) moveHeadForReversal(...)
          moves pointer to node at position "left"
          and stores predecessor.

    2) reverse(...)
          reverses nodes from left..right
          using recursion.

    3) successor
          remembers node after reversed segment.

    4) reconnect list

          predecessor -> new reversed head
          old left node -> successor


    --------------------------------------------------
    Important edge case
    --------------------------------------------------

    If left == 1:

    The head of the list changes after reversal.

    So return the new reversed head instead
    of the original head.


    --------------------------------------------------
    Key pointer operations
    --------------------------------------------------

    head.next.next = head   // actual reversal
    head.next = successor   // reconnect tail

    Think:

    "Recursion reverses the rest,
     I only flip my local arrow."
    */
    ListNode successor;

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseKNodes(head, right);
        }

        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    ListNode reverseKNodes(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }

        ListNode newHead = reverseKNodes(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.getLinkedListOf(2);
        ListNode.printLinkedList(head);
        ListNode newHead = new ReverseLinkedListII().reverseBetween(head, 1, 2);
        ListNode.printLinkedList(newHead);
    }
}
