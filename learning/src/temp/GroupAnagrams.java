package temp;

import java.util.*;
import java.util.stream.Collectors;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String encodedString = getEncodedString(str);
            List<String> list = map.computeIfAbsent(encodedString, k -> new ArrayList<>());
            list.add(str);
        }
        return  map.values().stream().collect(Collectors.toList());
    }

    String getEncodedString(String string) {
        int[] alphabetsFrequency = new int[26];
        for (char c : string.toCharArray()) {
            alphabetsFrequency[c - 'a']++;
        }

        char a = 'a';
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            stringBuilder.append("#" + a).append(alphabetsFrequency[i]);
            a++;
        }
        return stringBuilder.toString();
    }
}
