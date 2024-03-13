package id189rotate_array;

import java.util.Arrays;

public class Solution {
    public void rotate(int[] nums, int k) {
        /*int n = nums.length;
        k = k % n;

        int[] numsBak = new int[n];
        for (int i = 0; i < n; i++) {
            numsBak[i] = nums[i];
        }

        for (int i = 0; i < n; i++) {
            int pos = (i + k) % n;
            nums[pos] = numsBak[i];
        }

        return;*/
        int n = nums.length;
        k = k % n;

        if (k == 0)
            return;

        int cnt = gcd(n, k);

        for (int start = 0; start < cnt; start++) {
            int pos = start;
            int num = nums[start];
            do {
                int next = (pos + k) % n;
                int temp = nums[next];
                nums[next] = num;
                num = temp;
                pos = next;
            } while (start != pos);
        }


        return;
    }

    int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    }
}
