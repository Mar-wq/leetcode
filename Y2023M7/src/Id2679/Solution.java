package Id2679;

import java.util.Arrays;

public class Solution {
    public int matrixSum(int[][] nums) {
        int m = nums.length, n = nums[0].length;

        for (int i = 0; i < m; i++) {
            Arrays.sort(nums[i]);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            int mxc = nums[0][i];
            for (int j = 0; j < m; j++) {
                mxc = Math.max(mxc, nums[j][i]);
            }

            res += mxc;
        }

        return res;
    }
}
