package p150problems.batista;

public class NearestValidPoint {
    public int nearestValidPoint(int x, int y, int[][] points) {
        int minDist = Integer.MAX_VALUE;
        int resultIdx = -1;

        for (int i = 0; i < points.length; i++) {
            if (x == points[i][0] || y == points[i][1]) {
                int manDist = Math.abs(x - points[i][0]) + Math.abs(y - points[i][1]);
                if (manDist < minDist) {
                    minDist = manDist;
                    resultIdx = i;
                } else if (manDist == minDist) {
                    resultIdx = Math.min(resultIdx, i);
                }
            }
        }

        return resultIdx;
    }

    public static void main(String[] args) {
        System.out.println(new NearestValidPoint().nearestValidPoint(3, 4, new int[][]{{2, 3}}));
    }
}
