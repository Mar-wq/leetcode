package id1462course_schedule_iv;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        int[][] G = new int[numCourses][numCourses];
        for (int[] prerequisite : prerequisites) {
            int a = prerequisite[0], b = prerequisite[1];
            G[a][b] = 1;
        }

        for (int k = 0; k < numCourses; k++) {
            for (int i = 0; i < numCourses; i++) {
                for (int j = 0; j < numCourses; j++) {
                    G[i][j] = G[i][j] | (G[i][k] & G[k][j]);
                }
            }
        }

        int n = queries.length;
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            int a = query[0], b = query[1];
            if (G[a][b] == 1) {
                res.add(true);
            } else {
                res.add(false);
            }
        }

        return res;
    }


}
