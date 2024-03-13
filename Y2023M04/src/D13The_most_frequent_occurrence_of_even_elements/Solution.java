package D13The_most_frequent_occurrence_of_even_elements;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();


        //原始数组的遍历   记住了
        for (int x : nums) {
            if (x % 2 == 0) {
                count.put(x, count.getOrDefault(x, 0) + 1);
            }
        }

        int res = -1, cnt = 0;

        for (Map.Entry<Integer, Integer>entry : count.entrySet()) {
            if (res == -1 || entry.getValue() > cnt || entry.getValue() == cnt && res > entry.getKey()) {
                res = entry.getKey();
                cnt = entry.getValue();
            }
        }

        return res;
    }
}
