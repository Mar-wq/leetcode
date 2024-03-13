package id16ThreeSum;

import java.util.Arrays;

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = nums[0] +  nums[1] + nums[2];
        for (int i = 0; i < n - 2; i++) {
            int twoClo = twoSumClosest(nums, i + 1, target - nums[i]);
            if (Math.abs(target - (twoClo + nums[i])) < Math.abs(target - res)) {
                res = twoClo + nums[i];
            }
        }

        return res;
    }

    int twoSumClosest(int[] nums, int start, int target) {
        int lo = start, n = nums.length, hi = n - 1;

        int res = nums[start] + nums[start + 1];
        while (lo < hi) {
            if(Math.abs(target - (nums[lo] + nums[hi])) < Math.abs(target - res)) {
                res = nums[lo] + nums[hi];
            }

            if (nums[lo] + nums[hi] > target) {
                hi--;
            } else if (nums[lo] + nums[hi] < target) {
                lo++;
            } else {
                break;
            }
        }

        return res;
    }
}
