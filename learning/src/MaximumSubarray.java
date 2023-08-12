public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int globalmax = -100000;
        int max = -100000;
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            if (current > max) {
                max = Math.max(current, max + current);
            } else {
                max += current;
            }
            globalmax = Math.max(max, globalmax);
        }
        return Math.max(max, globalmax);
    }
}
