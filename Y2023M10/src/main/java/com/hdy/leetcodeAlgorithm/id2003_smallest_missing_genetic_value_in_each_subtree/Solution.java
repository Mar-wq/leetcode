package com.hdy.leetcodeAlgorithm.id2003_smallest_missing_genetic_value_in_each_subtree;

import java.util.*;

class Solution {
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = parents.length;
        List<Integer>[] children = new List[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parents[i]].add(i);
        }

        int[] res = new int[n];
        Arrays.fill(res, 1);

        Set<Integer>[] genSet = new Set[n];
        for (int i = 0; i < n; i++) {
            genSet[i] = new HashSet<>();
        }

        dfs(0, res, nums, children, genSet);

        return res;
    }

    private int dfs(int node, int[] res, int[] nums, List<Integer>[] children, Set<Integer>[] genSet) {
        genSet[node].add(nums[node]);

        for (int child : children[node]) {
            res[node] = Math.max(res[node], dfs(child, res, nums, children, genSet));
            // 这里是启发式搜索，小的往大的添加  可以有效的节约时间   因为子节点的那个已经遍历过了 俨然没有用了  那我们就可以借用子节点的基因集合
            if (genSet[node].size() < genSet[child].size()) {
                Set<Integer> temp = genSet[node];
                genSet[node] = genSet[child];
                genSet[child] = temp;
            }
            genSet[node].addAll(genSet[child]);
        }

        while (genSet[node].contains(res[node])) {
            res[node]++;
        }

        return res[node];
    }
}
