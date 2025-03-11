package temp;

class FindRange {
    public int[] searchRange(int[] nums, int target) {
        int leftmost = binarySearchLeft(nums, target, 0, nums.length - 1);
        if (leftmost == -1) {
            return new int[]{-1, -1};
        }
        int rightmost = binarySearchRight(nums, target, 0, nums.length - 1);
        return new int[]{leftmost, rightmost};
    }

    private int binarySearchLeft(int[] nums, int target, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + ((high - low) / 2);
        if (target == nums[mid]) {
            if (low == mid || nums[mid - 1] != target) {
                return mid;
            }
            high = mid - 1;
        } else if (target < nums[mid]) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
        return binarySearchLeft(nums, target, low, high);
    }

    private int binarySearchRight(int[] nums, int target, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + ((high - low) / 2);
        if (target == nums[mid]) {
            if (low == high || nums[mid + 1] != target) {
                return mid;
            }
            low = mid + 1;
        } else if (target < nums[mid]) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
        return binarySearchRight(nums, target, low, high);
    }
}