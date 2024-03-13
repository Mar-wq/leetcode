package com.hdy.laze_to_create_packge_for_leetcode.cheess;

import java.util.Scanner;
// 1:无需package
// 2: 类名必须Main, 不可修改

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        int n, m;
        n = scan.nextInt();
        m = scan.nextInt();
        int[][] opertions = new int[m][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 4; j++) {
                opertions[i][j] = scan.nextInt();
            }
        }
        int[][] board = new int[n][n];

        Solution solution = new Solution();
        board = solution.chess(board, opertions);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }

        scan.close();
    }
}


class Solution {
    public int[][] chess(int[][] board, int[][] operations) {
        int n = board.length;
        for (int[] operation : operations) {
            int x1 = operation[0] - 1, x2 = operation[2] - 1, y1 = operation[1] - 1, y2 = operation[3] - 1;

            for (int i = x1; i <= x2; i++) {
                board[i][y1] += 1;
                if (y2 < n - 1) {
                    board[i][y2 + 1] += 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int temp = 0;
            for (int j = 0; j < n; j++) {
                temp += board[i][j];
                board[i][j] = temp % 2;
            }
        }

        return board;
    }
}
