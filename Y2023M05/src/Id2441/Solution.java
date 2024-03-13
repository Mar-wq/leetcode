package Id2441;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int findMaxK(int[] nums) {
        Set<Integer> set = new HashSet<>();

        int res = -1;
        for (int num : nums) {
            if (set.contains(-num)) {
                res = Math.max(res, Math.abs(num));
            }
            set.add(num);
        }

        return res;
    }
}