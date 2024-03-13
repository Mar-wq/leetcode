package test;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int smallestRepunitDivByK(int k) {
        int resid = 1 % k, len = 1;
        Set<Integer> set = new HashSet<>();
        set.add(resid);

        while (resid != 0) {
            resid = (resid * 10 + 1) % k;
            if (set.contains(resid)) {
                return -1;
            }
            len++;
            set.add(resid);
        }

        return len;
    }
}