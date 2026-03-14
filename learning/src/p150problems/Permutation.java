package p150problems;
import java.util.*;
public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if(nums.length==0){
            return ans;
        }

        List<Integer> runningAns = new ArrayList<>();
        int index=0;
        boolean[] visited = new boolean[nums.length];
        backtrack(visited,nums,ans,runningAns);
        return ans;
    }

    void backtrack(boolean[] visited,int[] nums,List<List<Integer>> ans,List<Integer> runningAns){
        if(nums.length==runningAns.size()){
            ans.add(new ArrayList<>(runningAns));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(!visited[i]){
                //pick
                visited[i]=true;
                runningAns.add(nums[i]);
                backtrack(visited,nums,ans,runningAns);
                visited[i]=false;
                runningAns.remove(runningAns.size()-1);
            }
        }
    }
}
