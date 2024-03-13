package com.hdy.conCurrence.id1114PrintInOrder;

import java.util.concurrent.atomic.AtomicInteger;

/*class Foo {
    private AtomicInteger firstJobDone = new AtomicInteger(0);
    private AtomicInteger secondJobDone = new AtomicInteger(0);


    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();

        firstJobDone.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (firstJobDone.get() != 1) {
            // 等待任务一的完成，但这里是空转，也会浪费cpu
        }

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();

        secondJobDone.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (secondJobDone.get() != 1) {
            // 同上
        }


        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}*/


/*
class Foo {
    private Object lock1, lock2;

    public Foo() {
        lock1 = new Object();
        lock2 = new Object();
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        synchronized (lock1) {
            printFirst.run();
            lock1.notify();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock1) {
            lock1.wait();
            synchronized (lock2) {
                // printSecond.run() outputs "second". Do not change or remove this line.
                printSecond.run();
                lock2.notify();
            }
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock2) {
            lock2.wait();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}
*/


class Foo {
    private Object lock1, lock2;

    public Foo() {
        lock1 = new Object();
        lock2 = new Object();
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        synchronized (lock1) {
            printFirst.run();
            lock1.notify();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock1) {
            lock1.wait();
            synchronized (lock2) {
                // printSecond.run() outputs "second". Do not change or remove this line.
                printSecond.run();
                lock2.notify();
            }
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock2) {
            lock2.wait();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}
