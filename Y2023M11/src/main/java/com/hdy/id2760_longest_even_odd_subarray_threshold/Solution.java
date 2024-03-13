package com.hdy.id2760_longest_even_odd_subarray_threshold;

class Solution {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int n = nums.length;
        int[] f = new int[n];
        f[0] = eligeble(nums[0], threshold);
        int res = f[0];
        for (int i = 1; i < n; i++) {
            f[i] = eligeble(nums[i], threshold);
            if (isContinue(nums, i, threshold)) {
                f[i] = f[i - 1] == 0 ? 0 : f[i] + 1;
            }
            res = Math.max(f[i], res);
        }

        return res;
    }

    private boolean isContinue(int[] nums, int i, int threshold) {
        if (nums[i] > threshold) {
            return false;
        }

        if (nums[i] % 2 == nums[i - 1] % 2) {
            return false;
        }

        return true;
    }

    private int eligeble(int num, int threshold) {
        return (num <= threshold) && num % 2 == 0 ? 1 : 0;
    }
}