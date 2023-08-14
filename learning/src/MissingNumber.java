public class MissingNumber {
    public int missingNumber(int[] nums) {
        int runningSum = 0;
        int actualToBeSum = 0;
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            actualToBeSum += i+1;
        }
        return actualToBeSum - runningSum;
    }
}
