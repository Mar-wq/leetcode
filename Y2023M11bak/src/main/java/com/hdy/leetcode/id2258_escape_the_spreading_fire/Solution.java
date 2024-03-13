package com.hdy.leetcode.id2258_escape_the_spreading_fire;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    int[][] directions = {
            {-1, 0}, // 上
            {1, 0},  // 下
            {0, -1}, // 左
            {0, 1}   // 右
    };

    public int maximumMinutes(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] fireTime = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(fireTime[i], Integer.MAX_VALUE);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.add(i * n + j);
                    fireTime[i][j] = 0;
                }
            }
        }

        int time = 1;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                Integer pos = queue.poll();
                int x = pos / n, y = pos % n;
                for (int[] direction : directions) {
                    int x1 = x + direction[0], y1 = y + direction[1];
                    if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n && grid[x1][y1] != 2 && fireTime[x1][y1] == Integer.MAX_VALUE) {
                        fireTime[x1][y1] = time;
                        queue.add(x1 * n+ y1);
                    }
                }
            }
            time++;
        }

        int l = 0 , r = (m + 1) * (n + 1);

        while (l < r) {
            int mid = (l + r) / 2;
            if (canReach(grid, fireTime, mid)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        if (r == 0) {
            return -1;
        } else if (l == (m + 1) * (n + 1)) {
            return 1000000000;
        } else {
            return l - 1;
        }
    }

    private boolean canReach(int[][] grid, int[][] fireTime, int stayTime) {
        Queue<Integer> queue = new ArrayDeque<>();
        if (stayTime < fireTime[0][0]) {
            queue.add(0);
        } else {
            return false;
        }
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;


        int time = 1;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                Integer pos = queue.poll();
                int x = pos / n, y = pos % n;
                for (int[] direction : directions) {
                    int x1 = x + direction[0], y1 = y + direction[1];
                    if (x1 == m - 1 && y1 == n - 1 && (stayTime + time) <= fireTime[m - 1][n - 1]) {
                        return true;
                    }
                    if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n && grid[x1][y1] != 2 && !visited[x1][y1] && fireTime[x1][y1] > (stayTime +time)) {
                        queue.add(x1 * n + y1);
                        visited[x1][y1] = true;
                    }
                }
            }
            time++;
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] grid =  {
                {0, 2, 1, 0, 0, 0, 0},
                {0, 2, 2, 2, 2, 2, 0},
                {0, 2, 0, 0, 0, 0, 0},
                {0, 2, 0, 2, 2, 2, 2},
                {0, 2, 0, 0, 0, 0, 0},
                {0, 2, 2, 2, 2, 2, 0},
                {0, 2, 0, 0, 0, 0, 0},
                {0, 2, 0, 2, 2, 2, 2},
                {0, 0, 0, 0, 0, 0, 0}
        };


        Solution a = new Solution();
        System.out.println(a.maximumMinutes(grid));;
    }

}
