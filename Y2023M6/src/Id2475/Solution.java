package Id2475;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int unequalTriplets(int[] nums) {

        // 排序实现
/*        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;

        for (int i = 0, j = 0; i < n; i = j) {
            while (j < n && nums[j] == nums[i]) {
                j++;
            }
            ans += i * (j - i) * (n - j);
        }

        return ans;*/


        // hashmap实现
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.merge(num, 1, Integer::sum);
        }

        int res = 0, n = nums.length, t = 0;

        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            res += t * entry.getValue() * (n - t - entry.getValue());
            t += entry.getValue();
        }

        return res;
    }
}
