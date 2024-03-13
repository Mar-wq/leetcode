package com.hdy.leetcodeAlgorithm.id2562FindTheArrayConcatenationValue;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public long findTheArrayConcVal(int[] nums) {
        int n = nums.length;
        int i = 0, j = n - 1;

        long res = 0;
        // 双指针
        while (i <= j) {
            if (i == j) {
                res += nums[i];
                break;
            }

            res += concat(nums[i], nums[j]);
            i++;
            j--;
        }

        return res;
    }

    private long concat(int a, int b) {
        // 下次数字拼接的东西记得用字符串   别在底层纠结  没多少意义
        return Long.parseLong(Integer.toString(a) + Integer.toString(b));
    }

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("aaa");
        //set.remove("aaa");
        System.out.println(set.contains("aaa"));
    }
}