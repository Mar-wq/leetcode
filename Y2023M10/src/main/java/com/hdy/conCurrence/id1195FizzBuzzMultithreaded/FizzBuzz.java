package com.hdy.conCurrence.id1195FizzBuzzMultithreaded;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;



class FizzBuzz1 {
    private int n;
    private Object lock = new Object();
    private int cur = 1;

    public FizzBuzz1(int n) {
        this.n = n;
    }



    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (cur <= n) {
            synchronized (lock) {
                if (cur % 3 == 0 && cur % 15 != 0) {
                    printFizz.run();
                    ++cur;
                }
                lock.notifyAll();
                lock.wait();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (cur <= n) {
            synchronized (lock) {
                if (cur % 5 == 0 && cur % 15 != 0) {
                    printBuzz.run();
                    ++cur;
                }
                lock.notifyAll();
                lock.wait();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (cur <= n) {
            synchronized (lock) {
                if (cur % 15 == 0) {
                    printFizzBuzz.run();
                    ++cur;
                }
                lock.notifyAll();
                lock.wait();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (cur <= n) {
            synchronized (lock) {
                if (cur % 3 != 0 && cur % 15 != 0 && cur % 5 != 0) {
                    printNumber.accept(cur);
                    ++cur;
                }
                lock.notifyAll();
                lock.wait();
            }
        }
    }

    public static void main(String[] args) {
        FizzBuzz1 fb = new FizzBuzz1(10);
        ExecutorService pool = Executors.newFixedThreadPool(4);


        pool.execute(() -> {
            try {
                fb.fizz(() -> System.out.println("f"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.execute(() -> {
            try {
                fb.buzz(() -> System.out.println("b"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        pool.execute(() -> {
            try {
                fb.fizzbuzz(() -> System.out.println("fb"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        pool.execute(() -> {

            try {
                fb.number(new IntConsumer() {
                    @Override
                    public void accept(int value) {
                        System.out.println(value);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("end");
    }
}

class FizzBuzz {
    private int n;

    private Semaphore number = new Semaphore(1);
    private Semaphore fizz = new Semaphore(0);
    private Semaphore buzz = new Semaphore(0);
    private Semaphore fizzbuzz = new Semaphore(0);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 0; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                fizz.acquire();
                printFizz.run();
                number.release();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 0; i <= n; i++) {
            if (i % 3 != 0 && i % 5 == 0) {
                buzz.acquire();
                printBuzz.run();
                number.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 0; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                fizzbuzz.acquire();
                printFizzBuzz.run();
                number.release();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i <= n; i++) {
            number.acquire();
            if (i % 3 != 0 && i % 5 != 0) {
                printNumber.accept(i);
                number.release();
            } else if (i % 3 == 0 && i % 5 != 0) {
                fizz.release();
            } else if (i % 3 != 0 && i % 5 == 0) {
                buzz.release();
            } else {
                fizzbuzz.release();
            }
        }
    }

    public static void main(String[] args) {
        FizzBuzz fb = new FizzBuzz(10);
        ExecutorService pool = Executors.newFixedThreadPool(4);


        pool.execute(() -> {
            try {
                fb.fizz(() -> System.out.println("f"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.execute(() -> {
            try {
                fb.buzz(() -> System.out.println("b"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        pool.execute(() -> {
            try {
                fb.fizzbuzz(() -> System.out.println("fb"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        pool.execute(() -> {

            try {
                fb.number(new IntConsumer() {
                    @Override
                    public void accept(int value) {
                        System.out.println(value);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("end");
    }
}



class Solution {
    public int calculate(String s) {
        int sign = 1;
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                continue;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if (s.charAt(i) == '+') {
                sign = 1;
            } else if (s.charAt(i) == '(') {
                int j = nextBrk(s, i);
                res += sign * calculate(s.substring(i + 1, j));
                i = j;
            } else {
                int j = nextNotNum(s, i);
                Integer num = Integer.valueOf(s.substring(i, j));
                i = j - 1;
                res += sign * num;
            }
        }

        return res;
    }

    private int nextNotNum(String s, int i) {
        for (int j = i; j < s.length(); j++) {
            if (s.charAt(j) < '0' || s.charAt(j) >'9') {
                return j;
            }
        }

        return s.length();
    }

    private int nextBrk(String s, int i) {
        int l = 1;
        for (int j = i + 1; j < s.length(); j++) {
            if (s.charAt(j) == '(') {
                ++l;
            }

            if (s.charAt(j) == ')') {
                --l;
                if (l == 0) {
                    return j;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("我去wait了");
                    lock.notify();
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                System.out.println("我抢到锁了");
            }
        }).start();

        System.out.println("end");
    }
}
