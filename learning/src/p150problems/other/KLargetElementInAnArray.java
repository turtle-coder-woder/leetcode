package p150problems.other;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class KLargetElementInAnArray {
    /*
     * To find k largest element, we can make a min heap and keep it just to size k
     * if a new elements comes,
     * we can just check if heap.root value is less than current-> delete root (which will fix heapify internally) and add new element
     * in the last our ans will be automatically on heap.root after processing all nums
     * */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.offer(num);
            } else if (minHeap.peek() < num) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }

        return minHeap.poll();//leetcode says atleast 1 element will always be there and k>=1
    }

    public static void main(String[] s) {
        int input[] = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        System.out.println(new KLargetElementInAnArray().findKthLargest(input, k));
    }
}
