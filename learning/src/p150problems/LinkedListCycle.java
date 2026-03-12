package p150problems;

import p150problems.helper.ListNode;

public class LinkedListCycle {
    /*
    Cycle detection:
    slow = 1 step
    fast = 2 steps

    If cycle exists → fast eventually meets slow.
    If fast hits null → no cycle.
    */
    public boolean hasCycle(ListNode head) {
        ListNode slowPointer = head;
        ListNode fastPointer = head;

        while(fastPointer!=null && fastPointer.next!=null){
            slowPointer=slowPointer.next;
            fastPointer=fastPointer.next.next;
            if(slowPointer==fastPointer){
                return true;
            }
        }
        return false;
    }

    private boolean hasCycleSlowAndFastPointers(ListNode slowPointer, ListNode fastPointer) {
        if(fastPointer==null|| fastPointer.next==null){
            return false;
        }
        if(slowPointer==fastPointer){
            return true;
        }
        return hasCycleSlowAndFastPointers(slowPointer.next,fastPointer.next.next);
    }

    public static void main(String[] args){
        ListNode head = ListNode.getLinkedListOf(5);
        ListNode temp = head;
        temp.next.next.next=temp;
        System.out.println();
        System.out.println(new LinkedListCycle().hasCycle(head));
    }
}
