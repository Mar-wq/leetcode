package com.hdy.laze_to_create_packge_for_leetcode.travel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
// 1:无需package
// 2: 类名必须Main, 不可修改

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        int n, m;
        n = scan.nextInt();
        m = scan.nextInt();
        int[][] stations = new int[n][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                stations[i][j] = scan.nextInt();
            }
        }

        Solution solution = new Solution();
        int res = solution.prepareMoney(stations, m);
        System.out.println(res);
        scan.close();
    }
}


class Solution {
    private Map<Key, Val> memo = new HashMap<>();
    private int res = Integer.MAX_VALUE;
    int m;
    public int prepareMoney(int[][] stations, int m) {
        this.m = m;
        if(dfs(stations, 0, m, 0)) {
            return res;
        }

        return -1;
    }

    private boolean dfs(int[][] stations, int i, int remain, int costMoney) {
        if (i == stations.length ) {
            res = Math.min(costMoney, res);
            return true;
        }

        int tempRemain = remain;
        int dis = stations[i][0];
        remain = remain - dis;
        if (remain < 0) {
            return false;
        }
        Key key = new Key(i, tempRemain);
        if (memo.containsKey(key)) {
            Val val = memo.get(key);
            if (val.cost <= costMoney) {
                return val.result;
            }
        }

        int l = stations[i][2];
        int container = m - remain;
        int up = Math.min(l, container);
        int cost = stations[i][1];
        int last = 0;

        boolean res = false;
        while (last <= up) {
            int mid = (last + up) / 2;
            if (dfs(stations, i + 1, remain + mid, costMoney + mid * cost)) {
                res = true;
                up = mid - 1;
            } else {
                last = mid + 1;
            }
        }
        Val val = new Val(res, costMoney);
        if (memo.containsKey(key)) {
            Val val1 = memo.get(key);
            if (val1.cost > val.cost) {
                memo.put(key, val);
            }
        } else {
            memo.put(key, val);
        }
        return res;
    }
}

class Key {
    int pos;
    int remain;

    public Key(int pos, int remain) {
        this.pos = pos;
        this.remain = remain;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }
}

class Val {
    boolean result;
    int cost;

    public Val(boolean result, int cost) {
        this.result = result;
        this.cost = cost;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}