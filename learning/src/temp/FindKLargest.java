package temp;

import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

public class FindKLargest {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue();
        int index = 0;
        for (; index < k; index++) {
            minHeap.add(nums[index]);
        }

        for (; index < nums.length; index++) {
            int currentElement = nums[index];
            if (minHeap.peek() < currentElement) {
                minHeap.remove();
                minHeap.add(currentElement);
            }
        }
        return minHeap.peek();
    }
}
