package id1749maximum_absolute_sum_of_any_subarray;

class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        // 初始化
        dp[0][0] = nums[0]; dp[0][1] = nums[0];
        // 返回值就考虑数据为空的情况
        int res = Math.abs(nums[0]);
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + nums[i], nums[i]);
            dp[i][1] = Math.min(dp[i - 1][1] + nums[i], nums[i]);

            res = Math.max(res, Math.max(Math.abs(dp[i][0]), Math.abs(dp[i][1])));
        }


        return res;
    }
}