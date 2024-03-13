package id1123lowest_common_ancestor_of_deepest_leaves;


class TreeNode {
     int val;
    TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
        this.right = right;
     }
}


public class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).getKey();
    }

    private Pair<TreeNode, Integer>  dfs(TreeNode root) {
        if (root == null) {
            return new Pair<>(root, 0);
        }

        Pair<TreeNode, Integer> left = dfs(root.left);
        Pair<TreeNode, Integer> right = dfs(root.right);

        int val  = 0;
        if (left.getValue() > right.getValue()) {
            val = left.getValue() + 1;
            return new Pair<>(left.getKey(), val);
        }
        if (left.getValue() < right.getValue()) {
            val = right.getValue() + 1;
            return new Pair<>(right.getKey(), val);
        }

        val = left.getValue() + 1;
        return new Pair<TreeNode, Integer>(root, val);
    }

    class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
