package p150problems.other;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int[] interval : intervals) {
            int startTime = interval[0];
            int endTime = interval[1];
            if (!minHeap.isEmpty()) {
                if (minHeap.peek() <= startTime) {
                    minHeap.poll();
                }
            }
            minHeap.add(endTime);
        }
        return minHeap.size();
    }

    public static void main(String[] args) {
        MeetingRoomsII solver = new MeetingRoomsII();

        int[][] test1 = {
                {0, 30},
                {5, 10},
                {15, 20}
        };
        assertEquals(2, solver.minMeetingRooms(test1), "Test 1 failed");

        int[][] test2 = {
                {7, 10},
                {2, 4}
        };
        assertEquals(1, solver.minMeetingRooms(test2), "Test 2 failed");

        int[][] test3 = {
                {1, 5},
                {2, 6},
                {3, 7},
                {4, 8}
        };
        assertEquals(4, solver.minMeetingRooms(test3), "Test 3 failed");

        int[][] test4 = {
                {1, 2},
                {2, 3},
                {3, 4}
        };
        assertEquals(1, solver.minMeetingRooms(test4), "Test 4 failed");

        int[][] test5 = {
                {1, 10},
                {2, 3},
                {4, 5},
                {6, 7}
        };
        assertEquals(2, solver.minMeetingRooms(test5), "Test 5 failed");

        int[][] test6 = {
                {1, 4},
                {2, 5},
                {7, 9}
        };
        assertEquals(2, solver.minMeetingRooms(test6), "Test 6 failed");

        int[][] test7 = {
                {6, 15},
                {13, 20},
                {6, 17}
        };
        assertEquals(3, solver.minMeetingRooms(test7), "Test 7 failed");

        System.out.println("All test cases passed!");
    }

    private static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + " Expected: " + expected + ", but got: " + actual);
        }
    }
}