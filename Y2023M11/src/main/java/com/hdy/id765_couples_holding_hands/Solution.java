package com.hdy.id765_couples_holding_hands;

class Solution {
    private int[] parent;
    private int[] rank;


    // 查找元素所属的集合（根节点）
    public int find(int element) {
        if (parent[element] != element) {
            parent[element] = find(parent[element]); // 路径压缩
        }
        return parent[element];
    }

    // 合并两个集合
    public void union(int element1, int element2) {
        int root1 = find(element1);
        int root2 = find(element2);

        if (root1 != root2) {
            // 将 rank 较小的集合合并到 rank 较大的集合上
            if (rank[root1] > rank[root2]) {
                parent[root2] = root1;
                rank[root1] += rank[root2];
            } else if (rank[root1] < rank[root2]) {
                parent[root1] = root2;
                rank[root2] += rank[root1];
            } else {
                parent[root2] = root1;
                rank[root1] += rank[root2];
            }
        }
    }

    // 判断两个元素是否属于同一集合
    public boolean isConnected(int element1, int element2) {
        return find(element1) == find(element2);
    }

    public int minSwapsCouples(int[] row) {
        int n = row.length;
        parent = new int[n];
        rank = new int[n];

        // 初始化，每个元素的父节点是它自己，rank 初始为 1
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        for (int i = 0; i < n; i += 2) {
            int a = row[i], b = row[i + 1];
            this.union(a, b);
            int amate = (a % 2) == 0 ? a + 1 : a - 1;
            int bmate = (b % 2) == 0 ? b + 1 : b - 1;
            union(a, amate);
            union(b, bmate);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (i == parent[i]) {
                res += (rank[i] / 2 - 1);
            }
        }

        return res;
    }
}