package Id2460;

class Solution {
    public int[] applyOperations(int[] nums) {
        int n = nums.length;
        for (int i = 0, j = 0; i < n; i++) {
            if (i < n - 1 && nums[i] == nums[i + 1]) {
                nums[i] *= 2;
                nums[i + 1] = 0;
            }
            // 只要不是0就把它往前面放，这样自然而然最后都是些0
            if (nums[i] != 0) {
                swap(i, j, nums);
                j++;
            }
        }

        return nums;
    }
    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
