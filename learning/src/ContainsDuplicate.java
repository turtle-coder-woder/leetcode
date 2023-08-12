import java.util.*;

public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> map = new HashSet<>();
        for(int i=0; i <nums.length; i++){
            if(!map.add(nums[i])){
                return true;
            }
        }
        return false;
    }

}
