package id638shopping_offers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private Map<List<Integer>, Integer> memo = new HashMap<>();
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return dfs(price, special, needs);
    }

    int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (memo.containsKey(needs)) {
            return memo.get(needs);
        }


        int result = 0;
        for (int i = 0; i < needs.size(); i++) {
            if (needs.get(i) < 0) {
                return (Integer.MAX_VALUE / 2);
            }

            result += needs.get(i) * price.get(i);
        }


        for (int i = 0; i < special.size(); i++) {
            List<Integer> pack = special.get(i);
            for (int i1 = 0; i1 < pack.size() - 1; i1++) {
                needs.set(i1, needs.get(i1) - pack.get(i1));
            }
            int temp = dfs(price, special, needs) + pack.get(pack.size() - 1);
            result = Math.min(result, temp);
            for (int i1 = 0; i1 < pack.size() - 1; i1++) {
                needs.set(i1, needs.get(i1) + pack.get(i1));
            }
        }

        memo.put(needs, result);
        return result;
    }
}
