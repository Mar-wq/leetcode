package id2485FindCenterNum;


import org.junit.Test;

public class Solution {
    public int pivotInteger(int n) {
        int sumUpN = sumUpToN(n);
        int l = 1, r = n;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (sumUpToN(mid) * 2 == sumUpN + mid) {
                return mid;
            } else if (sumUpToN(mid) * 2 > sumUpN + mid) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return -1;
    }

    public int sumUpToN(int n) {
        return (n * (n + 1)) / 2;
    }

}
