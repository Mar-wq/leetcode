package com.hdy.leetcode.id318_maximum_product_of_word_length;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

class Solution {
    public int maxProduct(String[] words) {
        int n = words.length;
        int[] lengths = new int[n];
        Arrays.setAll(lengths, new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                return words[operand].length();
            }
        });
        
        int[] wordBits = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < lengths[i]; j++) {
                int k = words[i].charAt(j) - 'a';
                wordBits[i] |= (1 << k);
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((wordBits[i] & wordBits[j]) == 0) {
                    res = Math.max(res, lengths[i] * lengths[j]);
                }
            }
        }

        return res;
    }


}
