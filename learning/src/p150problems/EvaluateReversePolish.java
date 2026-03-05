package p150problems;

import java.util.ArrayDeque;

public class EvaluateReversePolish {
    /*
    Use ArrayDeque over stack first of all
    because all method in Stack are protected by Synchronized unnecessarily
    in java 1.6 java doc says use ArrayDeque over stack (has same methods)
    Now for the solution, use stack to store operands and when operator is stored, evaluatre value and keep it back in stack
    * */
    enum Operator {
        MUL("*", 2), DIV("/", 2), PLUS("+", 1), MINUS("-", 1);
        String s;
        int priority;

        Operator(String s, int i) {
            this.s = s;
            this.priority = i;
        }

        static Operator getByTokenValue(String val) {
            switch (val) {
                case "*":
                    return MUL;
                case "+":
                    return PLUS;
                case "-":
                    return MINUS;
                case "/":
                    return DIV;
            }
            return null;
        }

        public static int evaluate(int x, int y, Operator operator) {
            switch (operator) {
                case PLUS:
                    return x + y;
                case MINUS:
                    return x - y;
                case DIV:
                    return x / y;
                case MUL:
                    return x * y;
            }
            return 0;
        }
    }

    public int evalRPN(String[] tokens) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < tokens.length; i++) {
            String currentToken = tokens[i];
            Operator operator = Operator.getByTokenValue(currentToken);
            if (operator == null) {
                stack.push(Integer.valueOf(currentToken));
            } else {
                int y = stack.pop();
                int x = stack.pop();
                int val = Operator.evaluate(x, y, operator);
                stack.push(val);
            }
        }

        return stack.peek();
    }

    public static void main(String args[]) {
        String s[] = {"2", "1", "+", "3", "*"};
        System.out.println(new EvaluateReversePolish().evalRPN(s));
    }


}
