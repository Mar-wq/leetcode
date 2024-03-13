package Id1240;

/*
class Solution {
    public int tilingRectangle(int n, int m) {
        if (n < m) {
            return tilingRectangle(m, n);
        }

        if (n % m == 0) {
            return n / m;
        }

        int res = 0;
        res += n / m;
        res += tilingRectangle(m, n % m);

        return res;
    }
}*/


class Solution {
    int ans;

    public int tilingRectangle(int n, int m) {
        ans = Math.max(n, m);
        boolean[][] rect = new boolean[n][m];
        dfs(0, 0, rect, 0);

        return ans;
    }


    private void dfs(int x, int y, boolean[][] rect, int cnt) {
        int n = rect.length, m = rect[0].length;

        if (cnt >= ans) {
            return ;
        }

        if (x >= n) {
            ans = cnt;
            return ;
        }

        /*检测下一行*/
        if (y >= m) {
            dfs(x + 1, 0, rect, cnt);
            return;
        }

        if (rect[x][y]) {
            dfs(x, y + 1, rect, cnt);
            return ;
        }

        for (int k = Math.min(n - x, m - y); k >= 1 && isAvailable(rect, x, y, k); k--) {
            /*将长度为k的正方形区域标记覆盖*/
            fillUp(rect, x, y, k, true);
            dfs(x, y + k, rect, cnt + 1);
            fillUp(rect, x, y, k, false);
        }
    }

    public boolean isAvailable(boolean[][] rect, int x, int y, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (rect[x + i][y + j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void fillUp(boolean[][] rect, int x, int y, int k, boolean val) {
        for (int i = 0; i < k; i++){
            for (int j = 0; j < k; j++) {
                rect[x + i][y + j] = val;
            }
        }
    }
}
