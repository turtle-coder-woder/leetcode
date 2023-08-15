import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i) //number from i which are greater than 0 cant form sol, asn i,j,k>0 can form 0
            if (i == 0 || nums[i - 1] != nums[i]) { // for case [0,0,0,0,0,0] & for case [-2,-2,-2,-2,1,1]
                twoSum(nums, i, result);
            }
        return result;
    }
    void twoSum(int[] nums, int i, List<List<Integer>> result) {
        int j = i + 1, k = nums.length - 1;
        while (j < k) {
            int sum = nums[i] + nums[j] + nums[k];
            if (sum < 0) {
                ++j;
            } else if (sum > 0) {
                --k;
            } else {
                result.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
                while (j < k && nums[j] == nums[j - 1]){
                    ++j; // as we dont want duplicates from same i,(J),k
                }
            }
        }
    }
}
