package p150problems;

import java.util.ArrayList;
import java.util.List;

public class Combination {
    public List<List<Integer>> combine(int n, int k) {
        int ar[]=new int[n];
        for(int i=0;i<n;i++){
            ar[i]=i+1;
        }
        int start=0;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> runningSum = new ArrayList<>();
        backTrack(ar,start,k,ans,runningSum);
        return ans;
    }

    private void backTrack(int[] nums, int start, int k, List<List<Integer>> ans, List<Integer> runningSum) {
        //ye pehle add krna jaruri hai ni toh proper ans ni aayega
        if(runningSum.size()==k){
            ans.add(new ArrayList<>(runningSum));
            return;
        }

        if(start>=nums.length){
            return ;
        }


        runningSum.add(nums[start]);
        backTrack(nums,start+1,k,ans,runningSum);
        runningSum.remove(runningSum.size()-1);
        backTrack(nums,start+1,k,ans,runningSum);
    }

    public static void main(String[] args) {
        int n=4;
        int k=2;
        List<List<Integer>> ans = new Combination().combine(n,k);
        System.out.println();
        for (List<Integer> an : ans) {
            an.forEach(x -> System.out.print(x + ","));
            System.out.println();
        }
    }
}
