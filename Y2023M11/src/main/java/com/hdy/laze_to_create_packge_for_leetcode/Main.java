package com.hdy.laze_to_create_packge_for_leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private int n;
    private int[][] board;
    private static char[][] res;
    private static char[][] array = {
            {'.' , '.', '.', 'O', '.'},
            {'.', '2', 'O', '4', 'O'},
            {'.', 'O', '4', 'O', '.'},
            {'.', '2', 'O', 'X', '.'},
            {'O', '.', '.', '.', '.'}
    };

    private static String endResult;


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        int n = scan.nextInt();
        scan.nextLine(); // 跳过nextInt()后的换行符
        System.out.println(n);
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            String s = scan.nextLine();
            char[] chs = s.toCharArray();
            System.arraycopy(chs, 0, board[i], 0, chs.length);
        }


        System.out.println(Arrays.deepToString(board));
        endResult = Arrays.deepToString(array);

        char[][] result = solve(board);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(res[i][j]);
            }
            System.out.println();
        }


        scan.close();
    }

    private static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static List<Integer> checkPoints;

    private static char[][] solve(char[][] board) {
        int n = board.length;
        checkPoints = new ArrayList<>();
        List<Integer> needChangePoints = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') {
                    checkPoints.add(i * n + j);
                } else {
                    needChangePoints.add(i * n + j);
                }
            }
        }
        int target = needChangePoints.size();
        boolean[][] visited = new boolean[n][n];

        redfs(needChangePoints, 0, target, board);

        return res;
    }

    private static void redfs(List<Integer> needChangePoints, int i, int target, char[][] board) {
        if (i == target) {
            if (endResult.equals(Arrays.deepToString(board))) {
                System.out.println("122222222222222222");
            }

            if (checkBoard(board)) {
                char[][] copy = new char[board.length][];

                for (int k = 0; k < board.length; k++) {
                    copy[k] = Arrays.copyOf(board[k], board[k].length);
                }

                res = copy;
            }

            return;
        }

        int x = needChangePoints.get(i) / board.length, y = needChangePoints.get(i) % board.length;
/*        if (legal(board, x, y)) {
            board[x][y] = 'O';
            redfs(needChangePoints, i + 1, target, board);
            board[x][y] = '.';
        }*/

        board[x][y] = 'O';
        redfs(needChangePoints, i + 1, target, board);
        board[x][y] = '.';

        redfs(needChangePoints, i + 1, target, board);

    }

    private static void dfs(char[][] board, int i, int j, int cnt, int target, boolean[][] visited) {
        int n = board.length;

        if (allVisited(visited)) {
            if (checkBoard(board)) {
                char[][] copy = new char[board.length][];

                for (int k = 0; k < board.length; k++) {
                    copy[k] = Arrays.copyOf(board[k], board[k].length);
                }

                res = copy;
            }

            return;
        }

        if (i < 0 || i >= n || j < 0 || j >= n) {
            return ;
        }

        if (visited[i][j] == true) {
            return;
        }

        visited[i][j] = true;

        if (board[i][j] == '.') {
            if (legal(board, i, j)) {
                board[i][j] = 'O';
                for (int[] dir : dirs) {
                    dfs(board, i + dir[0], j + dir[1], cnt + 1, target, visited);
                }
                board[i][j] = '.';
            }
        } else {
            for (int[] dir : dirs) {
                dfs(board, i + dir[0], j + dir[1], cnt + 1, target, visited);
            }
        }

        visited[i][j] = false;

    }

    private static boolean allVisited(boolean[][] visited) {
        int n = visited.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == false) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkBoard(char[][] board) {
        int n = board.length;
        for (Integer checkPoint : checkPoints) {
            int x = checkPoint / n, y = checkPoint % n;
            int cnt = 0;
            for (int[] dir : dirs) {
                int i = x + dir[0], j = y + dir[1];
                if (i < 0 || i >= n || j < 0 || j >= n) {
                    continue;
                }

                if (board[i][j] == 'O') {
                    cnt++;
                }
            }

            if (board[x][y] != 'X' && (board[x][y] - '0') != cnt) {
                return false;
            }
        }

        /*for (int i = 0; i < n; i++) {
            int j = 0, cnt1 = 0;
            while (j < n) {
                int k = j;
                while (k < n && board[i][k] =='.') {
                    k++;
                }
                if (k - j != 0) {
                    cnt1++;
                }
                j = k + 1;
            }
            int cnt2 = 0;
            for (int l = 0; l < n; l++) {
                if (board[i][l] == 'O') {
                    cnt1++;
                }
            }

            if (cnt1 != cnt2) {
                return false;
            }
        }

        for (int j = 0; j < n; j++) {
            int i = 0, cnt1 = 0;
            while (i < n) {
                int k = i;
                while (i < n && board[k][j] == '.') {
                    k++;
                }

                if (k - i != 0) {
                    cnt1++;
                }

                i = k + 1;
            }

            int cnt2 = 0;
            for (int l = 0; l < n; l++) {
                if (board[l][j] == 'O') {
                    cnt2++;
                }
            }
            if (cnt1 != cnt2) {
                return false;
            }
        }*/


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    boolean flag = false;
                    int cnt1 = 0;
                    for (int k = i - 1; k >= 0 ; k--) {
                        if (board[k][j] == 'O') {
                            cnt1++;
                            flag = true;
                        }

                        if (Character.isDigit(board[k][j]) || board[k][j] == 'X' ) {
                            break;
                        }
                    }

                    for (int k = i + 1; k < n; k++) {
                        if (board[k][j] == 'O') {
                            cnt1++;
                            flag = true;
                        }

                        if (Character.isDigit(board[k][j]) || board[k][j] == 'X') {
                            break;
                        }
                    }

                    if (cnt1 > 1) {
                        return false;
                    }


                    cnt1 = 0;
                    for (int k = j - 1; k >= 0; k--) {
                        if (board[i][k] == 'O') {
                            cnt1++;
                            flag = true;
                        }
                        if (Character.isDigit(board[k][j]) || board[k][j] == 'X') {
                            break;
                        }
                    }

                    for (int k = j + 1; k < n; k++) {
                        if (board[i][k] == 'O') {
                            cnt1++;
                            flag = true;
                        }

                        if (Character.isDigit(board[k][j]) || board[k][j] == 'X') {
                            break;
                        }
                    }

                    if (cnt1 > 1) {
                        return false;
                    }

                    if (!flag) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static boolean legal(char[][] board, int i, int j) {
        int n = board.length;
        int cnt = 0;
        for (int k = 0; k < n; k++) {
            if (board[k][j] == 'O') {
                cnt++;
            }
        }

        if (cnt >= 1) {
            return false;
        }

        cnt = 0;

        for (int k = 0; k < n; k++) {
            if (board[i][k] == 'O') {
                cnt++;
            }
        }

        if (cnt >= 1) {
            return false;
        }

        return true;
    }
}
