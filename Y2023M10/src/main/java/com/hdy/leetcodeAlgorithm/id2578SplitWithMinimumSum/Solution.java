package com.hdy.leetcodeAlgorithm.id2578SplitWithMinimumSum;

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int splitNum(int num) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (num > 0) {
            int n = num % 10;
            num /= 10;
            if (n != 0) {
                pq.add(n);
            }
        }

        int[] twoNum = new int[2];
        int idx = 0;
        while (!pq.isEmpty()) {
            Integer n = pq.poll();
            twoNum[idx] = twoNum[idx] * 10 + n;
            idx = (idx + 1) % 2;
        }

        return twoNum[0] + twoNum[1];
    }
}
