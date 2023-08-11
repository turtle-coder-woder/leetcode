public class Merge2SortedArrays {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int backPointerOfAns = nums1.length - 1;
        int num1BackPointer = m - 1;
        int num2BackPointer = nums2.length - 1;
        while (num1BackPointer > -1 && num2BackPointer > -1) {
            if (nums1[num1BackPointer] > nums2[num2BackPointer]) {
                nums1[backPointerOfAns--] = nums1[num1BackPointer--];
            } else {
                nums1[backPointerOfAns--] = nums2[num2BackPointer--];
            }

        }

        while (num2BackPointer > -1) {
            nums1[backPointerOfAns--] = nums2[num2BackPointer--];
        }
    }

}
