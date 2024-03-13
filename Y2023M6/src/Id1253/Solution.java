package Id1253;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int n = colsum.length, allOne = 0;
        for (int i = 0; i < n; i++) {
            if (colsum[i] == 2) {
                allOne++;
            }
        }

        int upperLeft = upper - allOne;
        int lowerLeft = lower - allOne;

        List<List<Integer>> res = new ArrayList<>(2);
        List<Integer> up = new ArrayList<>(n);
        List<Integer> low = new ArrayList<>(n);

        if (upperLeft < 0 || lowerLeft < 0) {
            return res;
        }


        for (int i = 0; i < n; i++) {
            if (colsum[i] == 0) {
                up.add(0);
                low.add(0);
            } else if (colsum[i] == 1) {
                if (upperLeft > 0) {
                    up.add(1);
                    low.add(0);
                    upperLeft--;
                } else if (lowerLeft > 0) {
                    up.add(0);
                    low.add(1);
                    lowerLeft--;
                } else {
                    return res;
                }
            } else {
                up.add(1);
                low.add(1);
            }
        }

        if (upperLeft > 0 || lowerLeft > 0) {
            return res;
        }

        res.add(up);
        res.add(low);

        return res;
    }
}
