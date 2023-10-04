public class NumberOfUniquePaths {
    public int uniquePaths(int m, int n) {
        int memo[][] = new int[m + 1][n + 1];
        return calculatePathToEnd(memo, 1, 1, m, n);
    }

    private int calculatePathToEnd(int[][] memo, int i, int j, int m, int n) {
        if (i >= m || j >= n) {
            return 1;
        }
        int val = memo[i][j] == 0 ? calculatePathToEnd(memo, i + 1, j, m, n) + calculatePathToEnd(memo, i, j + 1, m, n) : memo[i][j];
        memo[i][j] = val;
        return val;
    }
}
