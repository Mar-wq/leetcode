package com.hdy.leetcodeAlgorithm.id2512RewardTopKStudents;

import java.util.*;

class Solution {
    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        Set<String> postive_set = new HashSet<>();
        Set<String> negative_set = new HashSet<>();

        for (String s : positive_feedback) {
            postive_set.add(s);
        }

        for (String s : negative_feedback) {
            negative_set.add(s);
        }

        int n = report.length;
        int[] records = new int[n];
        for (int i = 0; i < report.length; i++) {
            String[] words = report[i].split(" ");
            for (String word : words) {
                if (postive_set.contains(word)) {
                    records[i] += 3;
                }

                if (negative_set.contains(word)) {
                    records[i] -= 1;
                }
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }

            return b[0] - a[0];
        });

        for (int i = 0; i < n; i++) {
            pq.add(new int[]{records[i], student_id[i]});
        }

        List<Integer> res = new ArrayList<>();

        while (k > 0 && !pq.isEmpty()) {
            k--;
            int[] stu = pq.poll();
            res.add(stu[1]);
        }

        return res;

    }
}
