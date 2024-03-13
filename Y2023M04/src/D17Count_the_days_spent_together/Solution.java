package D17Count_the_days_spent_together;

class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] datesOfMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};  //定义数组  并初始化
        int[] prefixSum = new int[13];

        //用到前缀和求天数
        for (int i = 0; i < 12; i++) {
            prefixSum[i + 1] = prefixSum[i] + datesOfMonths[i];
        }

        int arriveAliceDay = calculateDay(arriveAlice, prefixSum);
        int leaveAliceDay = calculateDay(leaveAlice, prefixSum);
        int arriveBodDay = calculateDay(arriveBob, prefixSum);
        int leaveBobDay = calculateDay(leaveBob, prefixSum);

        //这里的比较很精妙    我不管有没有交集    反正最早走的减去最晚到的，之间就是我们的交集   如果为负   证明根本没有交集     ******
        return Math.max(0, Math.min(leaveAliceDay, leaveBobDay) - Math.max(arriveAliceDay, arriveBodDay) + 1);
    }

    public int calculateDay(String date,int[] prefixSum) {

        //字符串转int的函数简单记一下
        int month = Integer.parseInt(date.substring(0, 2));
        int days = Integer.parseInt(date.substring(3));

        return prefixSum[month - 1] + days;
    }
}
