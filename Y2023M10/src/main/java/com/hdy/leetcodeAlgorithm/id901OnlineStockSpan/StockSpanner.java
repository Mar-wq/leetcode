package com.hdy.leetcodeAlgorithm.id901OnlineStockSpan;


import java.util.Stack;

class StockSpanner {
    private Stack<int[]> monotoneStk;
    int idx;

    public StockSpanner() {
        idx = 0;
        monotoneStk = new Stack<>();
        monotoneStk.push(new int[]{-1, 1000000});
    }

    public int next(int price) {
        while (!monotoneStk.isEmpty() && monotoneStk.peek()[1] <= price) {
            monotoneStk.pop();
        }
        int result = idx - monotoneStk.peek()[0];
        monotoneStk.add(new int[]{idx, price});
        idx++;

        return result;
    }
}
