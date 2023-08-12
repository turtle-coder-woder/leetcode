public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int globalmax = Integer.MIN_VALUE;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            max += current;

            if (max > globalmax) {
                globalmax = max;
            }
            if (max < 0) {
                max = 0;
            }
        }
        return globalmax;
    }
}
