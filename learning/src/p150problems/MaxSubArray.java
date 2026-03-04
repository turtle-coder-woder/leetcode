package p150problems;

public class MaxSubArray {
    //keep a outer var as sum=nums[0], maxSum= nums[0]
    //start reading from left to right and adding sum +=num[i]
    // if currentsum is less than 0, update currentsum to current reading number, else add current num to current sum
    //keep updating maxSum after every iteration
    // return maxSum as ans
    public int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            //Start new subarray OR extend previous one
            if (currentSum < 0) {
                currentSum = nums[i];
            } else {
                currentSum += nums[i];
            }
            //update max if we can
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String args[]) {
        int a[] = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(new MaxSubArray().maxSubArray(a));
    }
}
