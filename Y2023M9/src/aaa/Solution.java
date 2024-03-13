package aaa;

import java.util.Arrays;

public class Solution {
    final static int MOD = (int) (1e9 + 7);
    public double sumOfAllSubsequence(int[] nums) {
        int n = nums.length;
        double sum = Arrays.stream(nums).sum();
        double res = sum;
        for (int m = 1; m <= n - 1; m++) {
            long cnt = calculateCombination(n - 1, m);
            res += cnt  * sum * findModularInverse((m + 1), MOD) % MOD;
        }

        return res;
    }


    // 计算组合数 C(n, m)
    public long calculateCombination(int n, int m) {
        if (m < 0 || m > n) {
            return 0;
        }

        long[][] dp = new long[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, m); j++) {
                if (j == 0 || j == i) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }
        }

        return dp[n][m];
    }


    // 使用扩展欧几里得算法计算逆元
    public  int findModularInverse(int a, int m) {
        int m0 = m, t, q;
        int x0 = 0, x1 = 1;

        if (m == 1) return 0;

        while (a > 1) {
            q = a / m;
            t = m;
            m = a % m;
            a = t;
            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }

        if (x1 < 0) x1 += m0;

        return x1;
    }

    public static void main(String[] args) {
        Solution aaa = new Solution();
        int[] a = {2, 1};
        System.out.println(aaa.sumOfAllSubsequence(a));

    }
}
