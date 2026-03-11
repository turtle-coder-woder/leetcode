package p150problems.helper;


//Definition for singly-linked list.
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode getLinkedListOf(int size){
        ListNode[] nodes = new ListNode[size];
        //fill nodes in array
        for(int i=0;i<size;i++){
            nodes[i] = new ListNode(i+1);
        }

        //now join them 0--8 as last one link will automatically be pointing to null
        for(int i=0;i<size-1;i++){
            nodes[i].next = nodes[i+1];
        }

        return nodes[0];//return as head
    }

    public static void printLinkedList(ListNode head){
        System.out.println();
        while (head!=null){
            System.out.print(head.val + " -> ");
            head=head.next;
        }

        System.out.print("X");
    }
}
