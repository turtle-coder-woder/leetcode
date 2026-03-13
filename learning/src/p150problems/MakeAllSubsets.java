package p150problems;

import java.util.ArrayList;

import java.util.List;

public class MakeAllSubsets {

    /*
    ===============================
    SUBSETS / POWERSET BACKTRACKING
    ===============================

    Goal:
    Generate all subsets of given array nums.
    Total subsets = 2^n because every element has 2 choices:
        pick it OR skip it.

    --------------------------------
    Important Concepts
    --------------------------------

    1) No visited[] needed
    For subsets we don't need visited[] because we always move forward
    using index i+1. This ensures:
        - elements are not reused
        - order is preserved
        - no duplicate permutations are generated

    The index itself controls the future choices.

    --------------------------------
    Two Valid Backtracking Patterns
    --------------------------------

    1) Pick / Not-Pick (Binary Decision Tree)

    At each index we ask:
        "Should I include nums[i]?"

    Two recursive branches:
        pick nums[i]
        skip nums[i]

    Tree example for nums = [1,2,3]:

                    []
               /          \
            pick1       skip1
            [1]            []
           /   \          /   \
       pick2  skip2   pick2  skip2
       [1,2]   [1]     [2]     []
       ...

    Base case:
        if i == nums.length
            subset is complete → store copy

    --------------------------------
    2) Loop-Based Backtracking (Most Common)

    Idea:
    At every recursion level, the current runningAns itself
    represents a valid subset.

    So first store it:
        ans.add(copy)

    Then try adding every possible next element.

    Pseudo flow:

    start with []

    [] → store
    pick 1 → [1]
    pick 2 → [1,2]
    pick 3 → [1,2,3]
    backtrack → [1,2]
    backtrack → [1]
    pick 3 → [1,3]
    backtrack → [1]
    backtrack → []
    pick 2 → [2]
    pick 3 → [2,3]
    backtrack → [2]
    backtrack → []
    pick 3 → [3]

    --------------------------------
    Backtracking Core Principle
    --------------------------------

    Every backtracking problem follows:

        choose
        explore
        undo

    Code pattern:

    runningAns.add(choice)    // choose
    recurse(...)              // explore
    runningAns.remove(...)    // undo

    Undo step restores state for next branch.

    --------------------------------
    Very Important Implementation Detail
    --------------------------------

    Always store a COPY of runningAns:

        ans.add(new ArrayList<>(runningAns))

    Never do:
        ans.add(runningAns)

    Because runningAns keeps changing during recursion
    and all answers would end up referencing the same list.

    --------------------------------
    Key Mental Model
    --------------------------------

    Subsets = Binary decisions for each element.

    For [1,2,3]:

    000 → []
    001 → [3]
    010 → [2]
    011 → [2,3]
    100 → [1]
    101 → [1,3]
    110 → [1,2]
    111 → [1,2,3]

    Backtracking simply explores this decision tree.
    */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length == 0) {
            return ans;
        }
        backtrackWithLoop(0,nums,ans,new ArrayList<Integer>());
        return ans;
    }

    private void backtrackWithLoop(int i, int[] nums, List<List<Integer>> ans,
                                   ArrayList<Integer> runningAns) {
        ans.add(new ArrayList<>(runningAns));

        for(;i<nums.length;i++){
            runningAns.add(nums[i]);
            backtrackWithLoop(i+1,nums,ans,runningAns);
            runningAns.remove(runningAns.size()-1);
        }
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
