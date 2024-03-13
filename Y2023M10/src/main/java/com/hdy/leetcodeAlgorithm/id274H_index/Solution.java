package com.hdy.leetcodeAlgorithm.id274H_index;

class Solution {
    public int hIndex(int[] citations) {
        int l = 0, r = 0;
        for (int citation : citations) {
            r =  Math.max(citation, r);
        }

        while (l <= r) {
            int mid = (l + r) / 2;
            if (cnt(mid, citations) > mid) {
                l = mid + 1;
            } else if (cnt(mid, citations) == mid) {
                return mid;
            }
            else {
                r = mid - 1;
            }
        }

        return l - 1;
    }

    private int cnt(int h, int[] citations) {
        int cnt = 0;
        for (int citation : citations) {
            if (citation >= h) {
                cnt++;
            }
        }

        return cnt;
    }
}
