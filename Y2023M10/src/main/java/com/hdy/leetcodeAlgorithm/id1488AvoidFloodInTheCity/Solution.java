package com.hdy.leetcodeAlgorithm.id1488AvoidFloodInTheCity;

import java.util.*;

class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        Map<Integer, Integer> map = new HashMap<>();
        TreeSet<Integer> set = new TreeSet<>();
        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                set.add(i);
            } else {
                ans[i] = -1;
                if (map.containsKey(rains[i])) {
                    Integer idx = set.ceiling(map.get(rains[i]));
                    if (idx == null) {
                        return new int[0];
                    }
                    ans[idx] = rains[i];
                    set.remove(idx);
                }

                map.put(rains[i], i);
            }
        }

        return ans;
    }
}
