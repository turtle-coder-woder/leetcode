package p150problems;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    /*
    Maintain a set and a queue
    when reading a chars, see if it is present already in set?
    if not safely add in set and queue
    it present, then we need to shrink our queue and also remove element from our set
    * */
    public int lengthOfLongestSubstring(String s) {
        int maxLengthOfNonRepeatingChars=0;
        Queue<Character> queue = new LinkedList<>();
        Set<Character> set = new HashSet<>();
        for(int i=0;i<s.length();i++){
            Character currentCharacter = s.charAt(i);
            if(set.contains(currentCharacter)){
                maxLengthOfNonRepeatingChars = Math.max(maxLengthOfNonRepeatingChars,queue.size());
                while(set.contains(currentCharacter)){
                    Character poped = queue.remove();
                    set.remove(poped);
                }
            }

            queue.offer(currentCharacter);
            set.add(currentCharacter);
        }
        maxLengthOfNonRepeatingChars = Math.max(maxLengthOfNonRepeatingChars,queue.size()); //as queue may still have some elements after our processing
        return maxLengthOfNonRepeatingChars;
    }

    public static void main(String[] args) {
        String s=" ";
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring(s));
    }
}
