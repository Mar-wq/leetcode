package com.hdy.laze_to_create_packge_for_leetcode.queryArray;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = scan.nextInt();
        int q = scan.nextInt();
        int[] arr = new int[n];
        int cnt = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
            if (arr[i] == 0) {
                cnt++;
            }
            sum += arr[i];
        }

        int[][] querys = new int[q][2];
        for (int i = 0; i < q; i++) {
                int l = scan.nextInt();
                int r = scan.nextInt();
                querys[i][0] = sum + (cnt * l);
                querys[i][1] = sum + (cnt * r);
                System.out.print(querys[i][0]);
                System.out.print(" ");
                System.out.print(querys[i][1]);
                System.out.println();
        }

        scan.close();
    }
}

class Solution {
    public int[][] queryArray(int[] arr, int[][] querys) {
        int n = arr.length;
        int q = querys.length;
        int cnt = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                cnt++;
            }
            
            sum += arr[i];
        }
        
        
        int[][] res = new int[q][2];
        
        for (int i = 0; i < q; i++) {
            res[i][0] = sum + (cnt * querys[i][0]);
            res[i][1] = sum + (cnt * querys[i][1]);
        }
        
        return res;
    }
}