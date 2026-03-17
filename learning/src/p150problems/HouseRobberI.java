package p150problems;

public class HouseRobberI {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length+2];
        //fill array
        for(int i=2;i<dp.length;i++){
            dp[i]=nums[i-2];
        }

        //compute ans
        for(int i=2;i<dp.length;i++){
            dp[i] = Math.max((dp[i]+dp[i-2]),dp[i-1]);
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args){
        int[] houses = new int[]{1,2,3,1};
        int ans = new HouseRobberI().rob(houses);
        System.out.println(ans);
    }
}
