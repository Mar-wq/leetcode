package Id375Guess;

import java.util.Arrays;

public class Solution {
/*    private int[][] memo;
    public int getMoneyAmount(int n) {
       memo = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }

        return  dfs(1, n);
    }

    private int dfs(int start, int end) {
        if (start >= end) {
            memo[start][end] = 0;
            return 0;
        }

        if (start + 1 ==  end) {
            memo[start][end] = start;
            return start;
        }

        if (memo[start][end] != -1) {
            return memo[start][end];
        }

        int res = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            res = Math.min(res, i + Math.max(dfs(start, i - 1), dfs(i + 1, end)));
        }
        memo[start][end] = res;

//        int mid = start + (end - start) / 2;
//        res = Math.max(dfs(start, mid - 1), dfs(mid + 1, end)) + mid;

        return res;
    }*/

    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];

        for (int i = n - 1; i > 0; i--) {
            for (int j = i + 1; j <= n; j++) {
                // 相当于初始化这个值，当前状况下的最大值了
                dp[i][j] = dp[i][j - 1] + j;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i][k - 1], dp[k + 1][j]) + k);
                }
            }
        }

        return dp[1][n];
    }
}
