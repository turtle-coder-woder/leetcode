package ms;
import java.util.*;

public class FindDisappearedNumbers {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for(int i=0;i<nums.length;i++){
            int val = nums[i];
            if(val!=0 && (i+1) != val){
                nums[i]=0;
                mark(val,nums);
            }

        }
        List<Integer> ansList = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                ansList.add(i+1);
            }
        }
        return ansList;
    }

    private void mark(int val, int[] nums) {
        if(val!=0 && nums[val-1] != val){
            int tempVal = nums[val-1];
            nums[val-1] = val;
            mark(tempVal,nums);
        }
    }
}
