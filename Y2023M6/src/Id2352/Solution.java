package Id2352;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<List<Integer>, Integer> cnt = new HashMap<>();

        for (int[] row : grid) {
            List<Integer> arr = new ArrayList<>();
            for (int num : row) {
                arr.add(num);
            }

            cnt.put(arr, cnt.getOrDefault(arr, 0) + 1);
        }

        int res = 0;

        for (int j = 0; j < n; j++) {
            List<Integer> arr = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                arr.add(grid[i][j]);
            }

            res += cnt.getOrDefault(arr, 0);
        }

        return res;
    }
}