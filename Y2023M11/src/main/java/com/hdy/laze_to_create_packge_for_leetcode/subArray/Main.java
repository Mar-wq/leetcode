package com.hdy.laze_to_create_packge_for_leetcode.subArray;

import java.util.Scanner;
// 1:无需package
// 2: 类名必须Main, 不可修改

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        int m, n, a, b;
        n = scan.nextInt();
        m = scan.nextInt();
        a = scan.nextInt();
        b = scan.nextInt();

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scan.nextInt();
            }
        }

        Solution solution = new Solution();
        Long res = solution.getResultFromArray(arr, a, b);
        System.out.println(res);

        scan.close();
    }
}


class Solution {
    private static final int MOD = 998244353;
    public long getResultFromArray(int[][] arr, int a, int b) {
        int n = arr.length, m = arr[0].length;
        long res = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i + a <= n) && (j + b <= m)) {
                    res = (res + getPart(arr, i, j, a, b) % MOD) % MOD;
                }
            }
        }

        return res;
    }

    private long getPart(int[][] arr, int row, int col, int a, int b) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i = row; i < row + a; i++) {
            for (int j = col; j < col + b; j++) {
                min = Math.min(min, arr[i][j]);
                max = Math.max(max, arr[i][j]);
            }
        }

        int res = (max % MOD) * (min % MOD);

        return res;
    }
}
