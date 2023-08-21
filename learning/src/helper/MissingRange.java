package helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissingRange {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> ans = new ArrayList<>();
        int expected = lower;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != expected) {
                int y = nums[i] - 1;
                ans.add(Arrays.asList(expected, y));
                expected = nums[i] + 1;
            } else {
                expected++;
            }
        }

        if (!(expected > upper)) {
            ans.add(Arrays.asList(expected, upper));
        }
        return ans;
    }
}
