package Id2517maxHoney;

import java.util.Arrays;

class Solution {
    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int n = price.length;

        int left = -1, right = price[n - 1] - price[0];

        while (left < right) {
            int mid = (left + right + 1) / 2;

            if (check(price, k, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    boolean check(int[] price, int k, int dis) {
        int num = 1;
        int pre = price[0];

        for (int i = 1; i < price.length; i++) {
            if (price[i] - pre >= dis) {
                num++;
            }

            pre = price[i];
        }

        return num >= k;
    }
}