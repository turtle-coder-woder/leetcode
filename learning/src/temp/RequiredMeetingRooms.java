package temp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class RequiredMeetingRooms {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < intervals.length; i++) {
            int currentMeetingStartTime = intervals[i][0];
            int currentMeetingEndTime = intervals[i][1];
            if (!minHeap.isEmpty() && currentMeetingStartTime >= minHeap.peek()) {
                minHeap.remove();
            }
            minHeap.add(currentMeetingEndTime);
        }

        return minHeap.size();
    }
}
