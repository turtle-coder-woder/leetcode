package p150problems;

import p150problems.helper.ListNode;

public class RemoveNthFromEnd {
            /*
        Remove nth from end using recursion:

        - go till end first
        - while coming back, count from end
        - when count == n, remove current node

        Think:
        I cannot know nth from end on the way down.
        I know it only on the way back.

        Other clever approach, slow and fast pointer
        move fast pointer till n
        and then move slow and fast pointer by 1 unit only to delete
        public ListNode removeNthFromEnd(ListNode head, int n) {
        int i=0;
        ListNode previousPointer = null;
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        while(i!=n && fastPointer!=null){
            fastPointer=fastPointer.next;
            i++;
        }
        while(fastPointer!=null){
            previousPointer = slowPointer;
            slowPointer=slowPointer.next;
            fastPointer=fastPointer.next;
        }

        //now actual deletion
        if(previousPointer==null){
            //this means we need to delete from head first element
            ListNode temp = head.next;
            head.next=null;
            head=temp;
        }else{
            previousPointer.next = slowPointer.next;
            slowPointer.next=null;
        }

        return head;
    }
        */


    int countFromBehind=1;
    public ListNode removeNthFromEnd(ListNode head, int n) {
        return removeNthFromEndHelper(null,head,n);
    }

    private ListNode removeNthFromEndHelper(ListNode prev,ListNode head, int n) {
        if(head==null){
            return head;
        }

        ListNode tail = removeNthFromEndHelper(head,head.next,n);
        if(countFromBehind==n){
           if(prev==null){
               //dealing with deletion of first node itself
               ListNode temp=head.next;
               head.next=null;
               head=temp;
           }else{
               prev.next = tail;
               head.next=null;
           }
        }
        countFromBehind++;
        return head;
    }


    public static void main(String[] args){
        ListNode head = ListNode.getLinkedListOf(3);
        int n=2;
        ListNode.printLinkedList(head);
        System.out.println(new RemoveNthFromEnd().removeNthFromEnd(head,n));
        ListNode.printLinkedList(head);
    }
}
