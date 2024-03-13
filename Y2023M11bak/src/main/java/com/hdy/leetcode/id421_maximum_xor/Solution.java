package com.hdy.leetcode.id421_maximum_xor;

import java.util.HashSet;
import java.util.Set;

class Solution {
    private static final int HIGH_BIT = 30;    
    
    public int findMaximumXOR(int[] nums) {

        int x = 0;
        for (int k = HIGH_BIT; k >= 0 ; k--) {
            Set<Integer> set = new HashSet<>();

            for (int num : nums) {
                set.add(num >> k);
            }

            int xNext = x * 2 + 1;
            boolean found = false;

            for (int num : nums) {
                if (set.contains(xNext ^ (num >> k))) {
                    found = true;
                    break;
                }
            }

            if (found) {
                x = xNext;
            } else {
                x = xNext - 1;
            }
        }

        return x;
    }
}