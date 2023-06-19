import java.util.Arrays;
import java.util.Iterator;

public class Solution {
    public static void main(String args[]) {
        char[][] board ={{'.','.','.','.','5','.','.','1','.'},
                {
                        '.','4','.','3','.','.','.','.','.'},
                {
                        '.','.','.','.','.','3','.','.','1'},
                {
                        '8','.','.','.','.','.','.','2','.'},
                {
                        '.','.','2','.','7','.','.','.','.'},
                {
                        '.','1','5','.','.','.','.','.','.'},
                {
                        '.','.','.','.','.','2','.','.','.'},
                {
                        '.','2','.','9','.','.','.','.','.'},
                {
                        '.','.','4','.','.','.','.','.','.'}};
        System.out.println(new isValidSoduko().isValidSudoku(board));
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
