package Id1016;

import java.util.HashSet;

class Solution {
    /*public boolean queryString(String s, int n) {
        for (int i = 1; i <= n; i++) {
            if (!s.contains(Integer.toBinaryString(i)))
                return false;
        }
        return true;
    }*/
    public boolean queryString(String s, int n) {
        var seen = new HashSet<>();
        var sArr = s.toCharArray();

        for (int i = 0, m = s.length(); i < m; i++) {
            int x = sArr[i] - '0';
            if (x == 0) continue;

            for (int j = i + 1; x <= n; j++) {
                seen.add(x);
                if (j == m) {
                    break;
                }
                x = (x << 1) | (sArr[j] - '0');
            }
        }

        return seen.size() == n;
    }

}
