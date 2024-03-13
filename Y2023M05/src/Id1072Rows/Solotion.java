package Id1072Rows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < m; i++) {
            char[] arr = new char[n];
            Arrays.fill(arr, '0');

            for (int j = 0; j < n; j++) {
                // 这里挺巧妙的   是1就反转  不是就没变化。
                arr[j] = (char)('0' + (matrix[i][j] ^ matrix[i][0]));
            }

            String s = new String(arr);
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        int res = 0;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            res =  Math.max(res, entry.getValue());
        }

        return res;
    }

}