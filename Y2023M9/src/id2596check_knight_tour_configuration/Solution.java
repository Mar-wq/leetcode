package id2596check_knight_tour_configuration;

public class Solution {
    private int[][] dirs = {{-2, -1}, {-2, 1}, {1, -2}, {-1, -2}, {2, -1}, {2, 1}, {-1, 2}, {1, 2}};

    public boolean checkValidGrid(int[][] grid) {
        int n = grid.length;
        int target = n * (n - 1);

        return dfs(grid, 0,0, target);
    }

    private boolean dfs(int[][] grid, int x, int y, int target) {
        if (grid[x][y] == target) {
            return true;
        }

        boolean result = false;
        for (int[] dir : dirs) {
            int x1 = x + dir[0], y1 = y + dir[1];
            if (x1 >= 0 && x1 < grid.length && y1 >= 0 && y1 < grid.length && grid[x1][y1] == grid[x][y] + 1) {
                result = dfs(grid, x1, y1, target);
            }

        }

        return result;
    }
}
