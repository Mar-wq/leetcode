package D18The_maximum_difference_between_a_node_and_its_ancestor;

import java.util.ArrayList;
import java.util.List;

/*public class Solution {
    private int ans;
    public int maxAncestorDiff(TreeNode root) {
        //Integer ans = Integer.valueOf(0);
        List<Integer> arr = new ArrayList<Integer>();

        dfs(root, arr);

        return ans;
    }

    private void dfs(TreeNode root, List<Integer> arr) {
        if (root == null) {
            return ;
        }
        int n = arr.size();
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, Math.abs(root.val - arr.get(i)));
        }
        arr.add(root.val);
        dfs(root.left, arr);
        dfs(root.right, arr);
        arr.remove(n);
    }
}*/


//这里存在的Interger的引用问题还没有解决      后面看情况处理

public class Solution {
    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
    }

    private int dfs(TreeNode root, int mi, int mx) {
        if (root == null) {
            return 0;
        }

        int ans = Math.abs(root.val - mi);
        ans = Math.max(ans, Math.abs(root.val - mx));

        int diffL = dfs(root.left, Math.min(mi, root.val), Math.max(mx, root.val));
        int diffR = dfs(root.right, Math.min(mi, root.val), Math.max(mx, root.val));

        ans = Math.max(ans, Math.max(diffL, diffR));

        return ans;
    }
}

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
