package p150problems;

import easyCollection.TreeNode;

public class MaxDepthOfBinaryTree {
    /*
        MENTAL MODEL — Max Depth of Binary Tree

        Depth of a node = 1 + max(depth of left subtree, depth of right subtree)

        Steps:
        1. Ask left child its depth
        2. Ask right child its depth
        3. Take the larger one
        4. Add 1 for the current node

        Base case:
        null node contributes depth = 0

        Think:
        "My height = 1 + deeper of my two children."
    */
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        return 1+Math.max(maxDepth(root.left),
                maxDepth(root.right));
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
