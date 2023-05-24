import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class ZIGZAGLEVELORDER {
    public enum DIRECTION {
        left, right
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        return traversal(root);
    }

    public List<List<Integer>> traversal(TreeNode root) {
        DIRECTION direction = DIRECTION.right;
        LinkedList<TreeNode> listOfTreeNode = new LinkedList<>();
        listOfTreeNode.add(root);

        List<List<Integer>> ans = new ArrayList<>();


        while (!listOfTreeNode.isEmpty()) {
            direction = changeDirection(direction);
            listOfTreeNode = consumeOneLevel(direction, listOfTreeNode, ans);
        }
        return ans;
    }

    public DIRECTION changeDirection(DIRECTION direction) {
        if (direction == DIRECTION.right) {
            return DIRECTION.left;
        }
        return DIRECTION.right;
    }

    public LinkedList<TreeNode> consumeOneLevel(DIRECTION direction, LinkedList<TreeNode> listOfTreeNode, List<List<Integer>> ans) {
        LinkedList<TreeNode> anotherLevelListOfNodes = new LinkedList<>();
        List<Integer> singleLevelAns = new ArrayList<>();

        while (!listOfTreeNode.isEmpty()) {
            TreeNode node = direction == DIRECTION.left ? listOfTreeNode.removeFirst() : listOfTreeNode.removeLast();
            singleLevelAns.add(node.val);
            addNodeChildToNextQueue(node, direction, anotherLevelListOfNodes);
        }
        ans.add(singleLevelAns);
        return anotherLevelListOfNodes;
    }

    public void addNodeChildToNextQueue(TreeNode node, DIRECTION direction, LinkedList<TreeNode> anotherLevelListOfNodes) {
        TreeNode left = node.left;
        TreeNode right = node.right;
        if (direction == DIRECTION.left) {
            if (left != null) {
                anotherLevelListOfNodes.add(left);
            }
            if (right != null) {
                anotherLevelListOfNodes.add(right);
            }
        } else {
            if (right != null) {
                anotherLevelListOfNodes.addFirst(right);
            }
            if (left != null) {
                anotherLevelListOfNodes.addFirst(left);
            }
        }
    }
}