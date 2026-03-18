package p150problems;

import easyCollection.TreeNode;

public class HouseRobber3 {
    /*
    Tree DP with 2 states per node:
    rob  = rob this node, so children must be skipped
    skip = skip this node, so each child can be robbed or skipped

    Transitions:
    rob  = node.val + left.skip + right.skip
    skip = max(left.rob, left.skip) + max(right.rob, right.skip)

    Why not level sum?
    Because different subtrees may prefer different choices
    (one may prefer child, another may prefer grandchild).
    */
    public int rob(TreeNode root) {
        Result result = dfs(root);
        return Math.max(result.robThis, result.skipThis);
    }

    private Result dfs(TreeNode root) {
        if (root == null) {
            return new Result();
        }

        Result leftResult = dfs(root.left);
        Result rightResult = dfs(root.right);

        Result currentResult = new Result();
        currentResult.robThis = root.val + leftResult.skipThis + rightResult.skipThis;
        currentResult.skipThis = Math.max(leftResult.robThis, leftResult.skipThis)
                + Math.max(rightResult.robThis, rightResult.skipThis);

        return currentResult;
    }

    private static class Result {
        int robThis;
        int skipThis;
    }
}