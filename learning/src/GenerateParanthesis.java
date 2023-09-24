import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class GenerateParanthesis {
    Character OPEN = '(';
    Character CLOSE = ')';

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        fill(2 * n, ans, "");
        return ans;
    }

    void fill(int expectedSize, List<String> ans, String last) {
        if (last.length() >= expectedSize) {
            if (validForParanthesis(last)) {
                ans.add(last);
            }
            return;
        }

        fill(expectedSize, ans, last + OPEN);
        fill(expectedSize, ans, last + CLOSE);
    }

    boolean validForParanthesis(String value) {
        Stack<Character> stack = new Stack<>();
        for (char c : value.toCharArray()) {
            if (c == CLOSE) {
                if (stack.isEmpty() || stack.peek() == CLOSE) {
                    return false;
                }

                stack.pop();
            }else{
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}
