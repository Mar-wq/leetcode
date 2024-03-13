package id771jewels_and_stones;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> s = new HashSet<>();
        int m = jewels.length(), n = stones.length();

        for (int i = 0; i < m; i++) {
            s.add(jewels.charAt(i));
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (s.contains(stones.charAt(i))) {
                res++;
            }
        }

        return res;
    }
}
