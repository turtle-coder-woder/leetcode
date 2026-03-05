package p150problems;

public class BinarySearch {
    /*
    * The binary search may look simple but
    * there are some things to remember always eliminate left and right bby updating mid=left+1 or right-1
    * but this makes its [left,right] a closed interval
    * when its a closed interval always make while loop as (left<=right) instead of left<right as this may leave edge case of leaving to check for a index value when left and right are same
    * */
    public int searchInsert(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;

        while(left<=right){
            int mid = left+((right-left)/2);
            if(target==nums[mid]){
                return mid;
            }
            else if(target<nums[mid]){
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        return left;
    }

    public static void main(String args[]){
        int a[]= new int[]{1,3,5,6};
        int target = 4;
        System.out.println(new BinarySearch().searchInsert(a,target));
    }
}
