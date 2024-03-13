package Id1262dividThree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    /*public int maxSumDivThree(int[] nums) {
        PriorityQueue<Integer>[] pArr = new PriorityQueue[3];
        pArr[1] = new PriorityQueue<>();
        pArr[2] = new PriorityQueue<>();
        int sum = 0;
        for (int num : nums) {
            sum += num;
            int key = num % 3;
            if (key != 0) {
                pArr[key].add(num);
            }
        }

        int remainder = sum % 3;
        if (remainder == 0) {
            return sum;
        } else if (remainder == 1) {
            int option1 = Integer.MAX_VALUE;
            if (pArr[2].size() >= 2) {
                option1 = pArr[2].poll() + pArr[2].poll();
            }
            int option2 = Integer.MAX_VALUE;
            if (pArr[1].size() >= 1) {
                option2 = pArr[1].poll();
            }
            int option = Math.min(option1, option2);
            return sum - option;
        } else {
            int option1 = Integer.MAX_VALUE;
            if (pArr[1].size() >= 2) {
                option1 = pArr[1].poll() + pArr[1].poll();
            }
            int option2 = Integer.MAX_VALUE;
            if (pArr[2].size() >= 1) {
                option2 = pArr[2].poll();
            }
            int option = Math.min(option1, option2);

            return sum - option;
        }

    }*/

    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][3];
        dp[0][1] = -1; dp[0][2] = -1;

        for (int i = 1; i <= n; i++) {
            int left = nums[i - 1] % 3;
            int dis = 3 - left;
            if (left == 0) {
                dp[i][0] = dp[i - 1][0] + nums[i - 1];
                dp[i][1] = dp[i - 1][1] == -1 ? -1 : nums[i - 1] + dp[i - 1][1];
                dp[i][2] = dp[i - 1][2] == -1 ? -1 : nums[i - 1] + dp[i - 1][2];
            }
            else if (left == 1) {

                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][dis] == -1 ? -1 : dp[i - 1][dis] + nums[i - 1]);
                dp[i][1] = Math.max(dp[i-1][0] + nums[i - 1], dp[i - 1][1]);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] == -1 ? -1 : dp[i - 1][1] + nums[i - 1]);
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][dis] == -1 ? -1 : dp[i - 1][dis] + nums[i - 1]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] == -1 ? -1 : dp[i - 1][2] + nums[i - 1]);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] + nums[i - 1]);
            }
        }

        return dp[n][0];
    }
}
