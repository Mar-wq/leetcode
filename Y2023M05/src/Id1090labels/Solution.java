package Id1090labels;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int largestValsFromLabels(final int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> values[b] - values[a]);

        int res = 0, cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.getOrDefault(labels[indices[i]], 0) >= useLimit) {
                continue;
            }

            res += values[indices[i]];
            cnt++;
            if (cnt >= numWanted) {
                break;
            }
            map.put(labels[indices[i]], map.getOrDefault(labels[indices[i]], 0) + 1);
        }

        return res;
    }
}
