package ID2432The_employee_who_worked_on_the_task_that_took_the_longest;

class Solution {
    public int hardestWorker(int n, int[][] logs) {
        int res = logs[0][0];
        int finshtime = logs[0][1];

        for (int i = 1; i < logs.length; i++) {
            if (logs[i][1] - logs[i - 1][1] > finshtime) {
                finshtime = logs[i][1] - logs[i - 1][1];
                res = logs[i][0];
            } else if (logs[i][1] - logs[i - 1][1] == finshtime) {
                res = Math.min(res, logs[i][0]);
            }
        }


        return res;
    }
}