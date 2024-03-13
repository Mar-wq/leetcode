package com.hdy.conCurrence.hungry;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestHungryLock {
    static final List<String> MENU = Arrays.asList(
            "地三鲜",
            "宫保鸡丁",
            "辣子鸡丁",
            "米拉烤翅"
    );

    static Random Random = new Random();
    static String cooking() {
        return MENU.get(Random.nextInt(MENU.size()));
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> {
            System.out.println("处理点餐....");
            Future<String> f = executorService.submit(() -> {
                System.out.println("做菜ing");
                return cooking();
            });

            try {
                System.out.println(f.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            System.out.println("处理点餐....");
            Future<String> f = executorService.submit(() -> {
                System.out.println("做菜ing");
                return cooking();
            });

            try {
                System.out.println(f.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
