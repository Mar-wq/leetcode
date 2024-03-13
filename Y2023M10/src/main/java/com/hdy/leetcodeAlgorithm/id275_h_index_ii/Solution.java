package com.hdy.leetcodeAlgorithm.id275_h_index_ii;

class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;

        int l = 0, r = n;

        while (l < r) {
            int mid = (l + r) / 2;
            int cnt = citations[mid];
            int rank = n - mid;

            if (cnt >= rank) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return n - l;
    }

    public static void main(String[] args) {
        int i = 0;
        int a = 0;

        while (i < 10) {
            a = a++;
            i++;
        }

        System.out.println(a);
    }
}
