package p150problems;

import java.util.Arrays;

public class TwoSum {
//    number are already sorted
//    we need 2 pointers left and right
//    left pointer+ right pointer sum should be compared
//    if matched return both l & r index, else if less increase l, if more decrease r
    public int[] twoSum(int[] numbers, int target) {
        int ans[] = new int[2];
        int left = 0;
        int right = numbers.length-1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                ans[0] = left+1;//as the ans required is 1 indexed
                ans[1] = right+1;
                return ans;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }

    public static void main(String args[]) {
        int[] a = new int[]{2, 7, 11, 15};
        int t = 13;
        int ans[]=new TwoSum().twoSum(a, t);
        Arrays.stream(ans).forEach(x-> System.out.println(x));
    }
}
