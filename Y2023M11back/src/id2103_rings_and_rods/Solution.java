package id2103_rings_and_rods;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int countPoints(String rings) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('R', 0);
        map.put('G', 1);
        map.put('B', 2);
        int[][] pillars = new int[10][3];

        for (int i = 0; i < rings.length(); i += 2) {
            int pillar = rings.charAt(i + 1) - '0';
            Character color = rings.charAt(i);
            pillars[pillar][map.get(color)]++;
        }

        int res = 0;
        for (int i = 0; i < 10; i++) {
            if (pillars[i][0] > 0 && pillars[i][1] > 0 && pillars[i][2] > 0) {
                res++;
            }
        }

        return res;
    }
}
