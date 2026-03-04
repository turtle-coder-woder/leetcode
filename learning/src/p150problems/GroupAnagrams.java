package p150problems;

import java.util.*;
import java.util.stream.IntStream;

public class GroupAnagrams {
    //read of all strings left to right
    // for each string, read all characters between them and try to make a unique frequncyArray for each of them
    // now from freq array, combine all values with #, this will turn a string into a unqiue code
    //use this unique code to add a string into a map of code to list of strings
    // in the end , read all values of map 1 by 1 and add them as 1 group of list of strings, and return in the last
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> mapOfCodeToGroupOfStrings = new HashMap<>();
        Arrays.stream(strs).forEach(s -> {
            String code = getUniqueCode(s);
            List<String> group = mapOfCodeToGroupOfStrings.getOrDefault(code, new ArrayList<>());
            group.add(s);
            mapOfCodeToGroupOfStrings.put(code, group);
        });

        mapOfCodeToGroupOfStrings.forEach((x, y) -> ans.add(y));

        return ans;
    }

    public String getUniqueCode(String s) {
        int[] frequencyArray = new int[26];
        int offSet = 'a';
        char[] sChar = s.toCharArray();
        IntStream.range(0, sChar.length).forEach(i -> {
            char c = sChar[i];
            frequencyArray[c - offSet]++;
        });

        StringBuilder sb = new StringBuilder();
        Arrays.stream(frequencyArray).forEach(x -> sb.append(x).append("#"));
        return sb.toString();
    }

    public static void main(String args[]) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(new GroupAnagrams().groupAnagrams(strs));
    }
}
