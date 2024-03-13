package com.hdy.laze_to_create_packge_for_leetcode.mt;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        scan.nextLine();
        String s = scan.nextLine();

        Solution solution = new Solution();
        int result = solution.getmtByKopertions(s, k);
        System.out.println(result);

        scan.close();
    }
}

class Solution {
    public int getmtByKopertions(String s, int k) {
        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'M' || s.charAt(i) == 'T') {
                cnt++;
            }
        }
        int last = n - cnt;
        int canChange = last > k ? k : last;

        return cnt + canChange;
    }
}