package p150problems;

import p150problems.helper.ListNode;

public class Merge2SortedLinkedList {
    /*
    MENTAL MODEL — Merge Two Sorted Linked Lists

    Core thought:
    Do NOT think "merge the whole two lists at once".

    Think:
    "Choose the smaller current node, and let recursion merge the rest."

    --------------------------------------------------
    What recursion means here
    --------------------------------------------------

    Suppose I have:

    list1 = 1 -> 5 -> 8
    list2 = 2 -> 15

    To decide the final merged head,
    I only compare the CURRENT heads:

        1 vs 2

    Smaller one must come first in answer.

    So:
    - pick 1
    - now recursively merge:
          5 -> 8
      with  2 -> 15

    That means:

        list1.next = mergeTwoLists(list1.next, list2)
        return list1

    --------------------------------------------------
    Golden rule
    --------------------------------------------------

    At every step:
    1. Compare current heads
    2. Smaller node wins
    3. Its next should point to merged result of remaining lists
    4. Return the winning node

    In short:

        smaller.next = merge(remaining part)
        return smaller

    --------------------------------------------------
    Base case
    --------------------------------------------------

    If one list becomes null,
    return the other list directly.

    Why?
    Because the remaining list is already sorted,
    so nothing more needs to be merged.

        if (list1 == null) return list2;
        if (list2 == null) return list1;

    --------------------------------------------------
    Why this works
    --------------------------------------------------

    Because both lists are already sorted.

    So among the two current heads,
    the smaller one is guaranteed to be the next correct node
    in the final merged list.

    After choosing that node,
    same smaller problem remains for the rest.

    --------------------------------------------------
    Pattern summary
    --------------------------------------------------

    - solve one node now
    - let recursion solve the rest
    - connect chosen node to recursive result
    - return chosen node

    Think:

    "I do not merge everything.
    I only decide who comes first."

    --------------------------------------------------
    Example
    --------------------------------------------------

    1 -> 5 -> 8
    2 -> 15

    Compare 1 and 2:
    pick 1

    Now solve:
    5 -> 8
    2 -> 15

    Compare 5 and 2:
    pick 2

    Now solve:
    5 -> 8
    15

    Compare 5 and 15:
    pick 5

    Now solve:
    8
    15

    Compare 8 and 15:
    pick 8

    Now solve:
    null
    15

    Base case:
    return 15

    Final:
    1 -> 2 -> 5 -> 8 -> 15

    --------------------------------------------------
    Complexity
    --------------------------------------------------

    Time: O(m + n)
    Each node is visited once

    Space: O(m + n) recursion stack
    (iterative version uses O(1) extra space)

    --------------------------------------------------
    Interview recall line
    --------------------------------------------------

    "Pick the smaller head, recursively merge the rest."
    */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1==null){
            return list2;
        }
        if(list2==null){
            return list1;
        }

        if(list1.val<list2.val){
            list1.next = mergeTwoLists(list1.next,list2);
            return list1;
        }else {
            list2.next = mergeTwoLists(list1,list2.next);
            return list2;
        }
    }

    public static void main(String[] args) {
        ListNode list1 = ListNode.getLinkedListFromArray(new int[]{1,5,8});
        ListNode list2 = ListNode.getLinkedListFromArray(new int[]{2,15});
        ListNode.printLinkedList(list1);
        ListNode.printLinkedList(list2);
        ListNode newHead = new Merge2SortedLinkedList().mergeTwoLists(list1, list2);
        ListNode.printLinkedList(newHead);
    }
}
