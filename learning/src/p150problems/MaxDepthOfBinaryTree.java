package p150problems;

import easyCollection.TreeNode;

public class MaxDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        return maxDepthHelper(root,0);
    }

    private int maxDepthHelper(TreeNode root, int depth) {
        if(root==null){
            return depth;
        }

        int depthLeft = maxDepthHelper(root.left,depth+1);
        int depthRight = maxDepthHelper(root.right,depth+1);
        return Math.max(depthLeft,depthRight);
    }

    public static void main(String[] args){
        TreeNode node = new TreeNode(4);
        node.left=new TreeNode(2);
        node.right=new TreeNode(7);
        node.left.left=new TreeNode(1);
        node.left.right=new TreeNode(3);
//        node.right.left=new TreeNode(6);
//        node.right.right=new TreeNode(9);
        TreeNode.printTree(node);
        System.out.println((new MaxDepthOfBinaryTree().maxDepth(node)));
    }
}
