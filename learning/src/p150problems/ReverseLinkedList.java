package p150problems;

import p150problems.helper.ListNode;

public class ReverseLinkedList {
    /*
LINKED LIST REVERSAL MENTAL MODEL

Core thought:
Do NOT think "reverse the whole list".
Think: "let the smaller part get reversed first, then fix my own link."

For recursive full reverse:
reverse(head.next) will already reverse everything after current node.
So if I am at:
head -> next

and recursion has reversed the rest,
I only need to do:
head.next.next = head    // next node points back to me
head.next = null         // break my old forward link

Why this works:
Before fixing:
1 -> 2 -> 3 -> 4 -> null

After reverse(head.next) returns at node 1:
1 -> 2
4 -> 3 -> 2 -> null

Now I flip only my local edge:
2 -> 1
and cut old edge:
1 -> null

Result:
4 -> 3 -> 2 -> 1 -> null

Golden rule:
Recursion reverses the rest.
I only attach myself at the end.

Base case:
if head == null || head.next == null
return head

Things to remember:
1. head.next.next = head  => actual reversal
2. head.next = null       => prevent cycle
3. return newHead         => deepest node becomes final head

Pattern summary:
- go deep
- trust smaller problem solved
- fix one pointer
- return unchanged newHead

Complexity:
Time  O(n)
Space O(n) recursion stack

Warning:
If you forget head.next = null, you may create a cycle.
*/
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode newHead = reverseList(head.next);

        head.next.next = head; //for flip
        head.next = null;

        return newHead;
    }



    public static void main(String[] args){
        ListNode head = ListNode.getLinkedListOf(5);
        ListNode.printLinkedList(head);
        ListNode newHead = new ReverseLinkedList().reverseList(head);
        ListNode.printLinkedList(newHead);
    }
}
