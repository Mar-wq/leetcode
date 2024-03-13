package Id1054;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> count =  new HashMap<>();

        for (int barcode : barcodes) {
            if (!count.containsKey(barcode)) {
                count.put(barcode, 0);
            }

            count.put(barcode, count.get(barcode) + 1);
        }


        // 这里使用到了优先队列       并且还是用lambda 表达式
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);


        // 这里还设计到了内部类                      对比一下count.keySet();
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            int[] ints = new int[]{entry.getValue(), entry.getKey()};
            // 这里的数组构造方法没学过  new int[]{entry.getValue(), entry.getKey()}
            pq.offer(new int[]{entry.getValue(), entry.getKey()});
        }

        int n = barcodes.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            int[] p = pq.poll();
            int val = p[1], cnt = p[0];

            if (i == 0 || res[i - 1] != val) {
                res[i] = val;

                if (cnt > 1) {
                    pq.offer(new int[]{cnt - 1, val});
                }
            } else {
                int[] q = pq.poll();
                pq.offer(p);
                val = q[1]; cnt = q[0];
                res[i] = val;

                if (cnt > 1) {
                    pq.offer(new int[]{cnt - 1, val});
                }
            }
        }

        return res;
    }
}
