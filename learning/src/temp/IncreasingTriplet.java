package temp;

public class IncreasingTriplet {
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false; //edge case
        }
        int low = nums[0];
        int mid = Integer.MAX_VALUE;


        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            if (current > mid) {
                return true;
            } else if (current > low) {
                mid = current;
            } else {
                low = current;
            }
        }
        return false;
    }
}
