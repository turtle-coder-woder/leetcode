package p150problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> runningAns = new ArrayList<>();
        int runningSum = 0;
        int index = 0;
        backtrack(candidates, target, index, runningSum, ans, runningAns);
        return ans;
    }

    void backtrack(int[] candidates, int target, int index, int runningSum, List<List<Integer>> ans, List<Integer> runningAns) {
        //[2,3,6,7] , target=7
        if (runningSum == target) {
            ans.add(new ArrayList<>(runningAns));
            return;
        }

        if (runningSum > target || index >= candidates.length) {
            return;
        }


        //pick
        runningSum += candidates[index];
        runningAns.add(candidates[index]);
        backtrack(candidates, target, index, runningSum, ans, runningAns); //dont change index as we can picking same value again
        //unpick
        runningSum -= candidates[index];
        runningAns.remove(runningAns.size() - 1);
        backtrack(candidates, target, index + 1, runningSum, ans, runningAns); //change index as we  have done picking above candidate, its time to move on
    }

    public static void main(String[] args) {
        int ar[] = new int[]{2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> ans = new CombinationSum().combinationSum(ar, target);
        System.out.println();
        for (List<Integer> an : ans) {
            an.forEach(x -> System.out.print(x + ","));
            System.out.println();
        }
    }
}
