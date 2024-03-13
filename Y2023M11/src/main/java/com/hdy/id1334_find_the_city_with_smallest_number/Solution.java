package com.hdy.id1334_find_the_city_with_smallest_number;

import java.util.Arrays;

class Solution1 {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] mp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(mp[i], Integer.MAX_VALUE / 2);
        }

        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], weight = edge[2];
            mp[from][to] = mp[to][from] = weight;
        }

        for (int k = 0; k < n; k++) {
            mp[k][k] = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    mp[i][j] = Math.max(mp[i][i], mp[i][k] + mp[k][j]);
                }
            }
        }

        int minCnt = Integer.MAX_VALUE;
        int res = -1;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (mp[i][j] <= distanceThreshold) {
                    cnt++;
                }
            }

            if (cnt < minCnt) {
                res = i;
            }
        }

        return res;
    }
}


class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[] ans = {Integer.MAX_VALUE / 2, -1};
        int[][] dis = new int[n][n];
        boolean[][] vis = new boolean[n][n];
        int[][] g = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE / 2);
            Arrays.fill(g[i], Integer.MAX_VALUE / 2);
        }

        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], weight = edge[2];
            g[from][to] = g[to][from] = weight;
        }

        for (int i = 0; i < n; i++) {
            dis[i][i] = 0;
            for (int j = 0; j < n; j++) {
                int t = -1;
                for (int k = 0; k < n; k++) {
                    if (!vis[i][k] && (t == -1 || dis[i][k] < dis[i][t])) {
                        t = k;
                    }
                }

                for (int k = 0; k < n; k++) {
                    dis[i][k] = Math.min(dis[i][k], dis[i][t] + g[t][k]);
                }

                vis[i][t] = true;
            }
        }

        for (int i = 0; i < n; ++i) {
            int cnt = 0;
            for (int j = 0; j < n; ++j) {
                if (dis[i][j] <= distanceThreshold) {
                    cnt++;
                }
            }
            if (cnt <= ans[0]) {
                ans[0] = cnt;
                ans[1] = i;
            }
        }
        return ans[1];
    }
}