package p150problems.other;

import java.util.*;

public class WordSearchII {
            /*

        * 🧠 MENTAL MODEL: WORD SEARCH II (TRIE + DFS OPTIMAL)
        *
        * Problem ko brute force string generation ki tarah mat soch.
        * Hume board se random strings generate nahi karni — sirf valid dictionary prefixes explore karne hain.
        *
        * ---
        * STEP 1: BUILD TRIE
        * ---
        * Sare words ko Trie me insert karte hain.
        *
        * Important:
        * * Har node ek prefix represent karta hai
        * * End node pe poora word store kar dete hain (node.word)
        *
        * Example:
        * "oath" insert karne par path banega:
        * root -> o -> a -> t -> h (yaha word = "oath")
        *
        *
        * ---
        * STEP 2: DFS FROM EVERY CELL
        * ---
        * Board ke har cell se DFS start karte hain.
        *
        * DFS me hum string nahi banate,
        * balki current Trie node carry karte hain.
        *
        *
        * ---
        * STEP 3: CORE DFS LOGIC
        * ---
        * Har step pe:
        *
        * 1. current board character uthao
        * 2. Trie me check karo:
        * ```
           kya current node ke children me ye character exist karta hai?
          ```
        *
        * ```
           -> agar nahi:
          ```
        * ```
               iska matlab current path kisi word ka prefix nahi hai
          ```
        * ```
               ❌ immediately return (PRUNING 🔥)
          ```
        *
        * ```
           -> agar haan:
          ```
        * ```
               next Trie node pe move karo
          ```
        *
        *
        * ---
        * STEP 4: WORD FOUND
        * ---
        * Agar next Trie node pe:
        * ```
           node.word != null
          ```
        *
        * iska matlab:
        * ```
           current path ek complete word bana raha hai
          ```
        *
        * -> ans me add karo
        *
        *
        * ---
        * STEP 5: VISITED HANDLING
        * ---
        * Ek cell ko ek hi path me ek baar use kar sakte hain.
        *
        * -> visited[i][j] = 1
        * -> DFS 4 directions me
        * -> backtrack: visited[i][j] = 0
        *
        *
        * ---
        * STEP 6: BACKTRACKING
        * ---
        * DFS ke baad:
        * -> visited undo karo
        * -> taaki dusre paths explore ho sakein
        *
        *
        * ---
        * 🔥 KEY INSIGHT
        * ---
        *
        * Old approach:
        * * prefix string bana
        * * har baar Trie root se search karo
        *
        * Current optimal approach:
        * * Trie node carry karo
        * * already prefix validated hai
        * * next character directly Trie se check hota hai
        *
        *
        * ---
        * ⚡ WHY THIS IS FAST
        * ---
        *
        * * Invalid paths early cut ho jaate hain (prefix pruning)
        * * String banane ki zarurat nahi
        * * Trie ko baar baar root se traverse nahi karna padta
        *
        *
        * ---
        * 🧠 ONE LINE SUMMARY
        * ---
        *
        * "Board par DFS karte waqt Trie node carry karo,
        * aur har step pe check karo ki current path kisi word ka valid prefix hai ya nahi."
        *

        */

    static class Trie {
        public Trie[] childrens = new Trie[26];
        public String word;
        private Trie[] ptr;
        private Trie[] prevPtr;
        int globalPtrIndex;

        int getIndexFromChar(char c) {
            return c - 'a';
        }

        public void insert(String word) {
            Trie[] ptr = childrens;
            int index = 0;
            for (char c : word.toCharArray()) {
                index = getIndexFromChar(c);
                if (ptr[index] == null) {
                    ptr[index] = new Trie();
                }
                prevPtr = ptr;
                ptr = ptr[index].childrens;
            }
            prevPtr[index].word = word;
        }

        boolean hasPrefix(String prefix) {
            if (prefix.equals("")) {
                return false;
            }
            ptr = childrens;
            prevPtr = ptr;
            globalPtrIndex = 0;
            for (char c : prefix.toCharArray()) {
                globalPtrIndex = getIndexFromChar(c);
                if (ptr[globalPtrIndex] == null) {
                    return false;
                }
                prevPtr = ptr;
                ptr = ptr[globalPtrIndex].childrens;
            }
            return true;
        }

        boolean isWord(String word) {
            return hasPrefix(word) && prevPtr[globalPtrIndex].word!=null;
        }
    }

    int[] dx = new int[]{0, 0, -1, +1};
    int[] dy = new int[]{+1, -1, 0, 0};

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int M = board.length;
        int N = board[0].length;
        Set<String> ans = new HashSet<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                    int[][] visited = new int[M][N];
                    dfs(board, visited, i, j, M, N, ans,trie);
            }
        }

        String[] ar = ans.toArray(new String[0]);
        return Arrays.asList(ar);
    }

    private void dfs(char[][] board, int[][] visited, int i, int j,
                     int M, int N, Set<String> ans, Trie trie) {
        if (i < 0 || j < 0 || i >= M || j >= N || visited[i][j] == 1) {
            return;
        }

        visited[i][j] = 1;
        char currentChar = board[i][j];
        int index = currentChar-'a';
        if (trie==null || trie.childrens[index]==null) {
            visited[i][j] = 0;
            return;
        }

        if (trie.childrens[index].word!=null && !ans.contains(trie.childrens[index].word)) {
            ans.add(trie.childrens[index].word);
        }

        for (int x = 0; x < 4; x++) {
            int newX = i + dx[x];
            int newY = j + dy[x];
            dfs(board, visited, newX, newY, M, N, ans, trie.childrens[index]);
        }
        visited[i][j] = 0;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = new String[]{"oath", "pea", "eat", "rain"};
        List<String> ans = new WordSearchII().findWords(board, words);
        ans.forEach(x -> System.out.println(x));

    }

}
