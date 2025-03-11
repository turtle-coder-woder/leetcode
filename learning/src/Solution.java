import easyCollection.*;
import helper.MissingRange;
import temp.CoinChange;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Solution {
    public static void main(String args[]) {
        int[] x = {186,419,83,408};
        int amt=6249;
        System.out.println(new CoinChange().coinChange(x,amt));
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
