package com.hdy.leetcodeAlgorithm.id380Insert_delete_getrandom_o1;

import java.util.*;

class RandomizedSet {
    Map<Integer, Integer> valToIdx;
    List<Integer> list;
    public RandomizedSet() {
        valToIdx = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (valToIdx.containsKey(val)) {
            return false;
        }
        list.add(val);
        valToIdx.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!valToIdx.containsKey(val)) {
            return false;
        }

        int last = list.size() - 1;
        int removeIdx = valToIdx.get(val);
        int lastVal = list.get(last);
        list.set(removeIdx, lastVal);
        valToIdx.put(lastVal, removeIdx);
        list.remove(last);
        valToIdx.remove(val);

        return true;
    }

    public int getRandom() {
        Random random = new Random();
        int idx = random.nextInt(list.size());

        return list.get(idx);
    }
}
