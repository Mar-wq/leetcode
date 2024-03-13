package D11Longest_arithmetic_sequence;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {


    //这个超过了时间复杂度  没必要一个一个去找  我直接可能的情况就好了   就是多开一点数组而已
/*    public int longestArithSeqLength(int[] nums) {
        int n = nums.length, mx = nums[0], mi = nums[0];
        for (int i = 1; i < nums.length; i++) {
            mx = Math.max(mx, nums[i]);
            mi = Math.min(mi, nums[i]);
        }

        int diff = mx - mi;
        int ans = 0;
        for (int d = -diff; d <= diff; d++) {
            int[] dp = new int[n];
            Arrays.fill(dp, 1);
            int temp = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] + d ==nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                temp = Math.max(temp, dp[i]);
            }

            ans = Math.max(ans, temp);
        }

        return ans;
    }*/


    public int longestArithSeqLength(int[] nums) {
        int n = nums.length, mx = nums[0], mi = nums[0];
        for (int i = 1; i < nums.length; i++) {
            mx = Math.max(mx, nums[i]);
            mi = Math.min(mi, nums[i]);
        }

        int diff = mx - mi;
        int ans = 0;
        for (int d = -diff; d <= diff; d++) {
            int[] dp = new int[mx + 1];
            Arrays.fill(dp, -1);
            for (int num : nums) {

                //  这里会出现一种情况就是diff=0时，我直接又覆盖了前面的取值
                //dp[num] = 1;
                if (num - d >= mi && num - d <= mx && dp[num - d] != -1) {
                    dp[num] = dp[num - d] + 1;
                }

                //这种情况才是理想的
                dp[num] = Math.max(dp[num], 1);
                ans = Math.max(ans, dp[num]);
            }

        }

        return ans;
    }
}
