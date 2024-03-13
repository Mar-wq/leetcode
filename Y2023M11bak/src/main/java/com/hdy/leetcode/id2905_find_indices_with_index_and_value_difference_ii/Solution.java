package com.hdy.leetcode.id2905_find_indices_with_index_and_value_difference_ii;

class Solution1 {
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        int maxIdx = 0, minIdx = 0;
        for (int j = indexDifference; j < n; j++) {
            int i = j - indexDifference;
            if (nums[maxIdx] < nums[i]) {
                maxIdx = i;
            }

            if (nums[minIdx] > nums[i]) {
                minIdx = i;
            }

            if (nums[maxIdx] - nums[j] >= valueDifference) {
                return new int[]{maxIdx, j};
            }

            if (nums[j] - nums[minIdx] >= valueDifference) {
                return new int[]{minIdx, j};
            }
        }

        return new int[]{-1, -1};
    }
}

class Solution {





    public int maxProfit(int[] prices) {
        int n = prices.length;

        int minIdx = 0;
        int maxProfit = 0;
        for (int j = 1; j < n; j++) {
            int i = j - 1;
            if (prices[i] < prices[minIdx]) {
                minIdx = i;
            }

            maxProfit = Math.max(maxProfit, prices[j] - prices[minIdx]);
        }

        return maxProfit;
    }
}










