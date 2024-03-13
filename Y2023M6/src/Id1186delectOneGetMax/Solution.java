package Id1186delectOneGetMax;

class Solution {
    /*private int[] tree;
    int[] backArr;

    public int maximumSum(int[] arr) {
        int n = arr.length;
        this.backArr = arr;
        this.tree = new int[4 * n];
        buildTree(1, 0, n - 1);

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                if (i > j) {
                    res = Math.max(res, sum - query(1, 0, n - 1, i, j));
                } else {
                    res = Math.max(res, sum);
                }
            }
        }

        return res;
    }

    private int buildTree(int id, int l, int r) {
        if (l == r) {
            tree[id] = backArr[l] >= 0 ? 0 : backArr[l];
            return tree[id];
        }

        int mid = (l + r) / 2;
        int lVal = buildTree(id * 2, l, mid);
        int rVal = buildTree(id * 2 + 1, mid + 1, r);
        tree[id] = Math.min(lVal, rVal);

        return tree[id];
    }

    int query(int id, int l, int r, int ql, int qr) {
        if (l > qr || r < ql) {
            return (int) 1e6;
        }

        if (l >= ql && r <= qr) {
            return tree[id];
        }

        int mid = (l + r) / 2;
        int lVal = query(id * 2, l, mid, ql, qr);
        int rVal = query(id * 2 + 1, mid + 1, r, ql, qr);

        return Math.min(lVal, rVal);
    }*/

    public int maximumSum(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][2];
        dp[0][0] = arr[0];
        dp[0][1] = 0;

        int res = Math.max(0, arr[0]);
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], 0) + arr[i];
            dp[i][1] = Math.max(dp[i - 1][1] + arr[i], dp[i - 1][0]);

            res = Math.max(res, Math.max(dp[i][0], dp[i][1]));
        }

        return res;
    }
}