package id1654minimum_jumps_to_reach_home;


import java.util.*;

public class Solution {

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Queue<int[]> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> fbSet = new HashSet<>();
        int lower = 0, upper = Math.max(Arrays.stream(forbidden).max().getAsInt() + a, b);
        for (int f : forbidden) {
            fbSet.add(f);
        }
        queue.offer(new int[]{0, 1});
        int step = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int[] state = queue.poll();
                if (state[0] == x) {
                    return step;
                }

                int nextP = state[0] + a;
                int nextD = 1;
                if (lower <= nextP && nextP <= upper && !visited.contains(nextP * nextD) && !fbSet.contains(nextP)) {
                    visited.add(nextP * nextD);
                    queue.offer(new int[]{nextP, 1});
                }

                if (state[1] == 1) {
                    nextP = state[0] - b;
                    nextD = -1;
                    if (lower <= nextP && nextP <= upper && !visited.contains(nextP * nextD) && !fbSet.contains(nextP)) {
                        visited.add(nextP * nextD);
                        queue.offer(new int[]{nextP, -1});
                    }
                }
            }
            step++;
        }

        return -1;
    }
}
