package Id1439;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution1 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
            }
        });

        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k) /*这里有什么意义呢？？*/; i++) {
            pq.offer(new int[]{i, 0});
        }

        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxpair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxpair[0]]);
            list.add(nums2[idxpair[1]]);
            ans.add(list);

            if (idxpair[1] + 1 < n) {
                pq.offer(new int[]{idxpair[0], idxpair[1] + 1});
            }
        }

        return ans;
    }
}
