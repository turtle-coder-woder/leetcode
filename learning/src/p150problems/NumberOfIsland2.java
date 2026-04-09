package p150problems;

public class NumberOfIsland2 {
    int[] dx = {-1,+1,0,0};
    int[] dy = {0,0,-1,+1};
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length==0){
            return 0;
        }
        int islands=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    islands++;
                    dfs(grid,i,j);
                }
            }
        }
        return islands;
    }

    private void dfs(char[][] grid, int i, int j) {
        //safe conditions
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length ||
                grid[i][j]=='0' || //is water
                grid[i][j]=='2') { //is visited
            return;
        }

        grid[i][j] = '2';
        for(int k=0;k<4;k++){
            int newX = i+dx[k];
            int newY = j+dy[k];
            dfs(grid,newX,newY);
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
        Integer ans = new NumberOfIsland2().numIslands(grid);
        System.out.println(ans);

        char[][] grid2 = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        Integer ans2 = new NumberOfIsland2().numIslands(grid2);
        System.out.println(ans2);
    }
}
