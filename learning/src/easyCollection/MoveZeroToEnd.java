package easyCollection;

public class MoveZeroToEnd {
    public void moveZeroes(int[] nums) {
        int numsPointer = 0;
        int i = 0;
        int zeroIndex = getZeroIndex(nums);
        if (zeroIndex != -1) {
            numsPointer = zeroIndex;
            i = zeroIndex + 1;
            while (i < nums.length) {
                if (nums[i] != 0) {
                    nums[numsPointer++] = nums[i];
                    nums[i] = 0;
                }
                i++;
            }
        }

    }

    int getZeroIndex(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == 0) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
