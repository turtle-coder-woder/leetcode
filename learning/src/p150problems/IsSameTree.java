package p150problems;

import easyCollection.TreeNode;

public class IsSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null){
            return true;
        }
        if(p==null||q==null){
            return false;
        }
        if(p.val!=q.val){
            return false;
        }

        return isSameTree(p.left,q.left)&&
                isSameTree(p.right,q.right);
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
        System.out.println(new IsSameTree().isSameTree(node,node));
    }
}
