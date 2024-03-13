package id718maximum_length_of_repeated_subarray;

public class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;

        int dp[][] = new int[n + 1][m + 1];

        int ans = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = nums1[i - 1] == nums2[j - 1] ? dp[i - 1][j - 1]  + 1: 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }

        return ans;
    }
}
