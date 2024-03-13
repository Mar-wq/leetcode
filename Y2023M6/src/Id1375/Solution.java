package Id1375;

class Solution {
    public int numTimesAllBlue(int[] flips) {
/*        int num = 0, ans = 0, n = flips.length;
        int std = 1 << n;
        System.out.println(std);
        for (int i = 0; i < n; i++) {
            int p = flips[i];
            num = num ^ (1 << (n - p));
            if (num == std - (1 << (n - (i + 1)))) {
                ans++;
            }
        }


        return ans;*/

        int n = flips.length, ans = 0;

        int maxpos = 0;
        for (int i = 0; i < n; i++) {
            if (flips[i] > maxpos) {
                maxpos = flips[i];
            }

            if (maxpos == i + 1) {
                ans++;
            }
        }

        return ans;
    }
}