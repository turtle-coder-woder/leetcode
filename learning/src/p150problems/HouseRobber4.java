package p150problems;

import java.util.Arrays;

public class HouseRobber4 {
    /*
    *This is binary search on answer problem
    * start binary search from 1 to max cash value capacity
    * for a value to be checked , do this -> in nums select values only <=value and than check if the house are not adjacent
    * the return value should be <=k for to be our ans
    * */
    public int minCapability(int[] nums, int k) {
        int left=1;
        int right = Arrays.stream(nums).max().getAsInt();
        while(left<right){
            int mid= left +((right-left)/2);
            if(canRob(nums,k,mid)){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left;
    }

    private boolean canRob(int[] nums, int k, int maxCashCapacity) {
        int housesCanBeRobbed = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]<=maxCashCapacity){
                housesCanBeRobbed++;
                i++;//cz we can't rob adjacent house
            }
        }
        return housesCanBeRobbed>=k;
    }
}
