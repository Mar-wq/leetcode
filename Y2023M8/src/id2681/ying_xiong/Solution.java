package id2681.ying_xiong;

import java.util.Arrays;

public class Solution {
    public int sumOfPower(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0, mod = 1000000007;
        int[] dp = new int[n];
        int[] preSum = new int[n];
        preSum[0] = 0;
        dp[0] = nums[0];
        res = nums[0] * nums[0] * dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = (nums[i] + preSum[i - 1]) % mod;
            dp[i] += nums[i];
            res += (dp[i] * nums[i] % mod * nums[i]) % mod;

            preSum[i] = preSum[i - 1] + dp[i];
        }

        return res;
    }
}
