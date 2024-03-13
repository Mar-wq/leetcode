package com.hdy.leetcodeAlgorithm.id2731MovementOfRobots;

import java.util.Arrays;

class Solution {
    private static final int MOD = 1000000007;

    public int sumDistance(int[] nums, String s, int d) {
        for (int i = 0; i < nums.length; i++) {
            if (s.charAt(i) == 'R') {
                nums[i] += d;
            } else {
                nums[i] -= d;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }

        Arrays.sort(nums);

        long res = 0;
        // 这里如果想降低复杂度，可以考虑前缀和   发现最后要降低复杂度，最终都归为了数学
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                res += (res % MOD + ((nums[j] - nums[i]) % MOD) % MOD) % MOD;
            }
        }

        return (int) res;
    }
}
