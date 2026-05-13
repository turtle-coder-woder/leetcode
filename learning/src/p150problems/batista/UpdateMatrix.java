package p150problems.batista;

import java.util.LinkedList;
import java.util.Queue;

public class UpdateMatrix {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int[][] updateMatrix(int[][] mat) {
        int M = mat.length;
        int N = mat[0].length;
        int[][] ans = new int[M][N];

        Queue<int[]> points = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (mat[i][j] == 1) {
                    ans[i][j] = Integer.MAX_VALUE;
                } else {
                    points.add(new int[]{i, j});
                }
            }
        }


        //now traverse and relax edges as per BFS
        while (!points.isEmpty()) {
            int size = points.size();
            for (int s = 0; s < size; s++) {
                int[] xy = points.poll();
                int x = xy[0];
                int y = xy[1];


                //all 4 directions
                for (int k = 0; k < 4; k++) {
                    int newX = x + dx[k];
                    int newY = y + dy[k];
                    if (didReach1(newX, newY, mat, M, N) == true) {
                        int newCost = 1 + ans[x][y];
                        if (newCost < ans[newX][newY]) {
                            ans[newX][newY] = newCost;
                            points.add(new int[]{newX, newY});
                        }
                    }
                }
            }
        }

        return ans;
    }

    private boolean didReach1(int newX, int newY, int[][] mat, int M, int N) {
        //out of bounds
        if (newX < 0 || newY < 0 || newX >= M || newY >= N) {
            return false;
        }

        return mat[newX][newY] == 1;
    }

    public static void main(String args[]) {
        int[][] mat = new int[][]{{0, 0, 0}, {
                0, 1, 0
        }, {
                1, 1, 1
        }};
        int ans[][] = new UpdateMatrix().updateMatrix(mat);

        for(int i=0;i<ans.length;i++){
            for(int j=0;j<ans[0].length;j++){
                System.out.print(ans[i][j]+", ");
            }
            System.out.println();
        }
    }
}
