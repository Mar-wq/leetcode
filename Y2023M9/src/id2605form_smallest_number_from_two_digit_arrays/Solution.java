package id2605form_smallest_number_from_two_digit_arrays;

import java.util.Map;

public class Solution {
    public int minNumber(int[] nums1, int[] nums2) {
        int[] n1 = new int[10], n2 = new int[10];


        int min1 = nums1[0];
        for (int i = 0; i < nums1.length; i++) {
            n1[nums1[i]] = 1;
            min1 = Math.min(min1, nums1[i]);
        }

        int min2 = nums2[0];
        for (int i = 0; i < nums2.length; i++) {
            n2[nums2[i]] = 1;
            min2 = Math.min(nums2[i], min2);
        }

        for (int i = 1; i < 10; i++) {
            if (n1[i] == 1 && n2[i] == 1) {
                return i;
            }
        }

        return Math.min(min1, min2) * 10 + Math.max(min1, min2);
    }
}
