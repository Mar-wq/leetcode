package com.hdy.id715_range_module;

class RangeModule {
    class Node {
        Node left, right;
        boolean cover;
        int add;
    }


    private int N = (int) 1e9;
    private Node root = new Node();

    public void update(Node node, int start, int end, int l, int r, int val) {
        if (l <= start && end <= r) {
            node.cover = val == 1;
            node.add = val;

            return ;
        }

        int mid = (start + end) >> 1;
        pushDown(node, mid - start + 1, end - mid);
        if (l <= mid) update(node.left, start, mid, l, r, val);
        if (r >= mid) update(node.right, mid + 1, end, l, r, val);

        pushUp(node);
    }

    private void pushUp(Node node) {
        node.cover = node.left.cover && node.right.cover;
    }

    private void pushDown(Node node, int leftNum, int rightNum) {
        if (node.left == null) node.left = new Node();
        if (node.right == null) node.right = new Node();
        if (node.add == 0) return;

        node.left.cover = node.add == 1;
        node.right.cover = node.add == 1;

        node.left.add = node.add;
        node.right.add = node.add;

        node.add = 0;
    }

    public boolean query(Node node, int start, int end, int l, int r) {
        if (l <= start && end <= r) return node.cover;
        int mid = (start + end) >> 1;
        boolean ans = true;
        pushDown(node, mid - start + 1, end - mid);
        if (l <= mid) ans &= query(node.left, start, mid, l, r);
        if (r > mid) ans &= query(node.right, mid + 1, end, l, r);
        return ans;

    }



    public RangeModule() {

    }

    public void addRange(int left, int right) {
        update(root, 1, N, left, right - 1, 1);
    }

    public boolean queryRange(int left, int right) {
        return query(root, 1, N, left, right - 1);
    }

    public void removeRange(int left, int right) {
        update(root, 1, N, left, right - 1, -1);
    }
}