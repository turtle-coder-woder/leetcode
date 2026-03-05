package p150problems;

import java.util.Stack;

public class ValidParentheses {
    /*
    Use stack to validate parentheses
    there are 3 types of parentheses, you can use enum to define its value if something is able to knock off its
    value multiply by 2 than its good else bad
     */

    public enum Bracket {
        ROUND_LEFT,
        ROUND_RIGHT,
        SQUARE_LEFT,
        SQAURE_RIGHT,
        CURLY_LEFT,
        CURLY_RIGHT;


        static Bracket getBracketFromChar(char x) {
            switch (x) {
                case '(':
                    return ROUND_LEFT;
                case ')':
                    return ROUND_RIGHT;
                case '[':
                    return SQUARE_LEFT;
                case ']':
                    return SQAURE_RIGHT;
                case '{':
                    return CURLY_LEFT;
                case '}':
                    return CURLY_RIGHT;
            }
            return null;
        }

        static Bracket getRightPair(Bracket givenBracket) {
            if (givenBracket.equals(Bracket.CURLY_LEFT)) {
                return Bracket.CURLY_RIGHT;
            } else if (givenBracket.equals(Bracket.SQUARE_LEFT)) {
                return Bracket.SQAURE_RIGHT;
            } else if (givenBracket.equals(Bracket.ROUND_LEFT)) {
                return Bracket.ROUND_RIGHT;
            }
            return null;
        }

        static boolean isRightBracket(Bracket given) {
            return given.toString().toUpperCase().contains("RIGHT");
        }
    }

    public boolean isValid(String s) {
        Boolean ans = true;
        char[] x = s.toCharArray();
        Stack<Bracket> stack = new Stack<>();

        for (int i = 0; i < x.length; i++) {
            char current = x[i];
            Bracket currentBracket = Bracket.getBracketFromChar(current);
            if (Bracket.isRightBracket(currentBracket)) {
                if (stack.empty()) {
                    return false; //right one can't be waived off with empty stack
                }

                Bracket poppedBracket = stack.pop();
                Bracket potentialRightBracket = Bracket.getRightPair(poppedBracket);
                if (potentialRightBracket == null || !potentialRightBracket.equals(currentBracket)) {
                    return false;
                }
            } else {
                stack.push(currentBracket);
            }
        }


        return stack.empty();

    }

    public static void main(String args[]) {
        String s = "()[]{}";
        System.out.println(new ValidParentheses().isValid(s));
    }
}
