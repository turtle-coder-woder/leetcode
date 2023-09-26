import java.util.*;
import java.util.stream.Collectors;

public class GenerateSubsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        List<Integer> current = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            traverse(i, ans, current, nums);
        }
        return ans;
    }

    private void traverse(int i, List<List<Integer>> ans, List<Integer> current, int[] nums) {
        if (i >= nums.length) {
            return;
        }

        current.add(nums[i]);
        ans.add(current.stream().collect(Collectors.toList()));
        for (int j = i + 1; j < nums.length; j++) {
            traverse(j, ans, current, nums);
        }
        current.remove(current.size() - 1);
    }
}
