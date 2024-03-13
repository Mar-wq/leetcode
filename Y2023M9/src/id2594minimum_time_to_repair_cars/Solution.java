package id2594minimum_time_to_repair_cars;

import java.util.PriorityQueue;

public class Solution {
    /*public long repairCars(int[] ranks, int cars) {
        PriorityQueue<Work> pq = new PriorityQueue<>((Work a, Work b) -> {
            if (a.capbility * (a.fixCnt + 1) * (a.fixCnt + 1) == b.capbility * (b.fixCnt + 1) * (b.fixCnt + 1)) {
                return (int) (a.capbility - b.capbility);
            }
           return (int) (a.capbility * (a.fixCnt + 1) * (a.fixCnt + 1) - b.capbility * (b.fixCnt + 1) * (b.fixCnt + 1));
        });

        for (int rank : ranks) {
            pq.add(new Work(rank, 0));
        }

        long res = -1;
        while (cars > 0) {
            Work w = pq.poll();
            w.andOne();
            res = Math.max(res, w.workTime());
            pq.add(w);
            cars--;
        }

        return res;
    }
    class Work{
        int capbility;
        long fixCnt;

        public Work(int capbility, long fixCnt) {
            this.capbility = capbility;
            this.fixCnt = fixCnt;
        }

        public long getCapbility() {
            return capbility;
        }

        public void setCapbility(int capbility) {
            this.capbility = capbility;
        }

        public long getFixCnt() {
            return fixCnt;
        }

        public void setFixCnt(long fixCnt) {
            this.fixCnt = fixCnt;
        }
        public void andOne() {
            this.fixCnt = this.fixCnt + 1;
        }

        public long workTime() {
            return fixCnt * fixCnt * capbility;
        }
    }*/

    public long repairCars(int[] ranks, int cars) {
        long l = 1, r = (long) (100 * 1e6 * 1e6);
        while (l < r) {
            long mid = l + (r - l) / 2;
            if (canFinsh(ranks, cars, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    boolean canFinsh(int[] ranks, int cars, long timePerWork) {
        long cnt = 0;
        for (int rank : ranks) {
            cnt += (long) Math.sqrt(timePerWork / rank);
        }

        return cnt >= cars;
    }
}
