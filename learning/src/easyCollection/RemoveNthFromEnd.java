package easyCollection;

import helper.ListNode;

public class RemoveNthFromEnd {
    //Solution will be in just 1 pass
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        ListNode nthAfterFirst = head;
        nthAfterFirst = moveNodeToNth(nthAfterFirst, n - 1);

        if (nthAfterFirst.next == null) {
            return removeHead(head);
        } else {
            ListNode prev = first;
            while (nthAfterFirst.next != null) {
                prev = first;
                first = first.next;
                nthAfterFirst = nthAfterFirst.next;
                //System.out.println("while motion prev " + prev.val + " first " + first.val + " nthAfterFirst" + nthAfterFirst.val);
            }
            deleteInternalNode(prev, first);
        }
        return head;
    }

    private void deleteInternalNode(ListNode prev, ListNode first) {
        prev.next = first.next;
        first = null;
    }

    private ListNode removeHead(ListNode head) {
        ListNode x = head;
        head = head.next;
        x = null;
        return head;
    }

    private ListNode moveNodeToNth(ListNode nthAfterFirst, int n) {
        while (n > 0 && nthAfterFirst != null) {
            nthAfterFirst = nthAfterFirst.next;
            n--;
        }
        //System.out.println("first motion " + nthAfterFirst.val);
        return nthAfterFirst;
    }
}
