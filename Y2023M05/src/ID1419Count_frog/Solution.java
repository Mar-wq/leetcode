package ID1419Count_frog;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int minNumberOfFrogs(String croakOfFrogs) {
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Character> last = new HashMap<>();
        last.put('c', 'b'); last.put('r', 'c'); last.put('o', 'r'); last.put('a', 'o'); last.put('k', 'a');
        map.put('b', 100001);

        int res = 0;
        // 拆箱和装箱是发生在赋值的时候自动转换的
        for (int i = 0; i < croakOfFrogs.length(); i++) {
            char c = croakOfFrogs.charAt(i);
            Integer val = map.getOrDefault(c, 0);
            Integer lastVal = map.getOrDefault(last.get(c), 0);

            if (val + 1 > lastVal) {
                return -1;
            }
            map.put(c, val + 1);

            if (c == 'c') {
                res = Math.max(res, val + 1);
            }

            if (c == 'k') {
                Integer v = map.get(c);
                map.put('c', map.get('c') - v);
                map.put('r', map.get('r') - v);
                map.put('o', map.get('o') - v);
                map.put('a', map.get('a') - v);
                map.put('k', map.get('k') - v);
            }

            if (i == croakOfFrogs.length() - 1) {
                if (map.get('c') != 0 || map.get('r') != 0 || map.get('o') != 0 || map.get('a') != 0 || map.get('k') != 0) {
                    return -1;
                }

            }
        }

        return res;
    }


/*    public int minNumberOfFrogs(String croakOfFrogs) {
        if (croakOfFrogs.length() % 5 != 0) {
            return -1;
        }

        int res = 0, frogNum = 0;
        int[] cnt = new int[4];

        // 这里对双值列表的初始化得学一学
        Map<Character, Integer> map = new HashMap<Character, Integer>() {{
           put('c', 0);
           put('r', 1);
           put('o', 2);
           put('a', 3);
           put('k', 4);
        }};

        for (int i = 0; i < croakOfFrogs.length(); i++) {
            char c = croakOfFrogs.charAt(i);
            int t = map.get(c);

            if (t == 0) {
                cnt[t]++;
                frogNum++;
                if (frogNum > res) {
                    res = frogNum;
                }
            } else {
                if (cnt[t - 1] == 0) {
                    return -1;
                }

                cnt[t - 1]--;

                if (t == 4) {
                    frogNum--;
                } else {
                    cnt[t]++;
                }
            }
        }

        if (frogNum > 0) {
            return -1;
        }

        return res;
    }*/
}
