package p150problems.helper;


//Definition for singly-linked list.
public class ListNode {
    public int val;
    public ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode getLinkedListOfWithOffset(int size,int offSetStart){
        return getLinkedListOf(size,offSetStart);
    }

    private static ListNode getLinkedListOf(int size, int offSetStart){
        ListNode[] nodes = new ListNode[size];
        //fill nodes in array
        for(int i=0;i<size;i++){
            nodes[i] = new ListNode(offSetStart+i+1);
        }

        //now join them 0--8 as last one link will automatically be pointing to null
        for(int i=0;i<size-1;i++){
            nodes[i].next = nodes[i+1];
        }

        return nodes[0];//return as head
    }

    public static ListNode getLinkedListOf(int size){
        return getLinkedListOf(size,0);
    }

    public static ListNode getLinkedListFromArray(int[] ar) {
        ListNode head = null;
        ListNode temp = null;
        for (int i = 0; i < ar.length; i++) {
            ListNode node = new ListNode(ar[i]);
            if (head == null) {
                head = node;
                temp = node;
            } else {
                temp.next = node;
                temp = temp.next;
            }
        }
        return head;
    }

    public static void printLinkedList(ListNode head){
        System.out.println();
        while (head!=null){
            System.out.print(head.val + " -> ");
            head=head.next;
        }

        System.out.print("X");
    }

    @Override
    public String toString() {
        return val+"";
    }
}
