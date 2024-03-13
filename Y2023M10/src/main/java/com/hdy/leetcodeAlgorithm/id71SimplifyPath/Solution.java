package com.hdy.leetcodeAlgorithm.id71SimplifyPath;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Solution {
    public String simplifyPath(String path) throws InterruptedException {
        String[] paths = path.split("/");
        Deque<String> stack = new ArrayDeque<>();

        Thread thread = Thread.currentThread();
        thread.join();

        for (String s : paths) {
            if ("..".equals(s)) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (s.length() > 0 && !".".equals(s)) {
                stack.addLast(s);
            }
        }

        StringBuilder sb = new StringBuilder();

        if (stack.isEmpty()) {
            sb.append("/");
        } else {
            while (!stack.isEmpty()) {
                sb.append("/");
                sb.append(stack.pollFirst());
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            System.out.println("11111111");
        });

        executorService.execute(() -> {
            System.out.println("2222");
        });

        System.out.println("end");
    }

}



