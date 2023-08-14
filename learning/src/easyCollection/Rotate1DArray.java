package easyCollection;

public class Rotate1DArray {
    public void rotate(int[] nums, int k) {
        int size = nums.length;
        k = k % size;
        int operations = 0;
        for (int i = 0; i < size && operations < size; i++) {
            int start = i;
            int prev = nums[i];
            do {
                int next = (start + k) % size;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                start = next;
                operations++;
            } while (start != i);
        }
    }
}
