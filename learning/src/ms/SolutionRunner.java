package ms;

public class SolutionRunner {
    public static void main(String... args) {
        String input = "(1+(4+5+2)-3)+(6+8)";
//        1,11,3   +,-
//        2,1,2   -,+
        int result = new BasicCalculator().calculate(input);
        System.out.println(result);
    }
}
