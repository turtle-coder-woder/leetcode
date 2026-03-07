package p150problems;

public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int left=0;
        int right=nums.length-1;

        while(left<right){
            int mid=left+((right-left)/2);
            if(nums[mid]<nums[right]){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return nums[left];
    }
    public static void main(String[] args) {
        int[] a = new int[]{4,5,6,7,0,1,2};
        System.out.println(new FindMinimumInRotatedSortedArray().findMin(a));
    }
}
