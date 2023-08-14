import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        int length = s.length();
        int i = 0;
        Stack<Character> stack = new Stack();
        while (i < length) {
            Character currentCharacter = s.charAt(i);
            if (stack.isEmpty()) {
                if (getTypeOfBracket(currentCharacter) % 2 == 1) { //should not be closing bracket
                    return false;
                }
                stack.push(currentCharacter);
            } else {
                if (getTypeOfBracket(currentCharacter) % 2 == 1) { //if a closing bracket than must be closing prev of its type
                    Character popped = stack.pop();
                    if (getTypeOfBracket(popped) + 1 != getTypeOfBracket(currentCharacter)) {
                        return false;
                    }
                } else {
                    stack.push(currentCharacter);
                }
            }

            i++;
        }

        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    int getTypeOfBracket(Character ch) {
        int val = 0;
        switch (ch) {
            case '(':
                val = 0;
                break;
            case ')':
                val = 1;
                break;
            case '[':
                val = 2;
                break;
            case ']':
                val = 3;
                break;
            case '{':
                val = 4;
                break;
            case '}':
                val = 5;
                break;

        }
        // System.out.println("for "+ch+" the val is"+val);
        return val;
    }
    //0,1-()
    //2,3-[]
    //4,5={}
}
