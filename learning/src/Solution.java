import java.util.Arrays;
import java.util.Iterator;

public class Solution {
    public static void main(String args[]) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        new Rotate1DArray().rotate(nums, 3);
        printPrimitiveArray(nums);
    }

    public static void printArray(Object[] arr) {
        Iterator<Object> iterator = Arrays.stream(arr).iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.print(next.toString() + " ");
        }
    }

    public static void printPrimitiveArray(int[] arr) {
        StringBuilder msg = new StringBuilder();
        for (int i : arr) {
            msg.append(i).append(",");
        }
        msg.deleteCharAt(msg.length() - 1);
        System.out.println(msg);
    }
}
