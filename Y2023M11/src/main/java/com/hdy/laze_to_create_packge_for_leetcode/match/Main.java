package com.hdy.laze_to_create_packge_for_leetcode.match;

import java.util.Scanner;
// 1:无需package
// 2: 类名必须Main, 不可修改

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        int n = 0;
        n = scan.nextInt();

        Solution solution = new Solution();
        String maxNum = solution.getMaxNum(n);
        System.out.println(maxNum);

        scan.close();
    }

}

class Solution {
    private int[] numMatchs = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
    private String res = "0";

    public String getMaxNum(int n) {
        dfs(n, new StringBuilder(), new int[10]);

        return res;
    }

    private void dfs(int n, StringBuilder num, int[] used) {
        if (n < 2 || checkUsed(used)) {
            String temp = num.toString();
            if (compare(res, temp)) {
                System.out.println(temp);
                res = temp;
            }
            return;
        }

        for (int i = 0; i < numMatchs.length; i++) {
            if (numMatchs[i] <= n && used[i] < 10) {
                used[i]++;
                num.append((char)(i + '0'));
                dfs(n - numMatchs[i], num, used);
                num.deleteCharAt(num.length() - 1);
                used[i]--;
            }
        }
    }

    private boolean compare(String res, String temp) {
        if (res.length() > temp.length()) {
            return false;
        } else if (res.length() < temp.length()) {
            return true;
        } else {
            return temp.compareTo(res) > 0;
        }

    }

    private boolean checkUsed(int[] used) {
        for (int x : used) {
            if (x < 10) {
                return false;
            }
        }
        System.out.println("9999999999777777777755555555554444444444333333333322222222221111111111");
        return true;
    }
}