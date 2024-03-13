package id1222queens_that_can_attack_the_king;

import java.util.*;

public class Solution {
/*    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        Set<Integer> queenPos = new HashSet<>();

        for (int[] queen : queens) {
            int x = queen[0], y = queen[1];
            queenPos.add(x * 8 + y);
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }

                int kx = king[0] + dx, ky = king[1] + dy;
                while (kx >= 0 && kx < 8 && ky >= 0 && ky < 8) {
                    int pos = kx * 8 + ky;
                    if (queenPos.contains(pos)) {
                        List<Integer> posList = new ArrayList<>();
                        posList.add(kx);
                        posList.add(ky);
                        ans.add(posList);
                        break;
                    }
                }
                kx += dx;
                ky += dy;
            }
        }

        return ans;
    }*/

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        Map<Integer, int[]> candidates = new HashMap<>();
        int kx = king[0], ky = king[1];

        for (int[] queen : queens) {
            int qx = queen[0], qy = queen[1];
            int x = qx - kx, y = qy - ky;

            if (x == 0 || y == 0 || Math.abs(x) == Math.abs(y)) {
                int dx = sgn(x), dy = sgn(y);
                int key = dx * 10 + dy;
                if (!candidates.containsKey(key) || candidates.get(key)[2] > Math.abs(x) + Math.abs(y)) {
                    candidates.put(key, new int[]{queen[0], queen[1], Math.abs(x) + Math.abs(y)});
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, int[]> entry : candidates.entrySet()) {
            int[] value = entry.getValue();
            List<Integer> posList = new ArrayList<>();
            posList.add(value[0]);
            posList.add(value[1]);
            ans.add(posList);
        }

        return ans;
    }

    public int sgn(int x) {
        return x > 0 ? 1 : (x == 0 ? 0 : -1);
    }
}
