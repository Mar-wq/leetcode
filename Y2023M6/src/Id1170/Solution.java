package Id1170;

import java.util.Arrays;

class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = words.length;
        int[] w = new int[n];

        for (int i = 0; i < words.length; i++) {
            w[i] = f(words[i]);
        }
        Arrays.sort(w);
        System.out.println(Arrays.toString(w));

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int num = f(queries[i]);
            ans[i] = n - upBound(w, num) ;
        }

        return ans;
    }

    private int f(String s) {
        char cur = 'z';
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == cur) {
                cnt++;
            }
            else if (c < cur) {
                cnt = 1;
                cur = c;
            }
        }

        return cnt;
    }

    int upBound(int[] w, int target) {
        int l = 0, r = w.length - 1;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (w[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
}
