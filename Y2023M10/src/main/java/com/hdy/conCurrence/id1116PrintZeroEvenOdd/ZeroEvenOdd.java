package com.hdy.conCurrence.id1116PrintZeroEvenOdd;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/*class ZeroEvenOdd {
    private int n;
    private ReentrantLock lock;
    private Condition zero;
    private Condition even;
    private Condition odd;
    private volatile int start = 1;
    private volatile int flag = 0;

    public ZeroEvenOdd(int n) {
        this.n = n;
        this.lock = new ReentrantLock();
        this.zero = lock.newCondition();
        this.even = lock.newCondition();
        this.odd = lock.newCondition();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();  // 锁住这一行之后的所有代码
        try {
            while (start <= n) {
                if (flag != 0) {
                    zero.await();
                } else {
                    printNumber.accept(0);
                    if (start % 2 == 1) {
                        odd.signal();
                        flag = 1;
                    } else {
                        even.signal();
                        flag = 2;
                    }
                }
            }
            odd.signal();
            even.signal();
        } finally {
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (start <= n) {
                if (flag != 2) {
                    even.await();
                } else {
                    printNumber.accept(start++);
                    zero.signal();
                    flag = 0;
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (start <= n) {
                if (flag  != 1) {
                    odd.await();
                } else {
                    printNumber.accept(start++);
                    zero.signal();
                    flag = 0;
                }
            }
        } finally {
            lock.unlock();
        }
    }
    private void test() {
        System.out.println("hello");
    }

    public static void main  (String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(7);
        PrintNumber printNumber = new PrintNumber();
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                zeroEvenOdd.odd(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                zeroEvenOdd.even(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}*/

class PrintNumber implements IntConsumer {
    @Override
    public void accept(int value) {
        System.out.println(value);
    }

}

class ZeroEvenOdd {
    private int n;
    private Map<String, Thread> map = new ConcurrentHashMap<>();
    volatile int state = 0; // 0打印0， 1打印奇数， 2打印偶数

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        map.put("zero", Thread.currentThread());
        for (int i = 0; i < n; i++) {
            while (state != 0) {
                LockSupport.park();
            }

            printNumber.accept(0);

            if ((i & 1) == 0) {
                state = 2;
            } else {
                state = 1;
            }

            map.forEach((k, v) -> LockSupport.unpark(v)); // 通知其他两个线程
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        map.put("even", Thread.currentThread());

        for (int i = 2; i <= n; i += 2) {
            while (state != 2) {
                LockSupport.park();
            }

            printNumber.accept(i);
            state = 0;
            LockSupport.unpark(map.get("zero")); // 通知zero线程
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        map.put("odd", Thread.currentThread());

        for (int i = 1; i <= n; i += 2) {
            while (state != 2) {
                LockSupport.park();
            }

            printNumber.accept(i);
            state = 0;
            LockSupport.unpark(map.get("zero")); // 通知zero线程
        }
    }
}
