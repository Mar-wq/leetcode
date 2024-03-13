package id88merge_sorted_array;

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1, p3 = m + n - 1;
        int sentry = Integer.MIN_VALUE;
        while (p2 >= 0) {
            if (p1 >= 0) {
                if (nums2[p2] >= nums1[p1]) {
                    nums1[p3] = nums2[p2];
                    p3--;
                    p2--;
                } else {
                    nums1[p3] = nums1[p1];
                    p1--;
                    p3--;
                }
            } else {
                nums1[p3--] = nums2[p2--];
            }

        }
    }
}