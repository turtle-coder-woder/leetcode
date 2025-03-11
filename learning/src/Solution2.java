import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.Map;

class Solution2 {
    Map<Character, List<Character>> knowledgeBase = new HashMap<>();

    public Solution2() {
        knowledgeBase.put('2', Arrays.asList('a', 'b', 'c'));
        knowledgeBase.put('3', Arrays.asList('d', 'e', 'f'));
        knowledgeBase.put('4', Arrays.asList('g', 'h', 'i'));
        knowledgeBase.put('5', Arrays.asList('j', 'k', 'l'));
        knowledgeBase.put('6', Arrays.asList('m', 'n', 'o'));
        knowledgeBase.put('7', Arrays.asList('p', 'q', 'r', 's'));
        knowledgeBase.put('8', Arrays.asList('t', 'u', 'v'));
        knowledgeBase.put('9', Arrays.asList('w', 'x', 'y', 'z'));
    }

    public static void main(String[] args) {
        String digits = "23";
        new Solution2().printGeneratedCombination(digits);
    }

    public void printGeneratedCombination(String digits) {
        if (digits == null) {
            digits = "";
        }
        List<String> result = Arrays.asList("");
        for(Character c:digits.toCharArray()){
            result = makeCombinations(c, result);
        }

        System.out.println(result.toString());
    }

    public List<String> makeCombinations(Character currentDigit, List<String> prevResult) {
        List<String> newResult = new ArrayList<>();
        for(int i = 0; i < prevResult.size(); i++) {
            String currentCombinationValue = prevResult.get(i);
            newResult = appendCombinations(currentCombinationValue, currentDigit, newResult);
        }

        return newResult;
    }

    public List<String> appendCombinations(String currentCombinationValue, Character currentDigit, List<String> newResult) {
        List<Character> knowledge = knowledgeBase.getOrDefault(currentDigit, null);
        if (knowledge != null) {
            for(Character k:knowledge){
                newResult.add(currentCombinationValue + k);
            }
        }
        return newResult;
    }

}
