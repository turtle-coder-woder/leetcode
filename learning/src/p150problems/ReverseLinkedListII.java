package p150problems;

import p150problems.helper.ListNode;

public class ReverseLinkedListII {
    //version 1
    ListNode successor;
    ListNode predecessor;
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode headNew;
        if(left!=1){
            headNew = moveHeadForReversal(head,left,right);
        }else{
            headNew = head;
        }

        ListNode node = reverse(headNew,right-left,0);
        headNew.next=successor;
        if(predecessor!=null){
            predecessor.next=node;
        }
        return left==1? node: head;
    }

    private ListNode reverse(ListNode headNew, int numberOfNodesToReverse, int processedNodes) {
        if(processedNodes==numberOfNodesToReverse){
            successor = headNew.next;
            return headNew;
        }
        ListNode newHead = reverse(headNew.next,numberOfNodesToReverse,processedNodes+1);
        headNew.next.next = headNew;
        headNew.next=null;
        return newHead;
    }

    private ListNode moveHeadForReversal(ListNode head, int left, int right) {
        if(left==1){
            return head;
        }
        if(left==2){
            predecessor = head;
        }
        return moveHeadForReversal(head.next,left-1,right-1);
    }

    public static void main(String[] args){
        ListNode head = ListNode.getLinkedListOf(2);
        ListNode.printLinkedList(head);
        ListNode newHead = new ReverseLinkedListII().reverseBetween(head,1,2);
        ListNode.printLinkedList(newHead);
    }
}
