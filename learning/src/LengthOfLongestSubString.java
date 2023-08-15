import java.util.HashMap;

public class LengthOfLongestSubString {
    public int lengthOfLongestSubstring(String inputString) {
        if(inputString.length()<=1){
            return inputString.length(); //edge case
        }

        int max = Integer.MIN_VALUE;
        int startOfSubstring = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < inputString.length(); i++) {
            char currentCharacter = inputString.charAt(i);

            if (map.get(currentCharacter) != null) {
                int lastIndexOfCurrentCharacter = map.get(currentCharacter);

                int maxSoFar = i - startOfSubstring;
                if (max < maxSoFar) {
                    max = maxSoFar;
                }
                while(startOfSubstring<=lastIndexOfCurrentCharacter){
                    map.remove(inputString.charAt(startOfSubstring));
                    startOfSubstring++;
                }
            }
            map.put(currentCharacter, i);
        }

        int maxSoFar = inputString.length() - startOfSubstring;
        if (max < maxSoFar) {
            max = maxSoFar;
        }
        return max;
    }
}
