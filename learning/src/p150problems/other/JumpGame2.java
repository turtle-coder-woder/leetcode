package p150problems.other;

import java.util.*;

public class JumpGame2 {

    /*
     * JUMP GAME II — UNIFIED MENTAL MODEL
     *
     * Goal:
     * - Minimum jumps chahiye to reach last index.
     * - Har index se 1 to nums[i] tak jump kar sakte hain.
     *
     * ------------------------------------------------------------
     * THINKING EVOLUTION (same problem, 3 perspectives)
     * ------------------------------------------------------------
     *
     * 1) BRUTE FORCE (try all paths)
     * - Har index se sab possible jumps try karo.
     * - Har path ka jump count nikal ke minimum lo.
     * - Basically recursion tree explore kar rahe ho.
     *
     * Intuition:
     * - "Har possible path me se best kaun sa hai?"
     *
     * Complexity:
     * - Time: Exponential (O(2^n)) — TLE
     * - Space: recursion stack
     *
     * ------------------------------------------------------------
     *
     * 2) DP (Top-down / Bottom-up)
     *
     * Core DP state:
     * - dp[i] = minimum jumps needed from index i to reach end
     *
     * Transition:
     * - dp[i] = 1 + min(dp[i + 1], dp[i + 2], ..., dp[i + nums[i]])
     *
     * Intuition:
     * - "Agar main next kisi index par jaun, to wahan se end tak kitna cost lagega?"
     * - Same index baar baar aayega → memoization use karo
     *
     * Alternate bottom-up view:
     * - dp[i] = min jumps to reach index i from start
     *
     * Complexity:
     * - Time: O(n^2)
     * - Space: O(n)
     *
     * ------------------------------------------------------------
     *
     * 3) GREEDY (optimal)
     *
     * Core idea:
     * - Exact path choose mat karo
     * - Current jump me jo poori range reachable hai,
     *   us range ke andar se next jump ke liye best boundary choose karo
     *
     * Variables:
     * - currentEnd = current jump se max boundary
     * - farthest = next jump ke liye best boundary
     *
     * Process:
     * - Traverse array left to right
     * - Har index pe update:
     *      farthest = max(farthest, i + nums[i])
     * - Jab i == currentEnd:
     *      jump lena hi padega (range khatam ho gayi)
     *      jump++
     *      currentEnd = farthest
     *
     * Intuition:
     * - Har jump ek BFS level jaisa hai
     * - Hum exact landing point nahi, best range expansion choose kar rahe hain
     *
     * Complexity:
     * - Time: O(n)
     * - Space: O(1)
     *
     * ------------------------------------------------------------
     *
     * FINAL INTUITION:
     * - Brute: explore all paths
     * - DP: overlapping subproblems ko cache karo
     * - Greedy: range ko expand karte jao, path choose karne ki zarurat nahi
     *
     * Interview takeaway:
     * - DP shows understanding
     * - Greedy shows mastery
     */
    public int jump(int[] nums) {
//        return minJumpBruteForce(nums);
//        return minJumpDp(nums);
        return minJumpGreedy(nums);
    }


    private int minJumpGreedy(int[] nums) {
        int farthestjump = 0;
        int currentEnd = 0;
        int jump = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthestjump = Math.max(farthestjump, i + nums[i]);//till which max index point we can go using current index
            if (i == currentEnd) {//if we have reached end of our starting range, it means now its time to make jump
                //though we dont know from which point in [currentEnd,last-farthestJump], but for sure mathematically we will have to leave this range to reach next range
                jump++;
                //and make our new boundry
                currentEnd = farthestjump;
            }
        }
        return jump;
    }


    private int minJumpDp(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE); //say we cant reach to end any how in the start
        //now say if i am standing at last, i can reach last by doing 0 jump
        dp[nums.length - 1] = 0;
        int startIndex = 0;
        //now ask whats the min cost to reach end from startIndex
        return minJumpDpHelper(nums, startIndex, dp);
    }

    private int minJumpDpHelper(int[] nums, int startIndex, int[] dp) {
        if (startIndex > nums.length - 1) {
            return 0;
        }
        if (dp[startIndex] != Integer.MAX_VALUE) {
            return dp[startIndex];
        }

        int maxJump = nums[startIndex];
        for (int x = maxJump; x >= 1; x--) {
            int newCost = minJumpDpHelper(nums, startIndex + x, dp);
            if (newCost != Integer.MAX_VALUE) {
                dp[startIndex] = Math.min(1 + newCost, dp[startIndex]);
            }

        }
        return dp[startIndex];
    }

    int minJumpBruteForce(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        //brute force
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums[i]; j >= 1; j--) {
                int newIndex = j + i;
                if (newIndex < nums.length) {
                    dp[newIndex] = Math.min(dp[i] + 1, dp[newIndex]);
                }

            }
        }
        return dp[nums.length - 1];
    }


    public static void main(String[] args) {
        int[] arTrue = new int[]{2, 3, 1, 1, 4};
//        t[] arTrue = new int[]{0, 3, 1, 1, 4,0,0};
        System.out.println(new JumpGame2().jump(arTrue));
        int[] arFalse = new int[]{2, 3, 0, 1, 4};
        System.out.println(new JumpGame2().jump(arFalse));
        int[] ar = new int[]{0};
        System.out.println(new JumpGame2().jump(ar));
        int[] ar2 = new int[]{5, 6, 4, 4, 6, 9, 4, 4, 7, 4, 4, 8, 2, 6, 8, 1, 5, 9, 6, 5, 2, 7, 9, 7, 9, 6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8, 5};
        System.out.println(new JumpGame2().jump(ar2));
    }
}
