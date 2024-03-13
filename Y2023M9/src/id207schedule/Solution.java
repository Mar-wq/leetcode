package id207schedule;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] prerequisite : prerequisites) {
            int a = prerequisite[0], b = prerequisite[1];
            graph[b].add(a);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] onpath = new boolean[numCourses];

        for (int node = 0; node < numCourses; node++) {
            if (!visited[node]) {
                visited[node] = true;
                if (dfs(node, graph, visited, onpath)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfs(int id,List<Integer>[] g, boolean[] visited, boolean[] onpath ) {
        onpath[id] = true;
        boolean result = false;
        for (int pre : g[id]) {
            if (onpath[pre]) {
                return true;
            }
            if (visited[pre]) {
                continue;
            }
            visited[pre] = true;
            result |= dfs(pre, g, visited, onpath);
            if (result) {
                return result;
            }
        }
        onpath[id] = false;

        return result;
    }
}
