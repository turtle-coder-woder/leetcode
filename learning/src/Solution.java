import java.util.Arrays;
import java.util.Iterator;

public class Solution {
    public static void main(String args[]) {
        int[] nums1 = {1,2,2,1,3};
        int[] nums2 = {2,2,3,3,3,3};
        int[]  ar = new IntersectionOf2Array().intersect(nums1, nums2);
        printPrimitiveArray(ar);
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
