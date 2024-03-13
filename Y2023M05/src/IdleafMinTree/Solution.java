package IdleafMinTree;

class Solution {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int[] a =  dfs(arr, 0, n - 1);

        return a[0];
    }

    int[] dfs(int[] arr, int l, int r) {
        if (l == r) {
            return new int[]{0, arr[l]};
        }

        int[] res = new int[2];
        res[0] = Integer.MAX_VALUE;
        for (int k = l; k < r; k++) {
            int[] arrL = dfs(arr, l, k);
            int[] arrR = dfs(arr, k + 1, r);

            if (arrL[0] + arrR[0] + arrR[1] * arrL[1] < res[0]) {
                res[0] = arrL[0] + arrR[0] + arrR[1] * arrL[1];
                res[1] = Math.max(arrL[1], arrR[1]);
            }
        }

        return res;
    }
}