package p150problems;

import java.util.*;

public class CoinExchange {

    /*
    ==================== COIN CHANGE - MASTER MENTAL MODEL ====================

    Problem:
    Given coins[], find minimum number of coins to make amount.

    Core Recurrence:
    f(x) = 1 + min(f(x - coin))   for all coins

    Meaning:
    To make amount x → pick one coin + solve smaller subproblem

    --------------------------------------------------------------------------

    THINKING FLOW (VERY IMPORTANT):

    1. This is a "MINIMUM steps to reach 0" problem
    2. Every choice reduces problem size → (amount - coin)
    3. Same subproblems repeat → use DP

    --------------------------------------------------------------------------

    TOP-DOWN (RECURSION + MEMO):

    Mental Model:
    "Start from target → break into smaller problems"

    Example:
    f(11) → f(10), f(9), f(6)...

    Key Idea:
    - Explore all choices
    - Cache results to avoid recomputation

    Important Rules:
    - f(0) = 0
    - f(x < 0) = INF (invalid)
    - store result in dp[x]

    --------------------------------------------------------------------------

    BOTTOM-UP (ITERATIVE DP):

    Mental Model:
    "Build solution from smallest to largest"

    Example:
    dp[0] → dp[1] → dp[2] → ... → dp[amount]

    Key Idea:
    - Solve small amounts first
    - Use them to build bigger answers

    Transition:
    dp[i] = min(dp[i], 1 + dp[i - coin])

    --------------------------------------------------------------------------

    INTUITION (MOST IMPORTANT LINE):

    DP = "Solve once, reuse forever"

    --------------------------------------------------------------------------

    WHEN TO USE:

    Top-down:
    - easier to think
    - natural recursion problems

    Bottom-up:
    - faster (no recursion overhead)
    - better space/time control

    --------------------------------------------------------------------------

    COMMON MISTAKES:

    ❌ Forgetting base case (amount == 0)
    ❌ Not handling negative amount
    ❌ Not checking INF before adding 1
    ❌ Using HashMap when array works (slower)

    --------------------------------------------------------------------------

    FINAL UNDERSTANDING:

    This is NOT greedy.
    This is NOT backtracking.

    This is:
    → "Try all choices"
    → "Pick minimum"
    → "Cache results"

    ==========================================================================
    Time Complexity:
    O(amount * number_of_coins)

    Reason:
    Each amount computed once
    Each time we loop over all coins

    ----------------------------------

    Space Complexity:
    O(amount) → dp array
    O(amount) → recursion stack (worst case)

    Total: O(amount)
    */

    private int coinChange(int[] a, int target) {
        return coinChangeTopDown(a, target);
        //return coinChangeBottomUp(a,target);
    }

    public int coinChangeTopDown(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        int ans = helper(coins, amount, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int helper(int[] coins, int amount, int[] dp) {
        if (amount == 0) return 0;
        if (amount < 0) return Integer.MAX_VALUE;

        if (dp[amount] != -1) return dp[amount];

        int min = Integer.MAX_VALUE;

        for (int coin : coins) {
            int res = helper(coins, amount - coin, dp);
            if (res != Integer.MAX_VALUE) {
                min = Math.min(min, 1 + res);
            }
        }

        dp[amount] = min;
        return min;
    }

    public int coinChangeBottomUp(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); //here amount+1 acts as infinity as in java inf+1 will be -inf and we dont want unrealistic ans

        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 5};
        int target = 11;
        System.out.println(new CoinExchange().coinChange(a, target));
    }

}
