package p150problems.batista;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosestPoints {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Double.compare(distance(o2), distance(o1));
            }
        });

        for (int i = 0; i < points.length; i++) {
            if (maxHeap.size() <= k) {
                maxHeap.offer(points[i]);
            } else {
                if (distance(points[i]) < distance(maxHeap.peek())) {
                    maxHeap.poll();
                    maxHeap.offer(points[i]);
                }
            }
        }
        int[][] ans = new int[k][2];
        for (int i = 0; i < k; i++) {
            ans[i] = maxHeap.poll();
        }
        return ans;
    }

    public static double distance(int[] p) {
        int x = p[0];
        int y = p[1];
        return Math.sqrt((x * x) + (y * y));
    }

    public static void main(String[] args) {

        KClosestPoints obj = new KClosestPoints();

        int[][] points = {
                {1, 3},
                {-2, 2},
                {5, 8},
                {0, 1}
        };

        int k = 2;

        int[][] ans = obj.kClosest(points, k);

        for (int[] p : ans) {
            System.out.println(Arrays.toString(p));
        }
    }

}
