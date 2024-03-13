package com.hdy.leetcodeAlgorithm.id260SingleNumberIII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

class Solution {
    public int[] singleNumber(int[] nums) {
        int xorSum = 0;
        for (int num : nums) {
            xorSum ^= num;
        }

        // 防止溢出
        int lsb = xorSum == Integer.MIN_VALUE ? xorSum : xorSum & (-xorSum);
        int type1 = 0, type2 = 0;

        for (int num : nums) {
            if ((num & lsb) != 0) {
                type1 ^= num;
            } else {
                type2 ^= num;
            }
        }

        return new int[]{type1, type2};
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"aa", "bb", "cc");

        new Consumer<Thread>() {
            @Override
            public void accept(Thread thread) {
                thread.start();
            }
        };
        Consumer<Thread> a = Thread::start;
    }
}
