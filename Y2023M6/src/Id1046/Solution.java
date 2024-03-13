package Id1046;


public class Solution {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length, sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        boolean[][] dp = new boolean[n + 1][sum / 2 + 1];
        dp[0][0] = true;

        int cur = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum / 2; j++) {
                if (j == 0) {
                    dp[i][j] = true;
                    continue;
                }

                if (j >= stones[i - 1]) {
                    dp[i][j] = (dp[i - 1][j] | dp[i - 1][j - stones[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
                if (dp[i][j]) {
                    cur = Math.max(cur, j);
                }
            }
        }

        return sum - 2 * cur;
    }
}
