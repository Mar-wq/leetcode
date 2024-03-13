package com.hdy.laze_to_create_packge_for_leetcode.balance;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        scan.nextLine();
        char[][] arr = new char[n][];
        for (int i = 0; i < n; i++) {
            String s = scan.nextLine();
            arr[i] = s.toCharArray();
        }
        Solution solution = new Solution();
        for (int i = 1; i <= n; i++) {
            int cnt = solution.f(arr, i);
            System.out.println(cnt);
        }

        scan.close();;
    }
}


class Solution {
    public int f(char[][] arr, int k) {
        int cnt = 0;
        int n= arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i + k <= n && j + k <= n) {
                    if (check(arr, i, j, k)) {
                        cnt++;
                    }
                }
            }
        }

        return cnt;
    }

    private boolean check(char[][] arr, int i, int j, int k) {
        int zero = 0, one = 0;
        for (int m = 0; m < k; m++) {
            for (int n = 0; n < k; n++) {
                if (arr[i + m][j + n] == '1') {
                    one++;
                } else {
                    zero++;
                }
            }
        }

        return zero == one;
    }
}