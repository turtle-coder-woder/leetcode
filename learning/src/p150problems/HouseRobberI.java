package p150problems;

public class HouseRobberI {
    public int rob(int[] nums) {
        int prev2 = 0; // i-2 tak ka best
        int prev1 = 0; // i-1 tak ka best
        for(int num : nums){
            int curr = Math.max(num+prev2,prev1);
            prev2=prev1;
            prev1=curr;
        }
        return prev1;
    }

    public static void main(String[] args){
        int[] houses = new int[]{1,2,3,1};
        int ans = new HouseRobberI().rob(houses);
        System.out.println(ans);
    }
}
