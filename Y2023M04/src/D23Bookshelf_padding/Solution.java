package D23Bookshelf_padding;

import java.util.Arrays;

public class Solution {
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1000000);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            int maxHeight = 0, curWidth = 0;

            for (int j = i - 1; j >= 0; j--) {
                curWidth += books[j][0];
                if (curWidth > shelfWidth) {
                    break;
                }

                maxHeight = Math.max(maxHeight, books[j][1]);
                dp[i]  = Math.min(dp[i], dp[j] + maxHeight);
            }
        }

        return dp[n];
    }
}
