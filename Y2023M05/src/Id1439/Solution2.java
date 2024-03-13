package Id1439;


import java.util.PriorityQueue;

public class Solution2 {
    public int kthSmallest(int[][] matrix, int k) {
/*        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }


        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[2] < matrix[cur[1]].length) {
                pq.offer(new int[]{matrix[cur[1]][cur[2] + 1], cur[1], cur[2] + 1});
            }
            if (--k == 0) {
                return cur[0];
            }
        }

        return -1;*/

        int n = matrix.length;
        int l = matrix[0][0], r = matrix[n - 1][n - 1];

        while (l < n) {
            int mid = l + ((r - l) >> 1);
            if (check(matrix, mid, k, n)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    private boolean check(int[][] matrix, int mid, int k, int n) {
        int num = 0;
        for (int i = 0; i < n; i++) {
            int j = 0;
            while (j < n && matrix[i][j++] <= mid ) {
                num++;
            }
        }

        return num >= k;
    }
}
