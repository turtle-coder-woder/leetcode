package ms;

import java.util.*;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        List<Integer> list = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i <nums.length; i++){
            Integer x = nums[i];
            Integer required = target - x;
            Integer requiredPos = map.getOrDefault(required,null);
            if(requiredPos!=null){
                return new int[]{i,requiredPos};
            }
            map.put(x,i);
        }
        return null;
    }
}
