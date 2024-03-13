package com.hdy.conCurrence.interrupted;

import java.util.concurrent.locks.LockSupport;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("我准备park了");
            LockSupport.park();
            System.out.println("这里执行了，证明没有抛出被打断异常，而是直接接着执行了");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("我准备sleep了");
            try {
                Thread.sleep(2000);
                System.out.println("这里执行了，证明没有抛出被打断异常，而是直接接着执行了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t2.start();
        Thread.sleep(1000);
        t2.interrupt();
        System.out.println("end");
    }
}
