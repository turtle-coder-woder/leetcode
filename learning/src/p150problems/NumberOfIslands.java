package p150problems;

public class NumberOfIslands {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int islands = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    islands++;
                    dfs(grid, visited, i, j);
                }
            }
        }

        return islands;
    }

    private void dfs(char[][] grid, boolean[][] visited, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
            return;
        }

        if (visited[x][y] || grid[x][y] == '0') {
            return;
        }

        visited[x][y] = true;

        for (int dir = 0; dir < 4; dir++) {
            dfs(grid, visited, x + dx[dir], y + dy[dir]);
        }
    }

    public static void main(String[] args) {
        int[] ar = new int[]{1, 2, 3};
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        Integer ans = new NumberOfIslands().numIslands(grid);
        System.out.println(ans);

        char[][] grid2 = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        Integer ans2 = new NumberOfIslands().numIslands(grid2);
        System.out.println(ans2);

    }
}
