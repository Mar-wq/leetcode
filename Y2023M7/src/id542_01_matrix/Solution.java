package id542_01_matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
   // private int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public int[][] updateMatrix(int[][] mat) {
/*        int m = mat.length, n = mat.length;
        int[][] res = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(res[i], -1);
        }

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    res[i][j] = 0;
                    q.offer(new int[]{i, j});
                }
            }
        }

        int dis = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int[] cur = q.poll();
                int x = cur[0], y = cur[1];
                res[x][y] = dis;
                for (int[] dir : dirs) {
                    int xn = x + dir[0], yn = y + dir[1];
                    if (xn >= 0 && xn < m && yn >= 0 && yn < n && res[xn][yn] == -1) {
                        res[xn][yn] = 0;
                        q.offer(new int[]{xn, yn});
                    }
                }
            }
            dis++;
        }

        return res;*/

        int m = mat.length, n = mat.length;
        int[][] dist = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    dist[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i - 1 >= 0) {
                    dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                }

                if (j - 1 >= 0) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
                }
            }
        }


        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (i + 1  < m) {
                    dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
                }

                if (j + 1 < n) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
                }
            }
        }

        return dist;
    }
}
