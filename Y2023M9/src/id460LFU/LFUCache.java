package id460LFU;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

class LFUCache {
    // key到val的映射
    private HashMap<Integer, Integer> keyToVal;
    // key到freq的映射
    private HashMap<Integer, Integer> keyToFreq;
    // freq到key列表的映射
    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    // 记录最小频次
    int minFreq;
    // 记录LFU缓存的最大容量
    int cap;



    public LFUCache(int capacity) {
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        this.cap = capacity;
        this.minFreq = 0;

    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        // 增加key对应的freq
        increaseFreq(key);

        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        if (keyToVal.containsKey(key)) {
            // 将对应key的freq加1
            increaseFreq(key);
            // 修改key对应的值
            keyToVal.put(key, value);

            return;
        }

        if (this.cap <= keyToVal.size()) {
            removeMinFreqKey();
        }

        keyToVal.put(key, value);
        keyToFreq.put(key, 1);

        // 插入Fk表
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);

        this.minFreq = 1;
    }

    private void removeMinFreqKey() {
        // freq最小的key列表
        LinkedHashSet<Integer> keyList = freqToKeys.get(minFreq);
        //
        int deleteKey = keyList.iterator().next();
        keyList.remove(deleteKey);
        if (keyList.isEmpty()) {
            freqToKeys.remove(this.minFreq);
            // 我觉着这里需要更新最小频率吧  好吧   其实没必要    put紧接着就会把freq变为1
        }

        keyToVal.remove(deleteKey);
        keyToFreq.remove(deleteKey);
    }

    private void increaseFreq(int key) {
        int freq = keyToFreq.get(key);
        keyToFreq.put(key, freq + 1);
        freqToKeys.get(freq).remove(key);
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);
        // 如果freq对应的列表空了，移除这个freq
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);

            // 如果这个freq恰好是minFreq，更新minfreq的值
            if (freq == this.minFreq) {
                this.minFreq++;
            }
        }
    }
}
