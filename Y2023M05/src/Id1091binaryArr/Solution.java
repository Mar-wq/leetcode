package Id1091binaryArr;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private int n;
    private int[][] dirs= {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) {
            return -1;
        }

        n = grid.length;
        boolean[][] visited = new boolean[n][n];
        Queue<Integer> q = new LinkedList<>();

        q.offer(0);
        visited[0][0] = true;
        int step = 1;

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int k = 0; k < sz; k++) {
                int i = q.poll();
                int x = i / n, y = i % n;
                if (x == n - 1 && y == n - 1) {
                    return step;
                }

                for (int[] dir : dirs) {
                    int x0 = x + dir[0], y0 = y + dir[1];
                    if (checkPostion(x0, y0, visited, grid)) {
                        q.offer(x0 * n + y0);
                        visited[x0][y0] = true;
                    }
                }
            }
            step++;
        }

        return -1;
    }

    private boolean checkPostion(int x, int y, boolean[][] visited, int[][] grid) {
        if (x < 0 || x >= n || y < 0 || y >= n || grid[x][y] == 1) {
            return false;
        }

        return !visited[x][y];
    }
    // 只是想记录下学到的东东
    {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
    }
}