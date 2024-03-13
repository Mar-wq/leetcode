package D11A_robot_trapped_in_a_ring;

public class Solution {
    public boolean isRobotBounded(String instructions) {
        int[][] direc = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int curDir = 0, x = 0, y = 0;
        for (int i = 0; i < instructions.length(); i++) {
            char c = instructions.charAt(i);
            if (c == 'G') {
                x += direc[curDir][0];
                y += direc[curDir][1];
            }
            else if (c == 'R') {
                curDir = (curDir + 1) % 4;
            } else {
                curDir = (curDir + 4 - 1) % 4;
            }
        }

        return (x == 0 && y == 0) ? true : (curDir != 0);
    }
}
