public class RobHouse {
    public int rob(int[] nums) {
        int numsLength = nums.length; // as per question atleast 1 element will be present
        int dp[] = new int[numsLength + 1];

        //each individual house is the answer if they were only present
        for(int i=1; i <numsLength+1; i++){
            dp[i] = nums[i-1];
        }

        for (int i = 2; i < numsLength+1; i++) {
            int currentHouseMoney = dp[i];
            int profitAfterRobbingCurrentHouse = currentHouseMoney + dp[i - 2];
            int profitIfCurrentHouseIsNotRobbed = dp[i - 1];
            dp[i] = Math.max(profitAfterRobbingCurrentHouse, profitIfCurrentHouseIsNotRobbed);
        }
        return dp[numsLength];
    }
}
