package id68text_justification;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int right = 0, n = words.length;
        while (true) {
            int left = right; // 当前行的第一个单词在word的位置
            int sumLen = 0; // 统计这一行单词长度之和
            // 循环确定当前行可以放多少单词，注意单词之间至少应该有一个空格
            while (right < n && sumLen + words[right].length() + right - left <= maxWidth) {
                sumLen += words[right++].length();
            }

            // 当前行是最后一行：单词左对齐，且单词之间应该只有一个空格，在行末填充剩余空格
            if (right == n) {
                StringBuffer sb = join(words, left, n, " ");
                sb.append(blank(maxWidth - sb.length()));
                ans.add(sb.toString());
                return ans;
            }

            int numWords = right -left;
            int numSpace = maxWidth - sumLen;

            // 当前行只有一个单词： 该单词左对齐，在行末填充剩余空格
            if (numWords == 1) {
                StringBuffer sb = new StringBuffer((words[left]));
                sb.append(blank(numSpace));
                ans.add(sb.toString());
                continue;
            }

            // 当前行不只一个单词
            int avgSpaces = numSpace / (numWords - 1);
            int extraSpaces = numSpace % (numWords - 1);
            StringBuffer sb = new StringBuffer();
            sb.append(join(words, left, left + extraSpaces + 1, blank(avgSpaces + 1))); // 额外拼接一个空格
            sb.append(join(words, left + extraSpaces + 1, right, blank(avgSpaces))); // 拼接其余单词
            ans.add(sb.toString());
        }




    }
    // blank 返回长度为n的由空格组成的字符串
    public String blank(int n) {
        StringBuffer sb  = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append(' ');
        }

        return sb.toString();
    }

    // join 返回用sep拼接[left, right] 范围内的words组成的字符串
    public StringBuffer join(String[] words, int left, int right, String sep) {
        StringBuffer sb = new StringBuffer((words[left]));
        for (int i = left + 1; i < right; i++) {
            sb.append(sep);
            sb.append(words[i]);
        }

        return sb;
    }
}
