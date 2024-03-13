package id213house_robber_ii;

import java.util.Arrays;

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return nums[0];
        }

        int[] nums1 = Arrays.copyOfRange(nums, 0, n - 1);
        int[] nums2 = Arrays.copyOfRange(nums, 1, n);

        return Math.max(robLine(nums1), robLine(nums2));
    }

    public int robLine(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][1] = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }

        return Math.max(dp[n - 1][1], dp[n - 1][0]);
    }
}

