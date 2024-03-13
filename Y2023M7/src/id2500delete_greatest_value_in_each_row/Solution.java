package id2500delete_greatest_value_in_each_row;

import java.util.Arrays;

public class Solution {
    public int deleteGreatestValue(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            Arrays.sort(grid[i]);
        }

        int res = 0;
        int m = grid[0].length;
        for (int i = 0; i < m; i++) {
            int mx = grid[0][i];
            for (int j = 0; j < n; j++) {
                mx = Math.max(mx, grid[j][i]);
            }

            res += mx;
        }

        return   res;
    }
}
