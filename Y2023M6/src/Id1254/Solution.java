package Id1254;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    boolean closed = true;
                    grid[i][j] = 1;
                    Queue<int []> q = new ArrayDeque<>();
                    q.offer(new int[]{i, j});
                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        int x = cur[0], y = cur[1];
                        if (x == 0 || y == 0 || x == m - 1 || y == n - 1) {
                            closed = false;
                        }

                        for (int[] dir : dirs) {
                            int cx = x + dir[0], cy = y + dir[1];
                            if (cx >= 0 && cx < m && cy >= 0 && cy < n &&  grid[cx][cy] == 0) {
                                grid[cx][cy] = 1;
                                q.offer(new int[]{cx, cy});
                            }
                        }
                    }

                    if (closed) {
                        res++;
                    }
                }
            }
        }

        return res;
    }
}
