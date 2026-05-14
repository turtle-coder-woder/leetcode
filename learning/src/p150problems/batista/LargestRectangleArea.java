package p150problems.batista;

import java.util.*;

public class LargestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        int N = heights.length;
        int[] rightViewNearestSmallIndex = new int[N];
        int[] leftViewNearestSmallIndex = new int[N];
        Stack<Integer> stack = new Stack<>();
        //calculate left side next smallest index
        stack.add(0);
        for (int i = 1; i < N; i++) {
            int current = heights[i];
            while (!stack.isEmpty() && current < heights[stack.peek()]) {
                rightViewNearestSmallIndex[stack.pop()] = i;
            }
            stack.push(i);
        }

        //for the column of bar which doesn have next neasrt in right view, it means it can go till N
        while (!stack.isEmpty()) {
            rightViewNearestSmallIndex[stack.pop()] = N;
        }

        //calculate right side next smallest index
        stack.add(N - 1);
        for (int i = N - 2; i >= 0; i--) {
            int current = heights[i];
            while (!stack.isEmpty() && current < heights[stack.peek()]) {
                leftViewNearestSmallIndex[stack.pop()] = i;
            }
            stack.push(i);
        }

        //similarly for left side for the bar which dont have next nearest as left can go till -1
        while (!stack.isEmpty()) {
            leftViewNearestSmallIndex[stack.pop()] = -1;
        }

        int maxRectangle = heights[0];//atleast this would be maxRectangle
        for (int i = 0; i < N; i++) {
            int rightNextMin = rightViewNearestSmallIndex[i];
            int leftNextMin = leftViewNearestSmallIndex[i];
            int currentHeight = heights[i];
            int rectangleWithCurrentColumn = (rightNextMin - leftNextMin-1) * currentHeight;
            maxRectangle = Math.max(maxRectangle, rectangleWithCurrentColumn);
        }

        return maxRectangle;
    }

    public static void main(String[] args) {
        int[] heights = new int[]{2, 1, 5, 6, 2, 3};
        System.out.println(new LargestRectangleArea().largestRectangleArea(heights));
        int[] heights2 = new int[]{2, 4};
        System.out.println(new LargestRectangleArea().largestRectangleArea(heights2));
    }
}
