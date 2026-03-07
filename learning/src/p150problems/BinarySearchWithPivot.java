package p150problems;

public class BinarySearchWithPivot {
    public int search(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;

        while(left<=right){
            int mid = left+((right-left)/2);
            //if target match return index
            if(nums[mid]==target){
                return mid;
            }

            //if left part sorted work here
            if(nums[left]<=nums[mid]){
                if(target>=nums[left] && target<nums[mid]){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }else {
                //else right part sorted work here
                if(target>nums[mid] && target<=nums[right]){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }



        return -1;
    }

    private int findPivotForKthBend(int[] nums) {
        if(nums.length==1){
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left) / 2);

            if (nums[mid] > nums[right]) { //this mean our left part is good, pivot must be on right side
                left = mid+1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String args[]) {
        int a[] = new int[]{3,1};
        int target = 1;
        System.out.println(new BinarySearchWithPivot().search(a, target));
    }
}
