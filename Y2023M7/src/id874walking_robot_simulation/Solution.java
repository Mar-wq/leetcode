package id874walking_robot_simulation;

import java.util.HashSet;
import java.util.Set;

class Solution {
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int robotSim(int[] commands, int[][] obstacles) {
        Set<Integer> set = new HashSet<>();
        for (int[] obstacle : obstacles) {
            set.add(obstacle[0] * 60001 + obstacle[1]);
        }

        System.out.println(set.contains(new int[]{2, 4}));

        int res = 0;
        int d = 1, x = 0, y = 0;
        for (int c : commands) {
            if (c < 0) {
                d += c == -1 ? 1 : -1;
                d = (d + 4) % 4;
            } else {
                for (int i = 0; i < c; i++) {
                    if (set.contains((x + dirs[d][0]) * 60001 + y + dirs[d][1])) {
                        break;
                    }

                    x += dirs[d][0];
                    y += dirs[d][1];
                    res = Math.max(res, x * x + y * y);
                }

            }
        }


        return res;
    }
}
