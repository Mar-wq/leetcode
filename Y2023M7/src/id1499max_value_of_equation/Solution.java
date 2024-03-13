package id1499max_value_of_equation;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

class Solution {
/*    public int findMaxValueOfEquation(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> p1[0] - p2[0]);

        int res = Integer.MIN_VALUE;
        for (int[] point : points) {
            int x = point[0], y = point[1];
            while (!pq.isEmpty() && (x - pq.peek()[1] > k)) {
                pq.poll();
            }

            if (!pq.isEmpty()) {
                res = Math.max(res, x + y - pq.peek()[0]);
            }

            pq.add(new int[]{x - y, x});
        }

        return res;
    }*/
    public int findMaxValueOfEquation(int[][] points, int k) {
        Deque<int[]> queue = new ArrayDeque<>();
        int res = Integer.MIN_VALUE;

        for (int[] point : points) {
            int x = point[0], y = point[1];
            while (!queue.isEmpty() && x - queue.peekFirst()[1] > k) {
                queue.pollFirst();
            }

            if (!queue.isEmpty()) {
                res = Math.max(res, x + y - queue.peek()[0]);
            }

            while (!queue.isEmpty() && queue.peekLast()[0] >= x - y) {
                queue.pollLast();
            }

            queue.addLast(new int[]{x - y, x});
        }

        return res;
    }
}
