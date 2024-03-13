package com.hdy.leetcodeAlgorithm.id129SumRootToLeafNumber;


import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Solution {
    private List<String> list;

    public int sumNumbers(TreeNode root) {
        StringBuilder sb = new StringBuilder("");
        list = new ArrayList<>();

        dfs(root, sb);

        int res = list.stream().mapToInt(Integer::valueOf).sum();

        return res;
    }

    void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }

        sb.append(root.val);
        if (root.left == null && root.right == null) {
            list.add(sb.toString());
        }
        dfs(root.left, sb);
        dfs(root.right, sb);

        sb.deleteCharAt(sb.length() - 1); // 删除最后一个字符
    }
}
