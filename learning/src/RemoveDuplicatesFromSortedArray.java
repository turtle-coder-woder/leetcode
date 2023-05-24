public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        //after base condition given in question we can't get empty arary so we can safely assume atleast 1 uniqueNumber would be present
        int inPlaceCounter = 1;
        int prevNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int currentVal = nums[i];
            if (prevNum != currentVal) {
                prevNum = nums[inPlaceCounter++] = currentVal;
            }
        }
        return inPlaceCounter;
    }
}
