package id1851minimum_interval_to_include_each_query;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        int n = queries.length;
        Integer[] queriesBck = new Integer[n];
        for (int i = 0; i < n; i++) {
            queriesBck[i] = i;
        }

        Arrays.sort(queriesBck, (o1, o2) -> queries[o1] - queries[o2]);
        Arrays.sort(intervals,(o1, o2) -> o1[0] - o2[0]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) ->(o1[1] - o1[0]) - (o2[1] - o2[0]));

        int[] res = new int[n];
        Arrays.fill(res, -1);

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int queriy = queries[queriesBck[i]];
            while (cnt < intervals.length && queriy >= intervals[cnt][0]) {
                pq.add(intervals[cnt]);
                cnt++;
            }

            while (!pq.isEmpty() && pq.peek()[1] < queriy) {
                pq.poll();
            }

            if (!pq.isEmpty()) {
                res[queriesBck[i]] = pq.peek()[1] - pq.peek()[0] + 1;
            }
        }

        return res;
    }
}
