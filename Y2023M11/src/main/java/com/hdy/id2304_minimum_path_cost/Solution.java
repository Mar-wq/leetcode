package com.hdy.id2304_minimum_path_cost;

import java.util.Arrays;

class Solution {
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length, n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dfs(grid, moveCost, memo, 0, i));
        }

        return res;
    }

    private int dfs(int[][] grid, int[][] moveCost, int[][] memo, int i, int j) {
        if (i == grid.length) {
            memo[i][j] = grid[i][j];
            return grid[i][j];
        }

        if (memo[i][j] != Integer.MAX_VALUE) {
            return memo[i][j];
        }

        for (int k = 0; k < grid[i].length; k++) {
            memo[i][j] = Math.min(memo[i][j], dfs(grid, moveCost, memo, i + 1, k) + moveCost[grid[i][j]][k] + grid[i][j]);
        }

        return memo[i][j];
    }

}
