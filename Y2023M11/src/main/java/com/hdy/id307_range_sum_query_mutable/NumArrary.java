package com.hdy.id307_range_sum_query_mutable;

class NumArray {
    private int[] segmentTree;
    private int n;
    public NumArray(int[] nums) {
        this.n = nums.length;

        segmentTree = new int[4 * n];
        build(0, 0, n - 1, nums);
    }

    private int build(int id, int s, int e, int[] nums) {
        if (s == e) {
            segmentTree[id] = nums[s];
            return nums[s];
        }
        int mid = (s + e) / 2;

        segmentTree[id] = build(2 * id + 1, s, mid, nums) + build(2 * id + 2, mid + 1, e, nums);

        return segmentTree[id];
    }

    public void update(int index, int val) {
        change(index, val, 0, 0, n - 1);
    }

    private int change(int index, int val, int id, int s, int e) {
        if (index < s || index > e) {
            return segmentTree[id];
        }

        if (s == e && s == index) {
            return val;
        }
        int mid = (s + e) / 2;
        segmentTree[id] = change(index, val, 2 * id + 1, s, mid) + change(index, val, 2 * id + 2, mid + 1, e);

        return segmentTree[id];
    }

    public int sumRange(int left, int right) {
        return range(left, right, 0, 0, n - 1);
    }

    private int range(int left, int right, int id, int s, int e) {
        if (Math.max(left, s) > Math.min(right, e)) {
            return 0;
        }

        if (left <= s && e <= right) {
            return segmentTree[id];
        }

        int mid = (s + e) / 2;

        return range(left, right, id * 2 + 1, s, mid) + range(left, right, id * 2 + 2, mid + 1, e);
    }

    public static void main(String[] args) {
        int[] myArray = {1, 3, 5};
        NumArray a = new NumArray(myArray);
        int sum = a.sumRange(0, 2);
        System.out.println(sum);
    }

}
