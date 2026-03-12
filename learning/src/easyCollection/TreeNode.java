package easyCollection;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void printTree(TreeNode node) {
        if (node == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Queue<TreeNode> queue2 = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode temp = queue.poll();
                System.out.print(temp + ",");
                if (temp != null) {
                    queue2.add(temp.left);
                    queue2.add(temp.right);
                }
            }
            System.out.println();
            queue = queue2;
        }
    }

    @Override
    public String toString() {
        return " " + val + " ";
    }
}
