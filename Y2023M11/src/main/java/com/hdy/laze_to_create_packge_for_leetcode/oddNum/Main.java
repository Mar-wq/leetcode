package com.hdy.laze_to_create_packge_for_leetcode.oddNum;

import java.util.Scanner;
// 1:无需package
// 2: 类名必须Main, 不可修改

public class Main {
    private static final long MOD = 998244353;
    private static long res = 0;
    static int n, m;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();

        dfs(0, new StringBuilder());

        System.out.println(res);
        scan.close();
    }

    private static void dfs(int i, StringBuilder sb) {
        if (i == n) {
            if (check(sb)) {
                res = (res + 1) % MOD;
            }

            return;
        }
        int start = i == 0 ? 1 : (i + 1) % 2;

        for (int j = start; j <=9 ; j += 2) {
            sb.append(j);
            dfs(i + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private static boolean check(StringBuilder sb) {
        int i = 0, j = 0, sum = 0;
        while (j < n) {
            sum += sb.charAt(j) - '0';
            j++;
            if (j - i == 5) {
                if (sum > m) {
                    return false;
                }
                sum -= sb.charAt(i) - '0';
                i++;
            }
        }

        return true;
    }
}