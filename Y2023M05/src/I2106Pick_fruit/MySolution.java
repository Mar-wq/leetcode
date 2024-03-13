package I2106Pick_fruit;

public class MySolution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int[] sum = new int[n + 1];
        int[] indices = new int[n];

        //前缀和
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + fruits[i][1];
            // 相当于要包含i这个下标本身
            indices[i] = fruits[i][0];
        }

        int ans = 0;

        for (int x = 0; x <= k / 2; x++) {
            /*向左走x步，再向右走k-x步*/
            int y = k - 2 * x;
            int left = startPos - x;
            int right = startPos + y;

            int start = lowerBound(indices, 0, n - 1, left);
            int end = upperBound(indices, 0, n - 1, right);

            ans = Math.max(ans, sum[end] - sum[start]);

            /*向右走x步，在向左走k - x 步*/
            y = k - 2 * x;
            left = startPos - y;
            right = startPos + x;

            start = lowerBound(indices, 0, n - 1, left);
            end = upperBound(indices, 0, n - 1, right);

            ans = Math.max(ans, sum[end] - sum[start]);
        }

        return ans;
    }



    public int lowerBound(int[] arr, int left, int right, int val) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= val) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }



    // 这里也太抽象了把         其实吧也还好，静下心来，可以搞

    public int upperBound(int[] arr, int left, int right, int val) {

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] > val) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
