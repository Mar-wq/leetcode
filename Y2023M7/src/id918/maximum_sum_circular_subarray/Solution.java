package id918.maximum_sum_circular_subarray;

class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        // 不是环形数组的解决防范
        int leftMax[] = new int[n];
        leftMax[0] = nums[0];

        int res = nums[0], pre = nums[0], leftSum = nums[0];
        for (int i = 1; i < n; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            res = Math.max(res, pre);
            leftSum += nums[i];
            leftMax[i] = Math.max(leftMax[i - 1], leftSum);
        }
        int sum = 0;
        for (int j = n - 1; j > 0; j--) {
            sum += nums[j];
            res = Math.max(res, leftMax[j - 1] + sum);
        }

        return res;

    }
}
