package Id2611mouse_and_chesse;

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });

        int n = reward1.length;

        int res = 0;
        for (int i = 0; i < n; i++) {
            res += reward1[i];
            pq.offer(new int[]{reward1[i] - reward2[i], i});
        }

        while (k > 0) {
            k--;
            int[] tem =  pq.poll();
            res += tem[0];
            res -= reward2[tem[1]];
        }

        return res;
    }
}
