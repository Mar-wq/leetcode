package Id2178;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> res = new ArrayList<>();
        if (finalSum % 2 == 1) {
            return res;
        }

        for (long i = 2; i <= finalSum; i += 2) {
            res.add(i);
            finalSum -= i;
        }

        res.set(res.size() - 1, res.get(res.size() - 1) + finalSum);

        return res;
    }
}
