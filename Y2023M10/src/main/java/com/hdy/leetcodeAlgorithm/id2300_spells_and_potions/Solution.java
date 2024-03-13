package com.hdy.leetcodeAlgorithm.id2300_spells_and_potions;

import java.util.Arrays;

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        for (int potion : potions) {
            System.out.println(potion);
        }

        for (int i = 0; i < potions.length; i++) {
            potions[i] = (int) ((success + potions[i] - 1) / potions[i]);
        }

        int n = spells.length;
        int[] res = new int[n];

        for (int i = 0; i < spells.length; i++) {
            res[i] = findSuccessNum(spells[i], potions);
        }

        return res;
    }

    private int findSuccessNum(int spell, int[] potions) {
        int n = potions.length;
        // 这个点在处理特殊情况的时候很有用  记录最后一次满足要求的位置，遇到特殊的边界情况就不用讨论了

        int pos = n;
        int l = 0, r = n - 1;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (potions[mid] <= spell) {
                pos = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

/*        if (r == -1) {
            return n;
        } else if (l == n) {
            return 0;
        } else {
            return n - l;
        }*/

        return n - pos;
    }

}
