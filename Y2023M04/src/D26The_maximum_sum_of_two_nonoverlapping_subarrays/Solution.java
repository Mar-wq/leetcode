package D26The_maximum_sum_of_two_nonoverlapping_subarrays;

public class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        return Math.max(help(nums, firstLen, secondLen), help(nums, secondLen, firstLen));
    }

    public int help(int[] nums, int firstLen, int secondlen) {
        int suml = 0;
        for (int i = 0; i < firstLen; i++) {
            suml += nums[i];
        }
        int maxSumL = suml;

        int sumr = 0;

        for (int i = firstLen; i < firstLen + secondlen; i++) {
            sumr += nums[i];
        }

        int res = maxSumL + sumr;

        for (int i = firstLen + secondlen, j = firstLen; i < nums.length; i++, j++) {
            suml += nums[j]  - nums[j - firstLen];
            maxSumL = Math.max(suml, maxSumL);
            sumr += nums[i] - nums[i - secondlen];
            res = Math.max(res, maxSumL + sumr);
        }

        return res;
    }
}
