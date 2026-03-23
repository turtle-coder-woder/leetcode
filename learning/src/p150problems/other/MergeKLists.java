    package p150problems.other;

    import p150problems.helper.ListNode;

    import java.util.Comparator;
    import java.util.List;
    import java.util.PriorityQueue;
    /*
    MENTAL MODEL:

    - Har list sorted hai → har list ka current head hi valid candidate hai
    - Heap me hamesha "k candidates" rahenge (ek har list se)

    FLOW:
    - Sab lists ke head heap me daal do
    - Jab tak heap empty nahi:
        1. Sabse chhota node (global min) nikaalo
        2. Usko answer list me jodo
        3. Us node ka next (same list ka next element) heap me daal do
        4. Old next link tod do (ptr.next = null) taaki list clean bane

    CORE IDEA:
    - "One out → one in"
      (heap se jo nikla, uska next heap me gaya)

    WHY IT WORKS:
    - Kyuki har list sorted hai → agar current node consider nahi hua,
      toh uske baad wale nodes kabhi consider nahi ho sakte

    RESULT:
    - Har step pe global minimum milta hai → final merged sorted list

    COMPLEXITY:
    - Time: O(N log k)
    - Space: O(k)
    */
    public class MergeKLists {
        public ListNode mergeKLists(ListNode[] lists) {
            ListNode head = null;
            ListNode ptr = null;
            PriorityQueue<ListNode> minHeap = new PriorityQueue<>(new Comparator<ListNode>() {
                @Override
                public int compare(ListNode o1, ListNode o2) {
                    return Integer.compare(o1.val, o2.val);
                }
            });
            int k = lists.length - 1;
            //fill once untill k elements
            for (ListNode x : lists) {
                if (x != null) {
                    minHeap.offer(x);
                }
            }


            while (!minHeap.isEmpty()) {
                ListNode poppedNode = minHeap.poll();
                if (head == null) {
                    head = poppedNode;
                    ptr = poppedNode;
                } else {
                    ptr.next = poppedNode;
                    ptr = ptr.next;
                }
                if (ptr != null) {
                    if (ptr.next != null) {
                        minHeap.offer(ptr.next);
                    }
                    ptr.next = null;
                }

            }

            return head;
        }

        public static void main(String[] args) {
            ListNode head = ListNode.getLinkedListFromArray(new int[]{1, 4, 5});
            ListNode head2 = ListNode.getLinkedListFromArray(new int[]{1, 3, 4});
            ListNode head3 = ListNode.getLinkedListFromArray(new int[]{2, 6});
            ListNode[] lar = new ListNode[]{head, head2, head3};
            ListNode output = new MergeKLists().mergeKLists(lar);
            ListNode.printLinkedList(output);
        }
    }
