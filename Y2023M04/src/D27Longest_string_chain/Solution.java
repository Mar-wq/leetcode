package D27Longest_string_chain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int longestStrChain(String[] words) {
        //双值集合    put  contain
        Map<String, Integer> cnt = new HashMap<String, Integer>();

        // 为了简化匿名内部内   只有单个抽象方法的接口类就可以用lambda代替
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        int res = 0;


        // 有点像学的那个单列集合的遍历 增强for循环
        for (String word : words) {
            cnt.put(word, 1);
            for (int i = 0; i < word.length(); i++) {
                String prv = word.substring(0, i) + word.substring(i + 1);

                if (cnt.containsKey(prv)) {
                    cnt.put(word, Math.max(cnt.get(word), cnt.get(prv) + 1));
                }
            }

            res = Math.max(res, cnt.get(word));
        }

        return res;
    }
}
