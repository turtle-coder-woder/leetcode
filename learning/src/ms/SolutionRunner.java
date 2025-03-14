package ms;

import java.util.List;

public class SolutionRunner {
    public static void main(String... args) {
        int[] input = new int[]{4,3,2,7,8,2,3,1};
        List<Integer> result = new FindDisappearedNumbers().findDisappearedNumbers(input);
        System.out.println(result);
    }
}
