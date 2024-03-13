package id2240number_of_ways_to_buy_pens_and_pencils;

public class Solution {
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long res = 0;

        for (int i = 0; i * cost1 <= total; i++) {
            int remaining = total - i *cost1;
            res += ((remaining + cost2) / cost2);
        }

        return res;
    }
}
