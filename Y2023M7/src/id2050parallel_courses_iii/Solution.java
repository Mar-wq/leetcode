package id2050parallel_courses_iii;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {
        Map<Integer, Integer> memo = new HashMap<>();
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] relation : relations) {
            graph[relation[1]].add(relation[0]);
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            res = Math.max(res, dfs(i, graph, time, memo));
        }

        return res;
    }

    private int dfs(int i, ArrayList<Integer>[] graph, int[] time, Map<Integer, Integer> memo) {
        if (memo.containsKey(i)) {
            return memo.get(i);
        }
        int res = 0;
        for (Integer neib : graph[i]) {
            res = Math.max(res, dfs(neib, graph, time, memo));
        }

        res += time[i - 1];

        memo.put(i, res);

        return res;
    }
}
