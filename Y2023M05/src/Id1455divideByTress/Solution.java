package Id1455divideByTress;

class Solution {
    public int averageValue(int[] nums) {
        int cnt = 0, sum = 0;
        for (int num : nums) {
            if (num % 6 == 0) {
                sum += num;
                cnt++;
            }
        }

        return sum == 0 ? sum : sum / cnt;
    }
}