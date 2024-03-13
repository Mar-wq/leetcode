package id337house_robber_three;


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

public class Solution {
    public int rob(TreeNode root) {
        int[] result = dfs(root);

        return Math.max(result[0], result[1]);
    }

    int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs(root.left);
        int[] r = dfs(root.right);
        int choosed = l[0] + r[0] + root.val;
        int unChoosed = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);

        return new int[]{unChoosed, choosed};
    }
}
