package id42trapping_rain_water;

import java.util.Stack;

class Solution {
/*    public int trap(int[] height) {
        int n = height.length;
        int[] lHigher = new int[n], rHigher = new int[n];

        lHigher[0] = height[0];
        for (int i = 1; i < n; i++) {
            lHigher[i] = Math.max(lHigher[i - 1], height[i]);
        }

        rHigher[n - 1] = height[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            rHigher[i] = Math.max(rHigher[i + 1], height[i]);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            int h = Math.min(rHigher[i], lHigher[i]);
            res += h - height[i];
        }

        return res;
    }*/


    public int trap(int[] height) {
        int n = height.length;
        Stack<Integer> stk = new Stack<>();

        int res = 0;
        for (int i = 0; i < n; i++) {
            while (!stk.empty() && height[i] > height[stk.peek()]) {
                int endH = height[stk.peek()];
                stk.pop();

                if (stk.empty()) {
                    break;
                }

                int fenceH = Math.min(height[stk.peek()], height[i]);
                int length = i - stk.peek() - 1;
                res += length * (fenceH - endH);
            }
            stk.push(i);
        }

        return res;
    }
}