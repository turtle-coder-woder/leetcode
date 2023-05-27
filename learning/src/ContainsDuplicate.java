import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Map<Integer,Boolean> map = new HashMap<>();
        for(int i=0; i <nums.length; i++){
            if(map.get(nums[i])!=null){
                return true;
            }
            map.put(nums[i], true);
        }
        return false;
    }
}
