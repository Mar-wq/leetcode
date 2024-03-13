package com.hdy.test;

import java.util.Arrays;
import java.util.List;

public class TestInherit {
    public static void main(String[] args) {
        Fu a = new Zi();
        a.introduce();
    }
}

class Fu {
    private String name = "fu";


    public void introduce() {
        System.out.println("my name is " + name);
    }
}

class Zi extends Fu {

}



class Solution1 {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] f = new boolean[n + 1];

        f[0] = true;

        for (int i = 1; i <= n; i++) {
            for (String word : wordDict) {
                if (i < word.length()) {
                    continue;
                }
                if (word.equals(s.substring(i - word.length(), i))) {
                    f[i] |= f[i - word.length()];
                }
            }
        }

        return f[n];
    }
}

class Solution3 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] f = new int[n + 1][3][2];
        f[0][1][1] = Integer.MIN_VALUE;
        f[0][2][1] = Integer.MIN_VALUE;
        f[0][0][1] = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <=2 ; j++) {
                for (int k = 0; k < 2; k++) {
                }
            }
        }


        return 0;
    }
}


class Solution4 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        int sum = 0;
        for (int i = m - 1; i >= 0 ; i--) {
            sum += grid[i][n - 1];
            dp[i][n - 1] = sum;
        }

        sum = 0;
        for (int i = n - 1; i >= 0 ; i--) {
            sum += grid[m - 1][i];
            dp[m - 1][i] = sum;
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
            }
        }

        return dp[0][0];
    }
}


class Solution6 {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                int min = Math.min(dp[i - 1][j - 1], dp[i][j - 1]) + 1;
                min = Math.min(min, dp[i - 1][j] + 1);
                dp[i][j] = Math.min(dp[i][j], min);
            }
        }

        return dp[m][n];
    }
}


class Solution7 {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int resI = 0, resJ = 0;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[i][j] = true;
                    continue;
                }

                if (i + 1 == j) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                    if (j - i > resJ - resI) {
                        resI = i;
                        resJ = j;
                    }

                    continue;
                }

                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                if (dp[i][j]) {
                    if (j - i > resJ - resI) {
                        resI = i;
                        resJ = j;
                    }
                }
            }
        }

        return s.substring(resI, resJ + 1);
    }
}


class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;

        int[] dp = new int[n];
        dp[n - 1] = obstacleGrid[m - 1][n - 1] == 0 ? 1 : 0;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }

                if (j < n - 1 && obstacleGrid[i][j + 1] == 0) {
                    dp[j] += dp[j + 1];
                }
            }
        }

        return dp[0];
    }
}



class Test {
    public static void main(String[] args) {
        fun1();
    }

    static void fun1() {
        fun2();
    }
    static void fun2() {
        fun3();
    }
    static void fun3() {
        fun4();
    }
    static void fun4() {
        System.out.println("aaa");
    }
}


























































