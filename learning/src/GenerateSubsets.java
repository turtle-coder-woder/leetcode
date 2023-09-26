import java.util.*;

public class GenerateSubsets {
    final int SET = 1;
    final int UNSET = -SET;
    Set<String> set = null;

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        set = new HashSet<>();
        int[] maskArray = createMaskArray(nums.length);
        generateSubSets(ans, nums, maskArray, 0);
        return ans;
    }

    void generateSubSets(List<List<Integer>> ans, int[] nums, int[] maskArray, int currentlyMasked) {
        if (currentlyMasked > nums.length) {
            return;
        }

        if (collectAns(ans, nums, maskArray)) {
            for (int i = 0; i < maskArray.length; i++) {
                if (maskArray[i] == UNSET) {
                    maskArray[i] = SET;
                    generateSubSets(ans, nums, maskArray, currentlyMasked + 1);
                    maskArray[i] = UNSET;
                }
            }
        }

    }

    private boolean collectAns(List<List<Integer>> ans, int[] nums, int[] maskArray) {
        String hash = Arrays.stream(maskArray).mapToObj(String::valueOf).reduce((x, y) -> {
            return x + "#" + y;
        }).toString();
        if (set.contains(hash)) {
            return false;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < maskArray.length; i++) {
            if (maskArray[i] == SET) {
                result.add(nums[i]);
            }
        }
        ans.add(result);
        set.add(hash);
        return true;
    }

    int[] createMaskArray(int n) {
        int[] maskArray = new int[n];
        for (int i = 0; i < n; i++) {
            maskArray[i] = UNSET;
        }
        return maskArray;
    }
}
