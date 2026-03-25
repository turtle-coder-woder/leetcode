package p150problems.other;

import java.util.*;

public class WordSearchII {
    static class Trie {
        public Trie[] childrens = new Trie[26];
        public boolean isWord;
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
            prevPtr[index].isWord = true;
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
            return hasPrefix(word) && prevPtr[globalPtrIndex].isWord;
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
                char c = board[i][j];
                String prefix = String.valueOf(c);
                if (trie.hasPrefix(prefix)) {
                    int[][] visited = new int[M][N];
                    dfs(board, visited, i, j, M, N, ans, "", trie);
                }

            }
        }

        String[] ar = ans.toArray(new String[0]);
        return Arrays.asList(ar);
    }

    private void dfs(char[][] board, int[][] visited, int i, int j,
                     int M, int N, Set<String> ans, String prefix, Trie trie) {
        if (i < 0 || j < 0 || i >= M || j >= N || visited[i][j] == 1) {
            return;
        }

        visited[i][j] = 1;
        char currentChar = board[i][j];
        prefix += currentChar;
        if (!trie.hasPrefix(prefix)) {
            visited[i][j] = 0;
            return;
        }
        if (trie.isWord(prefix) && !ans.contains(prefix)) {
            ans.add(prefix);
        }

        for (int x = 0; x < 4; x++) {
            int newX = i + dx[x];
            int newY = j + dy[x];
            dfs(board, visited, newX, newY, M, N, ans, prefix, trie);
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
