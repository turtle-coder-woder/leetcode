package p150problems;

import java.util.Arrays;

public class TwoSumII {
    // maintaing 2 pointers left and right
    //since array is already soprted, try to see if left number + right number == target -> if yes updates ans and return
    // if sum is less than target , it means we need to discard left and move left=left+1
    // else if sum is  greater than target, we need to decrement right right=right-1
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int prospect = numbers[left] + numbers[right];
            if (prospect == target) {
                return new int[]{left+1, right+1};//as index is 1 index as mentioned in question
            } else if (prospect < target) {
                left++;
            } else {
                right--;
            }
        }
        return null; // we are sure that a solution will always be present as mentioned in the question
    }

    public static void main(String[] args) {
        int[] numbers = {2,3,4};
        int target = 6;
        int[] ans = new TwoSumII().twoSum(numbers, target);
        Arrays.stream(ans).forEach(System.out::println);
    }
}
