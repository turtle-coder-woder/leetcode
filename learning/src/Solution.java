import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Solution {
    public static void main(String args[]) {
        Map<String, Integer> fruits = new HashMap<>();
        fruits.put("apple", 10);
        fruits.put("banana", 20);
        fruits.put("orange", 30);

        fruits.forEach((k,v) -> {
            System.out.println("value"+v);
            System.out.println("key "+k);});
//        StringMatching x=new StringMatching("aabaaabaaac","aabaaac");
//        printPrimitiveArray(x.pi);
//        System.out.println(x.find());
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
