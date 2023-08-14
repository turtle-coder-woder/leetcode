package easyCollection;

import java.util.HashMap;

public class FirstUniqueCharacter {
    public int firstUniqChar(String s) {
        char[] sc = s.toCharArray();
        int[] alphabets = new int[26];

        for (char c : sc) {
            alphabets[index(c)]++;
        }
        for (int i = 0; i < sc.length; i++) {
            if (alphabets[index(sc[i])] == 1) {
                return i;
            }
        }
        return -1;
    }

    int index(char c) {
        return c - 'a';
    }
}
