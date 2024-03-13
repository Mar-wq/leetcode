package com.hdy.leetcodeAlgorithm.id2034StockPriceFluctuation;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;


// 还可以考虑用两个优先队列， 虽然不能直接跟新优先队列中的值，但可以考虑延迟删除，具体而言就是出队列的时候，我们可以查看出队列的时间戳和价格是否和hashMap当中的匹配，匹配则认为是没有过期的，反之亦然。

class StockPrice {
    private HashMap<Integer, Integer> timeToP;
    private TreeMap<Integer, Integer> priceAndTs;
    private int current;
    public StockPrice() {
        this.timeToP = new HashMap<>();
        this.priceAndTs = new TreeMap<>((a, b) -> b - a);
        this.current =  0;
    }

    public void update(int timestamp, int price) {
        if (timestamp > current) {
            current = timestamp;
        }
        int prevPrice = timeToP.getOrDefault(timestamp, 0);
        timeToP.put(timestamp, price);
        if (prevPrice > 0) {
            priceAndTs.put(prevPrice, priceAndTs.get(prevPrice) - 1);
            if (priceAndTs.get(prevPrice) == 0) {
                priceAndTs.remove(prevPrice);
            }
        }

        timeToP.put(timestamp, price);


    }

    public int current() {
        return timeToP.get(current);
    }

    public int maximum() {
        return priceAndTs.firstKey();
    }

    public int minimum() {
        return priceAndTs.lastKey();
    }
}
