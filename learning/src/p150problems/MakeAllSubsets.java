package p150problems;

import java.util.ArrayList;

import java.util.List;

public class MakeAllSubsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length == 0) {
            return ans;
        }
        backtrackWithoutLoop(0,nums,ans,new ArrayList<Integer>());
        return ans;
    }

    private void backtrackWithoutLoop(int i,int[] nums, List<List<Integer>> ans,
                                                     ArrayList<Integer> runningAns) {
        if(i>=nums.length){
            ans.add(new ArrayList<>(runningAns));
            return;
        }

        //pick
        runningAns.add(nums[i]);
        backtrackWithoutLoop(i+1,nums,ans,runningAns);

        //unpick
        runningAns.remove(runningAns.size()-1);
        backtrackWithoutLoop(i+1,nums,ans,runningAns);
    }


    public static void main(String[] args) {
        int[] ar = new int[]{1, 2, 3};
        List<List<Integer>> ans = new MakeAllSubsets().subsets(ar);
        System.out.println();
        for (List<Integer> an : ans) {
            an.forEach(x -> System.out.print(x + ","));
            System.out.println();
        }
    }

}
