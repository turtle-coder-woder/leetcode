package temp;

public class FindPeak {
    public int findPeakElement(int[] nums) {
        Integer peakIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[peakIndex]) {
                return peakIndex;
            }
            peakIndex = i;
        }

        return peakIndex;

    }
}
