package id822card_flipping_game;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
    public int flipgame(int[] fronts, int[] backs) {
        Set<Integer> same = new HashSet<>();
        PriorityQueue<Integer> q = new PriorityQueue<>();

        int n = fronts.length;
        for (int i = 0; i < n; i++) {
            if (fronts[i] == backs[i]) {
                same.add(fronts[i]);
            }
            q.add(fronts[i]);
            q.add(backs[i]);
        }

        while (!q.isEmpty()) {
            Integer cur = q.poll();
            if (!same.contains(cur)) {
                return cur;
            }
        }

        return 0;
    }
}
