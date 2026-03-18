package p150problems;

public class HouseRobber2 {
    /*
    Circle => first and last are adjacent, so both cannot be taken together.

    Thus solve 2 linear robber problems:
    - [0 ... n-2]
    - [1 ... n-1]

    Take max of both.

    Linear DP:
    curr = max(prevOne, prevTwo + nums[i])
    where:
    prevTwo = best till i-2
    prevOne = best till i-1
    */
    public int rob(int[] houses) {
        if(houses.length==1){
            return houses[0];
        }

        int combination1=robHouse(houses,0,houses.length-2);
        int combination2=robHouse(houses,1,houses.length-1);
        return Math.max(combination1,combination2);
    }

    private int robHouse(int[] houses, int left, int right) {
        int prev1=0;
        int prev2=0;
        for(;left<=right;left++){
            int max = Math.max(prev2,prev1+houses[left]);
            prev1=prev2;
            prev2=max;
        }
        return prev2;
    }

    public static void main(String[] args){
        int[] houses = new int[]{1,2,3,1};
        int ans = new HouseRobber2().rob(houses);
        System.out.println(ans);
    }
}
