package temp;

import easyCollection.TreeNode;

import java.util.*;

public class KthSmallestInBST {
    public int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k,Comparator.reverseOrder());
        traverseWithHeap(root, maxHeap, k);
        return maxHeap.peek();
    }

    void traverseWithHeap(TreeNode root, PriorityQueue<Integer> maxHeap, int size) {
        if (root == null) {
            return;
        }
        if (maxHeap.size() != size) {
            maxHeap.add(root.val);
        } else if (maxHeap.peek() > root.val) {
            maxHeap.remove();
            maxHeap.add(root.val);
        }
        traverseWithHeap(root.left, maxHeap, size);
        traverseWithHeap(root.right, maxHeap, size);
    }
}
