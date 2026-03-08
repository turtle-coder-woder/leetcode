package p150problems;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    /*
Minimum Window Substring — Sliding Window + Frequency Maps

Goal:
Find the smallest substring in `s` that contains all characters of `t`
with at least the same frequency.

Key Idea:
Use a sliding window [left, right] and expand right until the window
contains all required characters, then shrink left to minimize the window.

Data Structures:
1) targetMap  -> frequency of characters required from `t`
2) runningMap -> frequency of characters currently in window

Important Counters:
required = number of unique characters in targetMap
formed   = number of characters whose required frequency is satisfied

Window becomes VALID when:
    formed == required

Algorithm:
1) Expand window by moving `right`
2) Update runningMap
3) If runningMap[c] == targetMap[c], increment `formed`
4) When formed == required:
      - we found a valid window
      - update minimum answer
      - shrink window by moving `left`
5) While shrinking:
      - decrement runningMap[leftChar]
      - if runningMap[leftChar] < targetMap[leftChar]
            formed-- (window becomes invalid)
6) Continue expanding again.

Why we use `formed`:
Instead of comparing entire maps every step (which is expensive),
we track how many required characters are satisfied.

Time Complexity:
O(|s| + |t|) because each pointer moves at most once.

Common Mistakes (hit during development):
1) Decreasing `formed` when frequency != target
   ❌ Wrong:
        if (!runningMap.get(ch).equals(targetMap.get(ch)))
   ✔ Correct:
        if (runningMap.get(ch) < targetMap.get(ch))

   Because frequencies greater than target are still valid.

2) Updating frequency for wrong character while shrinking.

3) Thinking we must compare full maps every step.
   Instead we track `formed`.

Golden Rule:
Insert -> check `==`
Remove -> check `<`

Insert side:
    if windowFreq == targetFreq -> formed++

Remove side:
    if windowFreq < targetFreq -> formed--

Mental Model:
Right pointer collects required characters.
Left pointer removes extra characters.
Whenever window is valid, shrink it to make it minimal.
*/

    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        int uniqueCharactersInTarget = targetMap.size();
        int runningUniqueCharactersInSource = 0;

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        Map<Character, Integer> runningMap = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (targetMap.containsKey(c)) {
                runningMap.put(c, runningMap.getOrDefault(c, 0) + 1);

                if (runningMap.get(c).intValue() == targetMap.get(c).intValue()) {
                    runningUniqueCharactersInSource++;
                }
            }

            while (runningUniqueCharactersInSource == uniqueCharactersInTarget) {
                if ((right - left + 1) < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                char charAtLeft = s.charAt(left);

                if (runningMap.containsKey(charAtLeft)) {
                    runningMap.put(charAtLeft, runningMap.get(charAtLeft) - 1);

                    if (runningMap.get(charAtLeft) < targetMap.get(charAtLeft)) {
                        runningUniqueCharactersInSource--;
                    }
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaabbbbbcdd";
        String t = "abcdd";
        System.out.println(new MinimumWindowSubstring().minWindow(s, t)); // BANC
    }
}