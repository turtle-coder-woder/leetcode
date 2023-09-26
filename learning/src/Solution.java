import easyCollection.*;
import helper.MissingRange;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Solution {
    public static void main(String args[]) {
        List<List<Integer>> ans = new GenerateSubsets().subsets(new int[]{1,2,3,4});
        ans.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return getHashSum(o1).compareTo(getHashSum(o2));
            }
            public String getHashSum(List<Integer> o){
                Iterator it=o.iterator();
                StringBuilder sb =new StringBuilder();
                while (it.hasNext()){
                    sb.append("#"+it.next());
                }
                return sb.toString();
            }
        });
        System.out.println(ans);
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
