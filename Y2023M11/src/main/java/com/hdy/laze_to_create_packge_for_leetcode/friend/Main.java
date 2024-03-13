package com.hdy.laze_to_create_packge_for_leetcode.friend;

import java.util.ArrayDeque;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n, q, m;
        n = scan.nextInt();
        m = scan.nextInt();
        q = scan.nextInt();

        int[][] graph = new int[n][n];
        for (int i = 0; i < m; i++) {
            int u = scan.nextInt() - 1;
            int v = scan.nextInt() - 1;
            graph[u][v] = 1;
            graph[v][u] = 1;
        }

        for (int i = 0; i < q; i++) {
            int op = scan.nextInt();
            int u = scan.nextInt() - 1;
            int v = scan.nextInt() - 1;

            if (op == 1) {
                graph[u][v] = 0;
                graph[v][u] = 0;
            } else {
                if(canFriend(graph, u, v)) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            }
        }
    }

    private static boolean canFriend(int[][] graph, int u, int v) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int n  = graph.length;
        boolean[] visited = new boolean[n];
        q.add(u);
        visited[u] = true;

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int next  = q.peekFirst();
                q.pollFirst();
                if (next == v) {
                    return true;
                }

                for (int j = 0; j < n; j++) {
                    if (graph[next][j] == 1 && !visited[j]) {
                        visited[j] = true;
                        q.add(j);
                    }
                }
            }
        }

        return false;
    }
}
