package temp;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });


        List<int[]> list = new ArrayList<>();
        list.add(new int[]{intervals[0][0], intervals[0][1]});
        for (int i = 1; i < intervals.length; i++) {
            int lastIndex = list.size() - 1;
            int[] lastPair = list.get(lastIndex);
            int[] rawPair = intervals[i];
            Integer currentFirst = rawPair[0];
            Integer currentLast = rawPair[1];
            if (currentFirst <= lastPair[1]) {
                int first = lastPair[0];
                list.remove(lastIndex);
                int last = Math.max(lastPair[1], currentLast);
                list.add(new int[]{first, last});
            } else {
                list.add(new int[]{currentFirst, currentLast});
            }
        }

        int ans[][] = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            int[] p = list.get(i);
            ans[i]=p;
        }

        return ans;
        //aaaaa
    }
}
