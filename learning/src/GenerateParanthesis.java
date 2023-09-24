import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class GenerateParanthesis {
    Character OPEN = '(';
    Character CLOSE = ')';

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        fill(n, ans, new StringBuilder(), 0, 0);
        return ans;
    }

    void fill(int n, List<String> ans, StringBuilder stringBuilder, int open, int close) {
        if (stringBuilder.length() == 2 * n) {
            ans.add(stringBuilder.toString());
            return;
        }

        if (open < n) {
            stringBuilder.append(OPEN);
            fill(n, ans, stringBuilder, open + 1, close);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if (close < open) {
            stringBuilder.append(CLOSE);
            fill(n, ans, stringBuilder, open, close + 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }
}
