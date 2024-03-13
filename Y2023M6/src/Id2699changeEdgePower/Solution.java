package Id2699changeEdgePower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        List<Integer>[] graph = new List[n];
        Map<int[], int[]> m = new HashMap();
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0], b = edges[i][1];
            int w = edges[i][2];
            graph[a].add(b);
            graph[b].add(a);
            m.put(new int[]{a,b}, new int[]{w, i});
            m.put(new int[]{b,a}, new int[]{w, i});
        }
        boolean[] visited = new boolean[n];

        return edges;
    }

    public static void main(String[] args) {
        Integer a = new Integer("10");
        Solution b = new Solution();
        b.test1(a);
        System.out.println(a);
    }

    private void test1(Integer a) {
        // 这里的a只是一个栈区变量，-2会是一个新的对象，所以不会改变main中的a。ok？？
        a = -2;
        System.out.println(a);
    }
}