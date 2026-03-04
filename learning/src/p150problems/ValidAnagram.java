package p150problems;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ValidAnagram {
    //since given input is all lowercase, we can make a frequency array -> an array of primitive type of 26 size
    //further we can start by reading all characters of s, and maintain frequency of s by increasing alphabet freq
    //when we starrt reading all chars of t, we now decrease freq
    // in the end if we find any element which is not zero in freq array -> return false
    //else they must be anagrams
    public boolean isAnagram(String s, String t) {
        if (t.length() != s.length()) {
            return false;
        }
        int[] frequencyMap = new int[26];
        char sChar[] = s.toCharArray();
        char tChar[] = t.toCharArray();
        IntStream.range(0, sChar.length).forEach(i -> {
            char currentSourceChar = sChar[i];
            char currentTargetChar = tChar[i];
            frequencyMap[currentSourceChar - 'a']++;
            frequencyMap[currentTargetChar - 'a']--;
        });

        return Arrays.stream(frequencyMap).allMatch(x -> x == 0);
    }


    public static void main(String args[]) {
        String s = "anagram";
        String t = "nagarmm";
        System.out.println(new ValidAnagram().isAnagram(s, t));
    }
}
