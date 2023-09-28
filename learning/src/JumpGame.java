public class JumpGame {
    public boolean canJump(int[] nums) {
        int maxRange = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (i <= maxRange) {
                maxRange = Math.max(maxRange, i + nums[i]);
            } else {
                return false;
            }

        }
        return nums.length-1 <= maxRange;
    }
}
