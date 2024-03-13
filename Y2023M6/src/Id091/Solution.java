package Id091;

class Solution {
    public int minCost(int[][] costs) {
        int n  = costs.length;
        int[][] dp = new int[n + 1][3];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]) + costs[i - 1][j];
            }
        }

        return Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));
    }
}
