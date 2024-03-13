package com.hdy.leetcode.weekly_contest_370;

/*
class Solution {
    public int findChampion(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            boolean isChampion = true;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                if (grid[i][j] != 1) {
                    isChampion = false;
                    break;
                }
            }

            if (isChampion) {
                return i;
            }
        }

        return -1;
    }
}*/


/*class Solution {
    public int findChampion(int n, int[][] edges) {
        int[] indegree = new int[n];

        for (int[] edge : edges) {
            int b = edge[1];
            indegree[b]++;
        }

        int cnt = 0, champion = -1;
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                cnt++;
                champion = i;
            }
        }

        return cnt == 1 ? champion : -1;
    }
}*/


import java.util.ArrayList;
import java.util.List;

class Solution1 {
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        int n = values.length;
        List<Integer>[] G = new List[n];
        for (int i = 0; i < n; i++) {
            G[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            G[a].add(b);
            G[b].add(a);
        }

        boolean[] visited = new boolean[n];
        return dfs(0, G, values, visited, false, 0);
    }

    private long dfs(int node, List<Integer>[] g, int[] values, boolean[] visited, boolean flag, int sum) {
        visited[node] = true;
        if (g[node].size() == 1 && visited[g[node].get(0)] && flag == false) {
            return sum;
        }


        return 0;
    }
}
class Solution {
    public int findTheLongestBalancedSubstring(String s) {


        int n = s.length();
        int[] len01 = new int[2];

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                len01[1]++;
                res = Math.max(res, 2 * Math.min(len01[0], len01[1]));
            } else {
                if (i != 0 && s.charAt(i - 1) == '1') {
                    len01[1] = 0;
                }
                len01[0]++;
            }
        }


        return res;
    }
}
