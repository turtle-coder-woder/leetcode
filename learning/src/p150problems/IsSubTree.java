package p150problems;

import easyCollection.TreeNode;

public class IsSubTree {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root==null && subRoot==null){
            return true;
        }

        if(root==null || subRoot==null){
            return false;
        }

        if(root.val==subRoot.val && checkNowRootAndSubRoot(root,subRoot)){
                return true;
        }else{
            return isSubtree(root.left,subRoot)&&
                    isSubtree(root.right,subRoot);
        }
    }

    private boolean checkNowRootAndSubRoot(TreeNode root, TreeNode subRoot) {
        if(root==null && subRoot==null){
            return true;
        }

        if(root==null || subRoot==null){
            return false;
        }

        if(root.val!=subRoot.val){
            return false;
        }

        return checkNowRootAndSubRoot(root.left,subRoot.left) ||
                checkNowRootAndSubRoot(root.right,subRoot.right);

    }

    public static void main(String[] args){
        //no run was made in local pc
//        TreeNode node = new TreeNode(4);
//        node.left=new TreeNode(2);
//        node.right=new TreeNode(7);
//        node.left.left=new TreeNode(1);
//        node.left.right=new TreeNode(3);
//        node.right.left=new TreeNode(6);
//        node.right.right=new TreeNode(9);
//        TreeNode.printTree(node);
//        System.out.println(new IsSubTree().isSubtree(node,node));
    }
}
