public class SerachInRotatedArray {
    public int search(int[] nums, int target) {
        int pivotIndex = getPivotIndex(nums, 0, nums.length);
        int searchIndex = binarySearch(nums, target, 0, pivotIndex);
        searchIndex = searchIndex != -1 ? searchIndex : binarySearch(nums, target, pivotIndex + 1, nums.length - 1);
        return searchIndex;
    }

    private int binarySearch(int[] nums, int target, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + ((high - low) / 2);
        if (nums[mid] == target) {
            return mid;
        }

        if (target < nums[mid]) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
        return binarySearch(nums, target, low, high);
    }

    private int getPivotIndex(int[] nums, int low, int high) {
        if (low == high || low + 1 == high) {
            return low;
        }
        int mid = low + ((high - low) / 2);
        if (nums[low] > nums[mid]) {
            high = mid;
        } else {
            low = mid;
        }
        return getPivotIndex(nums, low, high);
    }
}
