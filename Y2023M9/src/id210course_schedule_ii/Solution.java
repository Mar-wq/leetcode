package id210course_schedule_ii;

import java.util.*;

public class Solution {

    //  基于队列的实现方式
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] prerequisite : prerequisites) {
            int a = prerequisite[0], b = prerequisite[1];
            graph[b].add(a);
            indegree[a] += 1;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        List<Integer> temp = new ArrayList<>();
        while (!q.isEmpty()) {
            Integer course = q.poll();
            temp.add(course);
            for (Integer neibor : graph[course]) {
                if (--indegree[neibor] == 0) {
                    q.offer(neibor);
                }
            }
        }

        if (temp.size() != numCourses) {
            return new int[0];
        }

        // 这里流的操作一点都不熟练  后续得加强了
        int[] res = temp.stream().mapToInt(Integer::intValue).toArray();


        return res;
    }
}
