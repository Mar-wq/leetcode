package Id1093bigStatistic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public double[] sampleStats(int[] count) {
/*        double minimum = -1, maximum = -1, mean, median, mode = 0;
        double sum = 0;
        int cnt = 0, singelCnt = 0;
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            if (count[i] != 0) {
                for (int j = 0; j < count[i]; j++) {
                    arr.add(i);
                }
                if (minimum == -1) {
                    minimum = i;
                }
                maximum = i;
                sum += count[i] * i;
                cnt += count[i];
                if (count[i] > singelCnt) {
                    singelCnt = count[i];
                    mode = i;
                }
            }
        }

        mean = sum / cnt;
        if (cnt / 2 == 1) {
            System.out.println(arr.get(cnt / 2));
            median = arr.get(cnt / 2);
        } else {
            System.out.println(arr.get(cnt / 2));
            System.out.println(arr.get((cnt - 1) / 2));

            median = (double) (arr.get(cnt / 2) + arr.get((cnt - 1) / 2)) / 2;
        }

        return new double[]{minimum, maximum, mean, median, mode};*/

        int n = count.length;
        int total = Arrays.stream(count).sum();
        double mean = 0.0;
        double median = 0.0;
        int minimum = 256;
        int maximum  = 0;
        int mode = 0;

        int left = (total + 1) / 2;
        int right = (total + 2) / 2;

        int maxfreq = 0;
        int cnt = 0;
        long sum = 0;

        for (int i = 0; i < n; i++) {
            sum += (long)count[i] * i;
            if (count[i] > maxfreq) {
                maxfreq = count[i];
                mode = i;
            }

            if (count[i] > 0) {
                if (minimum == 256) {
                    minimum = i;
                }
                maximum = i;
            }

            if (cnt < right && cnt + count[i] >= right) {
                median += i;
            }

            if (cnt < left && cnt + count[i] >= left) {
                median += i;
            }

            cnt += count[i];
        }

        mean = (double) sum / total;
        median = median / 2.0;

        return new double[]{minimum, maximum, mean, median, mode};
    }
}