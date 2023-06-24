import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinaryTreeLevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ansList = new ArrayList<>();
        List<TreeNode> innerLevel = new ArrayList<>();
        if (root != null) {
            innerLevel.add(root);
        }
        while (!innerLevel.isEmpty()) {
            List<Integer> innerIntgerLevel = new ArrayList<>();
            List<TreeNode> nextInnerLevel = new ArrayList<>();
            for (TreeNode node : innerLevel) {
                innerIntgerLevel.add(node.val);
                pushToList(node, nextInnerLevel);
            }
            ansList.add(innerIntgerLevel);
            innerLevel = nextInnerLevel;
        }
        return ansList;
    }

    void pushToList(TreeNode node, List<TreeNode> nextInnerLevel) {
        if (node.left != null) {
            nextInnerLevel.add(node.left);
        }
        if (node.right != null) {
            nextInnerLevel.add(node.right);
        }
    }
}
