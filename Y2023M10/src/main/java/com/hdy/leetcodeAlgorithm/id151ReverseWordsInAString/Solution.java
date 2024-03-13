package com.hdy.leetcodeAlgorithm.id151ReverseWordsInAString;

import java.util.*;

class Solution {
/*    public String reverseWords(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续空白字符作为分隔符分割
        List<String> list = Arrays.asList(s.split("\\s+"));
        Collections.reverse(list);

        return String.join(" ", list);
    }*/

    /*public String reverseWords(String s) {
        StringBuilder sb = trimSpaces(s);

        // 翻转字符串
        reverse(sb, 0, sb.length() - 1);

        // 翻转每个单词
        reverseEachWord(sb);

        return sb.toString();
    }

    private void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;

        while (start < n) {
            // 循环至单词的末尾
            while (end < n && sb.charAt(end) != ' ') {
                ++end;
            }

            reverse(sb, start, end - 1);
            // 更新start，去找下一个单词
            start = end + 1;
            ++end;
        }
    }

    private void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    private StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;

        while (left <= right && s.charAt(left) == ' ') {
            left++;
        }

        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }

        // 将字符串间多余的空格去除
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ') {
                sb.append(c);  // 这里很巧妙   如果前一个不是空格才
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }

            left++;
        }

        return sb;
    }*/

    public String reverseWords(String s) {
        int left = 0, right = s.length() - 1;

        // 去掉开头的空格字符串
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }

        // 去掉末尾的空格字符串
        while (left <= right && s.charAt(right) == ' ') {
            ++right;
        }

        Deque<String> d = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();

        while (left <= right) {
            char c = s.charAt(left);
            if ((word.length() != 0) && (c == ' ')) {
                // 将单词push到队列的头部
                d.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }

        d.offerFirst(word.toString());

        return String.join(" ", d);
    }
}