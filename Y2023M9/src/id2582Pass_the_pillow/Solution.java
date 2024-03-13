package id2582Pass_the_pillow;

class Solution {
    public int passThePillow(int n, int time) {
        int period = 2 * (n - 1);
        time = time % period;

        int passCnt = time >= n - 1 ? period - time : time;


        return passCnt + 1;
    }
}
