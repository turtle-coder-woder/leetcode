package easyCollection;

import helper.ListNode;

public class LinkedListHasCycleOrNot {
    public boolean hasCycle(ListNode head) {
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        while (fastPtr!=null && fastPtr.next!=null){
            fastPtr = fastPtr.next.next;
            if(slowPtr==fastPtr){
                return true;
            }
            slowPtr = slowPtr.next;
        }
        return false;
    }
}
