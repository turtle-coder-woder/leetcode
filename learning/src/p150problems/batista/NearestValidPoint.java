package p150problems.batista;
import java.util.*;

public class NearestValidPoint {
    public int nearestValidPoint(int tx, int ty, int[][] points) {
        PriorityQueue<Point> pointsList = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.manhatanDistance==o2.manhatanDistance){
                    return Integer.compare(o1.index,o2.index);
                }
                return Integer.compare(o1.manhatanDistance,o2.manhatanDistance);
            }
        });
        for(int i=0;i<points.length;i++){
            int[] p = points[i];
            int x = p[0];
            int y = p[1];
            if(x==tx || y==ty){
                pointsList.add(new Point(x,y,i,tx,ty));
            }
        }

        return pointsList.isEmpty()? -1 : pointsList.poll().index;
    }

    class Point{
        int x;
        int y;
        int index;
        int manhatanDistance;

        public Point(int x, int y, int i, int tx, int ty) {
            this.x=x;
            this.y=y;
            this.index=i;
            this.manhatanDistance = Math.abs(x-tx)+Math.abs(y-ty);
        }
    }

    public static void main(String[] args){
        System.out.println(new NearestValidPoint().nearestValidPoint(3,4,new int[][]{{2,3}}));
    }
}
