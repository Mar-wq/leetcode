package id57insert_interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> newIntervals = new ArrayList<>();
        for (int[] interval : intervals) {
            newIntervals.add(interval);
        }
        newIntervals.add(newInterval);
        intervals = newIntervals.toArray(new int[newIntervals.size()][]);

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }

                return o1[0] - o2[0];
            }
        });

        int start = intervals[0][0], end = intervals[0][1];

        List<int[]> res = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > end) {
                res.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
            end = intervals[i][1] > end ? intervals[i][1]:end;
        }

        res.add(new int[]{start, end});
        int[][] resback = res.toArray(new int[res.size()][]);

        return resback;
    }
}
