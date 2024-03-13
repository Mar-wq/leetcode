package id135candy;

public class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] fromRightIncr = new int[n];
        fromRightIncr[n - 1] = 1;
        for (int i = n - 2; i >= 0 ; i--) {
            if (ratings[i] > ratings[i + 1]) {
                fromRightIncr[i] = fromRightIncr[i + 1] + 1;
            } else {
                fromRightIncr[i] = 1;
            }
        }

        // 计算每个孩子最少分配多少个糖果
        int[] cnt = new int[n];
        cnt[0] = Math.max(1, fromRightIncr[0]);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                cnt[i] = Math.max(cnt[i - 1] + 1, fromRightIncr[i]);
            } else {
                cnt[i] = Math.max(1, fromRightIncr[i]);
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res += cnt[i];
        }

        return res;
    }
}
