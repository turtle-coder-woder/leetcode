package p150problems.other;

public class JumpGames {
    /*
     * Mental Model:
     * - We only care about the farthest index reachable so far.
     * - If current index becomes greater than maxReach, that means this index is unreachable.
     * - Otherwise, update maxReach using current index + jump length from here.
     * - If maxReach reaches or crosses the last index, return true.
     *
     * Why Greedy works:
     * - We do not need to try every path.
     * - We only need to know the farthest boundary we can reach while moving left to right.
     *
     * Complexity:
     * - Time: O(n)
     * - Space: O(1)
     *
     *
     * DP version idea:
     * - From each index, try all possible jumps.
     * - If any jump can reach the end, then current index can also reach the end.
     * - Use memo to avoid recomputing the same index again and again.
     *
     * DP Complexity:
     * - Time: O(n^2)
     * - Space: O(n)
     */
    public boolean canJump(int[] nums) {
//        Boolean[] canJumpMemo = new Boolean[nums.length];
//        return canJumpHelper(nums, canJumpMemo, 0);
        return canJumpGreedy(nums);
    }

    private boolean canJumpGreedy(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (maxReach >= nums.length - 1) {
                return true;
            }
            if (i > maxReach) {
                return false;
            }
            int currentMaxJump = nums[i];
            maxReach = Math.max(maxReach, i+currentMaxJump);
        }
        return true;
    }

    private boolean canJumpHelper(int[] nums, Boolean[] memo, int index) {
        if (index >= memo.length - 1) {
            return true;
        }

        if (memo[index] != null) {
            return memo[index];
        }

        int maxJumpAllowed = nums[index];

        for (int i = maxJumpAllowed; i >= 1; i--) {
            if (canJumpHelper(nums, memo, index + i)) {
                memo[index] = true;
                return true;
            }
        }

        memo[index] = false;
        return false;
    }

    public static void main(String[] args) {
        int[] arTrue = new int[]{2, 3, 1, 1, 4};
        System.out.println(new JumpGames().canJump(arTrue));
        int[] arFalse = new int[]{3,2,1,0,4};
        System.out.println(new JumpGames().canJump(arFalse));
    }
}
