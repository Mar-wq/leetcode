package com.hdy.leetcodeAlgorithm.weekRace;

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] p = new int[n][m];
        int mod = 12345;

        // 计算行乘积
        long[] rowProduct = new long[n];
        for (int i = 0; i < n; i++) {
            rowProduct[i] = 1;
            for (int j = 0; j < m; j++) {
                rowProduct[i] = (rowProduct[i] * grid[i][j]) % mod;
            }
        }

        // 计算列乘积
        long[] colProduct = new long[m];
        for (int j = 0; j < m; j++) {
            colProduct[j] = 1;
            for (int i = 0; i < n; i++) {
                colProduct[j] = (colProduct[j] * grid[i][j]) % mod;
            }
        }

        // 计算乘积矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                p[i][j] = (int) ((rowProduct[i]  % mod * (colProduct[j]  % mod)) * modInverse(grid[i][j], m)  % mod);
            }
        }

        return p;
    }

    // 计算逆元
    // 扩展的欧几里得算法
    public static int extendedGCD(int a, int b, int[] x, int[] y) {
        if (b == 0) {
            x[0] = 1;
            y[0] = 0;
            return a;
        }

        int[] x1 = new int[1];
        int[] y1 = new int[1];
        int gcd = extendedGCD(b, a % b, x1, y1);

        x[0] = y1[0];
        y[0] = x1[0] - (a / b) * y1[0];

        return gcd;
    }

    // 计算逆元
    public static int modInverse(int a, int m) {
        int[] x = new int[1];
        int[] y = new int[1];
        int gcd = extendedGCD(a, m, x, y);

        if (gcd != 1) {
            throw new ArithmeticException("逆元不存在");
        }

        int result = x[0] % m;
        if (result < 0) {
            result += m;
        }

        return result;
    }

}
