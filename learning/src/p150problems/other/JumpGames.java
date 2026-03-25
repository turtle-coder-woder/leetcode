package p150problems.other;

public class JumpGames {
    /*
     * Mental Model:
     * - Har index se check karo ki kya last index tak pahunch sakte hain.
     * - Current index se 1 to nums[index] tak har jump try karo.
     * - Agar kisi bhi jump se end reachable hai, to current index bhi reachable hai.
     * - Same index baar baar compute ho sakta hai, isliye memo use karte hain.
     * - memo[index] = true/false batata hai ki us index se end tak pahunchna possible hai ya nahi.
     *
     * Base case:
     * - Agar index last index ya uske aage pahunch gaya, to success.
     *
     * Optimization:
     * - Bade jumps pehle try kar rahe hain taaki early success mil sake.
     *
     * Complexity:
     * - Time: O(n^2)
     * - Space: O(n)
     */
    public boolean canJump(int[] nums) {
        Boolean[] canJumpMemo = new Boolean[nums.length];
        return canJumpHelper(nums, canJumpMemo, 0);
    }

    private boolean canJumpHelper(int[] nums, Boolean[] memo, int index) {
        if (index >= memo.length-1) {
            return true;
        }

        if (memo[index] != null) {
            return memo[index];
        }

        int maxJumpAllowed = nums[index];

        for (int i = maxJumpAllowed; i >=1; i--) {
            if (canJumpHelper(nums, memo, index + i)) {
                memo[index] = true;
                return true;
            }
        }

        memo[index] = false;
        return false;
    }

    public static void main(String[] args) {
        int[] ar = new int[]{3, 2, 1, 0, 4};
        System.out.println(new JumpGames().canJump(ar));
    }
}
