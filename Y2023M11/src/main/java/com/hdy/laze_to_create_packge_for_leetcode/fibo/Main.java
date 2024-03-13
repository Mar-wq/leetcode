package com.hdy.laze_to_create_packge_for_leetcode.fibo;

import java.util.Scanner;
// 1:无需package
// 2: 类名必须Main, 不可修改

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        int n;
        n = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        Solution solution = new Solution();
        int result = solution.getFiboMinOperations(arr);
        System.out.println(result);

        scan.close();
    }
}

class Solution {
    public int getFiboMinOperations(int[] arr) {
        return 0;
    }
}
