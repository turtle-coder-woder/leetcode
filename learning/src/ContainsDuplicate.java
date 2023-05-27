import java.util.Arrays;

public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        //since nums.length>1 as given in input constraint, we can safely use first val of arr
        int prev = nums[0];
        for(int i=1; i <nums.length; i++){
            if(prev == nums[i]){
                return true;
            }
            prev = nums[i];
        }
        return false;
    }
}
