package p150problems.batista;

import java.util.LinkedList;
import java.util.Queue;

public class OrangesRotting {
    int[] dx = {-1, +1, 0, 0};
    int[] dy = {0, 0, -1, +1};

    //0 mean nothing
    //1 means fresh
    //2 means rotten
    public int orangesRotting(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        int time = 0;
        Queue<int[]> queue = new LinkedList<>();

        //make visited
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i ,j});
                }
            }
        }

        while (!queue.isEmpty()) {
            Queue<int[]> queue2 = new LinkedList<>();
            while (!queue.isEmpty()) {
                int[] point = queue.poll();
                int i = point[0];
                int j = point[1];
                for (int k = 0; k < 4; k++) {
                    if (rottenOrangesFrom(i + dx[k], j + dy[k], grid) == 1) {
                        queue2.add(new int[]{(i + dx[k]), (j + dy[k])});
                    }
                }
            }
            if (!queue2.isEmpty()) {
                time++;
                queue = queue2;
            }
        }

        //check if any orange fresh?
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return time;
    }

    private int rottenOrangesFrom(int i, int j, int[][] grid) {
        //out of bounds
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return 0;
        }

        //if only currentOrangeIsFresh
        if (grid[i][j] == 1) {
            grid[i][j] = 2;
            return 1;
        }
        return 0;
    }

    public static void main(String args[]) {
        int[][] grid = new int[][]{{2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}};
        System.out.println(new OrangesRotting().orangesRotting(grid));
    }
}
