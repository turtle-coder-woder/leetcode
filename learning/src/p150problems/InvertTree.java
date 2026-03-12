package p150problems;

import easyCollection.TreeNode;

public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if(root==null){
            return root;
        }
        TreeNode leftTemp = root.left;
        root.left=root.right;
        root.right=leftTemp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public static void main(String[] args){
        TreeNode node = new TreeNode(4);
        node.left=new TreeNode(2);
        node.right=new TreeNode(7);
        node.left.left=new TreeNode(1);
        node.left.right=new TreeNode(3);
        node.right.left=new TreeNode(6);
        node.right.right=new TreeNode(9);
        TreeNode.printTree(node);
        TreeNode.printTree(new InvertTree().invertTree(node));
    }
}
