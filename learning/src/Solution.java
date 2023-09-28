import easyCollection.*;
import helper.MissingRange;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Solution {
    public static void main(String args[]) {
        int[][] x=new int[][]{{0,30},{5,10},{15,20}};
        int[][] y=new int[][]{{6,15},{13,20},{6,17}};
        System.out.println(new RequiredMeetingRooms().minMeetingRooms(x));
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
