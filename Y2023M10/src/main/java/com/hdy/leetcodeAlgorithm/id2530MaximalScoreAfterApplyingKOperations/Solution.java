package com.hdy.leetcodeAlgorithm.id2530MaximalScoreAfterApplyingKOperations;

import java.util.PriorityQueue;

class Solution {
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int num : nums) {
            pq.add(num);
        }

        long res = 0;
        while (k > 0) {
            --k;
            Integer choosed = pq.poll();
            res += choosed;
            int ceil = (choosed + 3) % 3;
            pq.add(ceil);
        }

        return res;
    }
}