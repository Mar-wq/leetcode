package com.hdy.laze_to_create_packge_for_leetcode.interv;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int k = scan.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }

        Solution solution = new Solution();
        int res = solution.f(arr, k);

        System.out.println(res);

        scan.close();
    }
}

class Solution {

    public int f(int[] arr, int k) {
        Long mul = 1L;
        int n = arr.length;
        for (int i : arr) {
            mul = mul * i;
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                long temp = 1L;
                for (int l = i; l <= j; l++) {
                    temp = temp * arr[l];
                }
                long reslut = mul / temp;
                if (check(reslut, k)) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private boolean check(long reslut, int k) {
        int cnt = 0;
        while (reslut % 10 == 0) {
            cnt++;
            reslut /= 10;
        }

        return cnt == k;
    }
}
