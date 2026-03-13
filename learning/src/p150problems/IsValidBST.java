package p150problems;

import easyCollection.TreeNode;

public class IsValidBST {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class Solution {

        // notice that we use long because if root is exactly Integer.MIN_VALUE the things can fail
        public boolean isValidBST(TreeNode root) {
            if(root==null){
                return true;
            }

            return isValidBSTHelper(root,Long.MIN_VALUE,Long.MAX_VALUE);
        }

        private boolean isValidBSTHelper(TreeNode root, long minRange, long maxRange) {
            if(root==null){
                return true;
            }

            if(root.val>minRange && root.val<maxRange){
                return isValidBSTHelper(root.left,minRange,root.val) &&
                        isValidBSTHelper(root.right,root.val,maxRange);
            }
            return false;
        }
    }
}
