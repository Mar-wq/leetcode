package id931minimum_falling_path_sum.Solution;

import java.util.Arrays;

public class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        dp[n - 1] = matrix[n - 1].clone();

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + matrix[i][j];
                } else if (j == n - 1) {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j - 1]) + matrix[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], Math.min(dp[i + 1][j - 1], dp[i + 1][j + 1])) + matrix[i][j];
                }
            }
        }

        int res = dp[0][0];
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp[0][i]);
        }

        return res;
    }
}
