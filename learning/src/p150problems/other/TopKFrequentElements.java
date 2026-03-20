package p150problems.other;

import java.util.*;

public class TopKFrequentElements {
    /*
    Approach:
    1. Count frequency of each number using HashMap.
    2. Maintain a min-heap of size k storing the current top k frequent elements.
    3. If heap size is less than k, add current element.
    4. Otherwise, compare current frequency with heap top:
       - if current is more frequent, remove heap top and insert current
       - else skip it
    5. At the end, heap contains the k most frequent elements.

    Complexity:
    - Time: O(n + u log k), where u = number of unique elements
    - Space: O(u + k)

    Note:
    - Better than sorting all elements when k is small.
    - Worst case can still become O(n log n) if k is close to n.

    ab is se better complexity chaiye toh bucket sorting use kr lena
    intiution: max frequency can be length og nums when all elements are same
    so we can make bucket[nums.length] of frequency to values in array list
    e.g bucket[3]-> array_list(23,41) means 23 and 41 are present 3 times
    now traverse from behind of bucket and collect k elements only
    */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Node> minHeap =
                new PriorityQueue<>((a, b) -> Integer.compare(a.frequency, b.frequency));

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            Node currentNode = new Node(entry.getKey(), entry.getValue());
            if (minHeap.size() < k) {
                minHeap.offer(currentNode);
            } else if (minHeap.peek().frequency < currentNode.frequency) {
                minHeap.poll();
                minHeap.offer(currentNode);
            }
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = minHeap.poll().val;
        }

        return ans;
    }

    static class Node {
        public int val;
        public int frequency;

        Node(int v, int f) {
            val = v;
            frequency = f;
        }
    }

    public static void main(String[] s) {
        int input[] = new int[]{1, 2, 1, 2, 1, 2, 3, 1, 3, 2};
        int k = 2;
        System.out.println(Arrays.toString(new TopKFrequentElements().topKFrequent(input, k)));
    }
}
