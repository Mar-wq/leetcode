package Id1110delectPointToManyTree;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = new HashSet<>();
        for (int d : to_delete) {
            set.add(d);
        }

        List<TreeNode> res = new ArrayList<>();

        root = dfs(root, set, res);

        if (root != null) {
            res.add(root);
        }

        return res;
    }

    private TreeNode dfs(TreeNode node, Set<Integer> s, List<TreeNode> ans) {
        if (node == null) {
            return null;
        }

        node.left = dfs(node.left, s, ans);
        node.right = dfs(node.right, s, ans);

        if (s.contains(node.val)) {
            if (node.left != null) {
                ans.add(node.left);
            }

            if (node.right != null) {
                ans.add(node.right);
            }

            return null;
        }

        return node;
    }
}