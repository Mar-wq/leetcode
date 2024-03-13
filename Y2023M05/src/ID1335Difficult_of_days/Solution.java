package ID1335Difficult_of_days;

import java.util.Arrays;

class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;

        if (n < d) {
            return -1;
        }

        int[][] dp = new int[d + 1][n + 1];
        for (int i = 0; i <= d; i++) {
            Arrays.fill(dp[i], 0x3f3f3f);
        }

        int mx = 0;
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, jobDifficulty[i]);
            dp[1][i + 1] = mx;
        }

        for (int i = 2; i <= d; i++) {
            for (int j = i; j <= n; j++) {
                mx = 0;
                for (int k = j - 1; k >= i; --k) {
                    mx = Math.max(mx, jobDifficulty[k]);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + mx);
                }
            }
        }

        return dp[d][n];
    }
}
