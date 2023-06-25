import helper.ListNode;

public class IsPalindromeLinkedList {
    ListNode globalSecondList = null;

    public boolean isPalindrome(ListNode head) {
        int size = getSize(head);
        int firstHalfSize = size / 2;
        int secondHalfSize = (size % 2 == 0) ? firstHalfSize : firstHalfSize + 1;

        globalSecondList = getSecondListFromHead(head, secondHalfSize);

        return isPalHelper(head, firstHalfSize);
    }

    private ListNode getSecondListFromHead(ListNode head, int secondHalf) {
        int counter = 0;
        ListNode secondList = head;
        while (counter != secondHalf) {
            secondList = secondList.next;
            counter++;
        }
        return secondList;
    }

    private int getSize(ListNode head) {
        int size = 0;
        ListNode ptr = head;
        while (ptr != null) {
            ptr = ptr.next;
            size++;
        }
        return size;
    }

    boolean isPalHelper(ListNode firstList, int comparisonLeft) {
        if (comparisonLeft == 0) {
            return true;
        }
        if (isPalHelper(firstList.next, --comparisonLeft)) {
            return checkMidToMidPlusOneOfGlobalSecondHalf(firstList);
        }
        return false;
    }

    boolean checkMidToMidPlusOneOfGlobalSecondHalf(ListNode firstList) {
        boolean currStatus = firstList.val == globalSecondList.val;
        globalSecondList = globalSecondList.next;
        return currStatus;
    }
}
