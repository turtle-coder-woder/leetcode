public class IsValidBST {
    public boolean isValidBST(TreeNode root) {
        return isNodeWithinRange(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isNodeWithinRange(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val >= max || root.val <= min) {
            return false;
        }
        return isNodeWithinRange(root.left, min, root.val) && isNodeWithinRange(root.right, root.val, max);
    }
}
