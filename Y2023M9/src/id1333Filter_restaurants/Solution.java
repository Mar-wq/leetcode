package id1333Filter_restaurants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < restaurants.length; i++) {
            if (restaurants[i][2] != veganFriendly) {
                continue;
            }

            if (restaurants[i][3] > maxPrice) {
                continue;
            }

            if (restaurants[i][4] > maxDistance) {
                continue;
            }

            res.add(i);
        }

        res = Arrays.stream(res.stream().sorted((a, b) -> {
            if (restaurants[a][2] == restaurants[b][2]) {
                return restaurants[b][1] - restaurants[a][1];
            }

            return restaurants[b][2] - restaurants[a][2];
        }).mapToInt(a -> restaurants[a][0]).toArray()).boxed().toList();


        return res;
    }
}
