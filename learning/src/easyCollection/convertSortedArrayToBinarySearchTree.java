package easyCollection;

public class convertSortedArrayToBinarySearchTree {
    //https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/editorial/
    public TreeNode sortedArrayToBST(int[] nums) {
        // since nums will always have atleast 1 number so we can proceed
        return makeNode(nums, 0, nums.length - 1, new TreeNode());
    }

    public TreeNode makeNode(int[] nums, int low, int high, TreeNode node) {
        if (low > high || low < 0) {
            return null;
        }
        int mid = low + (high - low) / 2;
        node.val = nums[mid];
        node.left = makeNode(nums, low, mid - 1, new TreeNode());
        node.right = makeNode(nums, mid + 1, high, new TreeNode());
        return node;
    }
}
