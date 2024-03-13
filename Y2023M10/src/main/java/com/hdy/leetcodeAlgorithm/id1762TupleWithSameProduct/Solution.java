package com.hdy.leetcodeAlgorithm.id1762TupleWithSameProduct;

import java.util.Collection;
import java.util.HashMap;

class Solution {
    public int tupleSameProduct(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Integer key = nums[i] * nums[j];
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }

        int res = 0;
        Collection<Integer> values = map.values();
        for (Integer value : values) {
            res += value * (value - 1) * 4;
        }

        return res;
    }
}
