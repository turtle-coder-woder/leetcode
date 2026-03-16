package p150problems;

import java.util.*;

public class PacificAtlanticWaterFlow {
    //instaed of doing dfs from all hieght cells, do dfs only from sea sides
    //it means do pacific top and left cornern cells only dfs to reverse traverse and know till where water can come from
    //similary do from atlantic, and check is ans lies in pacific, than yes its an ans
    //can keep a memo for both as well to fastrack the process

    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, 1, -1};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int M = heights.length;
        int N = heights[0].length;
        int[][] pacificMemo = new int[M][N];
        int[][] atlanticMemo = new int[M][N];

        //first row for pacific
        for (int i = 0; i < N; i++) {
            int x = 0;
            int y = i;
            traverse(x, y, M, N, heights, pacificMemo, -1);
        }

        //first col for pacific
        for (int i = 0; i < M; i++) {
            int x = i;
            int y = 0;
            traverse(x, y, M, N, heights, pacificMemo, -1);
        }

        //last row for atlantic
        for (int i = 0; i < N; i++) {
            int x = M - 1;
            int y = i;
            traverse(x, y, M, N, heights, atlanticMemo, -1);
        }

        //last col for atlantic
        for (int i = 0; i < M; i++) {
            int x = i;
            int y = N - 1;
            traverse(x, y, M, N, heights, atlanticMemo, -1);
        }


        return intersectionOf(pacificMemo, atlanticMemo, M, N);
    }

    private List<List<Integer>> intersectionOf(int[][] pacificMemo, int[][] atlanticMemo,
                                               int M, int N) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (pacificMemo[i][j] == 1 && atlanticMemo[i][j] == 1) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }
        return ans;
    }

    private void traverse(int x, int y,
                          int M, int N,
                          int[][] heights, int[][] memo, int lastHeight) {
        if (x < 0 || y < 0 || x >= M || y >= N) {
            //out of bounds
            return;
        }

        if (memo[x][y] != 0) {
            //means we have visited it return safely
            return;
        }

        int currentHeight = heights[x][y];
        if (currentHeight < lastHeight) { //water can't flow
            return;
        }

        memo[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            //check all directions
            int X = x + dx[i];
            int Y = y + dy[i];
            traverse(X, Y, M, N, heights, memo, currentHeight);
        }

    }

    public static void main(String[] args) {
        int[][] heights = new int[][]{
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        List<List<Integer>> ans = new PacificAtlanticWaterFlow().pacificAtlantic(heights);
        System.out.println();
        for (List<Integer> an : ans) {
            an.forEach(x -> System.out.print(x + ","));
            System.out.println();
        }
    }
}
