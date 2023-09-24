import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LetterCombinationOfPhoneNumbers {
    public List<String> letterCombinations(String digits) {
        return processCombination(digits, 0);
    }

    List<String> processCombination(String digits, int index) {
        if (index >= digits.length()) {
            return new ArrayList<>();
        }

        List<String> result = processCombination(digits, index + 1);
        List<String> currentResult = getStringForDigit(digits.charAt(index));
        if (result.isEmpty()) {
            return currentResult;
        }


        List<String> combinedAns = new ArrayList<>();
        for (String c : currentResult) {
            for (String x : result) {
                combinedAns.add(c + x);
            }
        }

        return combinedAns;
    }

    List<String> getStringForDigit(int x) {
        switch (x) {
            case '2':
                return Arrays.asList("a", "b", "c");
            case '3':
                return Arrays.asList("d", "e", "f");
            case '4':
                return Arrays.asList("g", "h", "i");
            case '5':
                return Arrays.asList("j", "k", "l");
            case '6':
                return Arrays.asList("m", "n", "o");
            case '7':
                return Arrays.asList("p", "q", "r", "s");
            case '8':
                return Arrays.asList("t", "u", "v");
            case '9':
                return Arrays.asList("w", "x", "y", "z");
        }
        return new ArrayList<>();
    }
}
