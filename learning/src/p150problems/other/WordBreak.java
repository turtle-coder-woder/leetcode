package p150problems.other;

import java.util.*;

public class WordBreak {
    /*
    ==================== WORD BREAK (STRING STATE) ====================

    STATE:
    f(s) = can this string s be broken into valid words?

    ------------------------------------------------------------------

    CORE IDEA:

    At any point:
    → try removing a valid prefix
    → solve for remaining string

    Example:
    "catsanddog"

    Try:
    "cat" + f("sanddog")
    "cats" + f("anddog")

    ------------------------------------------------------------------

    TRANSITION:

    For each word in dictionary:
        if s starts with word:
            check f(remaining_string)

    If ANY path works → return true

    ------------------------------------------------------------------

    BASE CASE:

    f("") = true

    (empty string means we successfully consumed entire string)

    ------------------------------------------------------------------

    DP (MEMOIZATION):

    Same substring can appear multiple times.

    So:
    memo[s] = result for substring s

    Before computing:
        if memo has s → return it

    After computing:
        store result

    ------------------------------------------------------------------

    INTUITION:

    "Can I consume this string completely by chopping valid prefixes?"

    ------------------------------------------------------------------

    TREE VISUALIZATION:

    f("catsanddog")
    ├── "cat"  → f("sanddog")
    │            ├── "sand" → f("dog")
    │                          ├── "dog" → f("") ✅
    │
    ├── "cats" → f("anddog")
                ├── "and" → f("dog") (reused from memo)

    ------------------------------------------------------------------

    WHY DP?

    Because same substring appears again:
        f("dog") computed once → reused

    Without memo:
        exponential recursion

    With memo:
        each substring solved once

    ------------------------------------------------------------------

    TYPE:

    → Decision DP (true/false)
    → Recursion + memo
    → Similar to Coin Change (but boolean instead of min)

    ------------------------------------------------------------------

    IMPORTANT COST:

    substring(...) creates new string → O(n)

    So:
    This version is correct but slightly slower than index-based DP

    ------------------------------------------------------------------

    FINAL ONE-LINER:

    "Try all prefixes → recursively solve rest → cache results"

    ==================================================================
    */
    Map<String, Boolean> memo;

    public boolean wordBreak(String s, List<String> wordDict) {
        memo = new HashMap<>();
        memo.put("", true);
        return canWordBreak(s, wordDict);
    }

    private boolean canWordBreak(String s, List<String> wordDict) {

        if (memo.getOrDefault(s, null) != null) {
            return memo.get(s);
        }

        boolean canBreak = false;

        for (String word : wordDict) {
            if (s.startsWith(word)) {
                if (canWordBreak(s.substring(word.length(), s.length()), wordDict)) {
                    canBreak = true;
                    break;
                }
            }
        }

        memo.put(s, canBreak);
        return canBreak;
    }

    public static void main(String[] args) {
        String s = "catsandog";
        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(new WordBreak().wordBreak(s, wordDict));
    }
}
