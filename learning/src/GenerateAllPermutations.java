import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateAllPermutations {
    List<List<Integer>> ans = null;

    public List<List<Integer>> permute(int[] nums) {
        ans = new ArrayList<>();
        int[] indexArray = new int[nums.length];
        for (int i = 0; i < indexArray.length; i++) {
            indexArray[i] = -1;
        }
        generate(nums, indexArray, 0);
        return ans;
    }

    void generate(int[] nums, int[] indexArray, int currentIndex) {
        if (currentIndex >= nums.length) {
            ans.add(getArrayList(nums, indexArray));
            return;
        }

        for (int i = 0; i < indexArray.length; i++) {
            if (indexArray[i] == -1) {
                indexArray[i] = currentIndex;
                generate(nums, indexArray, currentIndex + 1);
                indexArray[i] = -1;
            }
        }


    }

    List<Integer> getArrayList(int[] nums, int[] indexArray) {
        List<Integer> a = new ArrayList<>();
        for (Integer index : indexArray) {
            a.add(nums[index]);
        }
        return a;
    }
}
