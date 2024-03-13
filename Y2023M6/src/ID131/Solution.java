package ID131;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private int n;

    public List<List<String>> partition(String s) {
        n = s.length();
        boolean[][] dp = new boolean[n][n];

        List<List<String>> res = new ArrayList<>();

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[i][j] = true;
                } else if (i + 1 == j) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j)) );
                }
            }
        }
        List<String> cur = new ArrayList<>();
        dfs(0, dp, res, cur, s);

        return res;
    }

    private void dfs(int pos, boolean[][] dp, List<List<String>> res, List<String> cur, String s) {
        if (pos >= n) {
            // 这里放入的是同一个对象  后面全被删了   所以看到的是空白的
            res.add(new ArrayList<String>(cur));
        }

        for (int i = pos; i < n; i++) {
            if (dp[pos][i]) {
                // 注意是下取上不取
                cur.add(s.substring(pos, i));
                dfs(i + 1, dp, res, cur, s);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
