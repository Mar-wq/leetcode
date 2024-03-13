package id2127_maximum_employees_to_be_invited_to_a_meeting;

import java.util.*;

class Solution {
    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] deg = new int[n];

        for (int f : favorite) {
            deg[f]++;
        }

        List<Integer>[] rg = new List[n]; // 反图
        Arrays.setAll(rg, i -> new ArrayList<>());
        Deque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (deg[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = favorite[x];
            rg[y].add(x);
            if (--deg[y] == 0) {
                q.add(y);
            }
        }

        int maxRingSize = 0, sumChainSize = 0;

        for (int i = 0; i < n; i++) {
            if (deg[i] == 0) {
                continue;
            }

            deg[i] = 0;
            int ringSize = 1; //环基长度
            for (int x = favorite[i]; x != i ; x = favorite[x]) {
                deg[x] = 0; //将基环上的点的入读标记为0，避免重复访问
                ringSize++;
            }

            if (ringSize == 2) {
                sumChainSize += rdfs(i, rg) + rdfs(favorite[i], rg);
            } else {
                maxRingSize = Math.max(maxRingSize, ringSize);
            }
        }


        return Math.max(maxRingSize, sumChainSize);
    }

    private int rdfs(int x, List<Integer>[] rg) {

        int res = 1;
        for (Integer n : rg[x]) {
            res = Math.max(res, rdfs(n, rg) + 1) ;
        }

        return res;
    }

}
