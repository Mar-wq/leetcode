package Id1377frog;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public double frogPosition(int n, int[][] edges, int t, int target) {

        // 这里也得学习哈  如何开辟二维数组
        List<Integer>[] G = new ArrayList[n + 1];
        //int[] a = new int[3];
       // List<int[]> G = new ArrayList<>(n + 1);

        for (int i = 0; i <= n; i++) {
            G[i] = new ArrayList<>();
            //G.add(new int[2]);
        }

        for (int[] e : edges) {
            G[e[0]].add(e[1]);
            G[e[1]].add(e[0]);
        }

        boolean[] seen = new boolean[n + 1];

        return dfs(G, seen, 1, t, target);
    }

    private double dfs(List<Integer>[] G, boolean[] seen, int i, int t, int target) {
        int nxt = i == 1 ? G[i].size() : G[i].size() - 1;

        if (t == 0 || nxt == 0) {
            return i == target ? 1.0 : 0.0;
        }

        seen[i] = true;

        double ans = 0;
        for (Integer j : G[i]) {
            if (!seen[j]) {
                ans += dfs(G, seen, j, t - 1, target);
            }
        }

        return ans / nxt;
    }
}