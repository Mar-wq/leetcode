package id26Remove_duplicates;

class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int k = 0;
        int last = -100000;
        for (int i = 0; i < n; i++) {
            if (nums[i] != last) {
                nums[k++] = nums[i];
                last = nums[i];
            }
        }

        return k;
    }
}