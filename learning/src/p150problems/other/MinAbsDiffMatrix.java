package p150problems.other;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

public class MinAbsDiffMatrix {
    /*
    Mental Model:

    1. Brute Force:
       - For each k x k submatrix, collect all elements.
       - Use a sorted structure (TreeSet / sort array).
       - Minimum absolute difference will always be between adjacent elements in sorted order.
       - If only 1 distinct element → answer = 0.

    2. Key Observation:
       - No need to check all pairs → only adjacent elements after sorting.

    3. Optimization Idea (Sliding Window in 2D):
       - While moving window from (i,j) → (i,j+1):
           remove left column, add right column
       - While moving from (i,j) → (i+1,j):
           remove top row, add bottom row
       - This avoids rebuilding k*k elements every time.

    4. Limitation of Optimization:
       - Maintaining minimum absolute difference dynamically is hard.
       - Insertion/deletion can change multiple adjacent gaps.
       - So even with sliding window, we still need to scan sorted structure to compute min diff.

    5. Conclusion:
       - Brute force with sorting/TreeSet is simple and sufficient for given constraints.
       - Sliding window optimizes window construction, but not final diff computation fully.
    */
    public int[][] minAbsDiff(int[][] grid, int k) {
        int M = grid.length;
        int N = grid[0].length;
        int Mk = M - k+1;
        int Nk = N - k+1;
        int ans[][] = new int[Mk][Nk];
        for (int i = 0; i < Mk; i++) {
            for (int j = 0; j < Nk; j++) {
                ans[i][j] = calculate(grid, i, j, k);
            }
        }
        return ans;
    }

    private int calculate(int[][] grid, int startI, int startJ, int k) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = startI; i < startI + k; i++) {
            for (int j = startJ; j < startJ + k; j++) {
                treeSet.add(grid[i][j]);
            }
        }

        int minDiff = Integer.MAX_VALUE;
        Iterator<Integer> it = treeSet.iterator();
        Integer previous = null;
        if (it.hasNext()) {
            previous = it.next();
        }
        while (it.hasNext()) {
            int x = it.next();
            int diff = Math.abs(previous - x);
            minDiff = Math.min(minDiff, diff);
            previous = x;
        }
        return minDiff == Integer.MAX_VALUE ? 0 : minDiff;
    }

    public static void main(String[] s){
        int grid[][] = new int[][]{{1,8},
                {3,-2}};
        System.out.println(Arrays.deepToString(new MinAbsDiffMatrix().minAbsDiff(grid, 2)));
    }
}
