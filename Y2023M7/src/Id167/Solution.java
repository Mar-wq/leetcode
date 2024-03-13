package Id167;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
/*    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> m = new HashMap<>();
*//*        for (int i = 0; i < numbers.length; i++) {
            m.put(numbers[i], i + 1);
        }*//*

        int[] res = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            if (m.containsKey((target - number))) {
                res[1] = i;
                res[0] = m.get(target - number);
                break;
            }

            m.put(numbers[i], i + 1);
        }

        return res;
    }*/

    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int[] res = new int[2];
        for (int i = 0; i < n; i++) {
            int number = numbers[i];
            int dis = target - number;
            int index = binF(numbers, i + 1, dis);
            if (index != -1) {
                res[0] = i + 1;
                res[1] = index + 1;
                break;
            }
        }

        return res;
    }

    private int binF(int[] numbers, int start, int target) {
        int r = numbers.length;
        int l = start;

        while (l < r) {
            int mid = (l + r) /2;
            if (numbers[mid] == target) {
                return mid;
            } else if (numbers[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return -1;
    }
}
