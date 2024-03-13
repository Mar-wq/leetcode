package Id1177;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
/*        int n = s.length();
        int[][] sum = new int[n + 1][26];
        for (int i = 0; i < s.length(); i++) {
            sum[i + 1] = sum[i].clone();
            sum[i + 1][s.charAt(i) - 'a']++;
        }
        List<Boolean> ans = new ArrayList<>();
        for (int[] query : queries) {
            int left = query[0], right = query[1], k = query[2];
            int m = 0;
            for (int i = 0; i < 26; i++) {
                m += ((sum[right + 1][i] - sum[left][i]) % 2);
            }
            if (m / 2 <= k) {
                ans.add(true);
            } else {
                ans.add(false);
            }
        }

        return ans;*/


        int n = s.length();
        int[] sum = new int[n + 1];
        for (int i = 0; i < s.length(); i++) {
            int bit = 1 << (s.charAt(i) - 'a');
            sum[i + 1] = sum[i] ^ bit;
        }

        List<Boolean> ans = new ArrayList<>(queries.length);
        for (int[] query : queries) {
            int left = query[0], right = query[1], k = query[2], m = 0;
            m = Integer.bitCount(sum[right + 1] ^ sum[left]);
            ans.add(m / 2 <= k);
        }

        return ans;
    }
}