package Id1439;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// 1439
//有序矩阵中的第 k 个最小数组和          373查找和最小的 K 对数字           719找出第 K 小的数对距离
class Solution {
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length;
        int[] prev = mat[0];

        for (int i = 1; i < m; i++) {
            prev = merge(prev, mat[i], k);
        }

        return prev[k - 1];
    }

    public int[] merge(int[] f, int[] g, int k) {
        if (g.length > f.length) {
            return merge(g, f, k);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        for (int i = 0; i < g.length; i++) {
            pq.offer(new int[]{0, i, f[0] + g[i]});
        }

        List<Integer> list = new ArrayList<>();

        while (k-- > 0 && !pq.isEmpty()) {
            int[] entry = pq.poll();
            list.add(entry[2]);

            if (entry[0] + 1 < f.length) {
                pq.offer(new int[]{entry[0] + 1, entry[1], f[entry[0] + 1] + g[entry[1]]});
            }
        }

        int[] ans  = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }
}
