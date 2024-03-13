package ID1010Sings;

import java.util.HashMap;
import java.util.Map;

//class Solution {
//    public int numPairsDivisibleBy60(int[] time) {
//        Map<Integer, Integer> map = new HashMap<>();
//
//
//        int res = 0;
//        for (int t : time) {
//            int d = t % 60;
//            int half = (60 - d) % 60;
//
//
//            res += map.getOrDefault(half, 0);
//            map.put(d, map.getOrDefault(d, 0) + 1);
//        }
//
//        return res;
//    }
//}