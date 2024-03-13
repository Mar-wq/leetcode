package com.hdy.toPlay;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class T {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        Thread.sleep(2000);


        list.forEach((s) -> System.out.println(s));
    }
}


class Solution {
    public int sumOfMultiples(int n) {
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (i % 3 == 0) {
                res += i;
            } else if (i % 5 == 0) {
                res += i;
            } else if (i % 7  == 0) {
                res += i;
            }
        }

        return res;
    }
}
