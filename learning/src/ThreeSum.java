import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        if (nums.length < 3) {
            return ans;
        }

        Arrays.sort(nums);
        int i = 0, j, k = nums.length - 1;
        while (k - i > 1) { //will move j in between them
            j = i + 1;
            int miniK = k;
            while (miniK - j > 0) {
                int sum = nums[i] + nums[j] + nums[miniK];
                if (sum == 0) {
                    ArrayList<Integer> subList = new ArrayList();
                    subList.add(nums[i]);
                    subList.add(nums[j]);
                    subList.add(nums[miniK]);
                    if (!ans.contains(subList)) {
                        ans.add(subList);
                    }
                    miniK--;
                } else if (sum < 0) {
                    j++;
                } else {
                    miniK--;
                }
            }

            i++;
        }

        return ans;
    }
}
