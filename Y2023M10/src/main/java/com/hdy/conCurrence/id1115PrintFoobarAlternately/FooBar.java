package com.hdy.conCurrence.id1115PrintFoobarAlternately;
/*
class FooBar {
    private int n;
    private static final Object lock = new Object();
    private Object response = null;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            synchronized (lock) {
                while (response != null) {
                    lock.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                response = new Object();
                lock.notifyAll();
            }

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            synchronized (lock) {
                while (response == null) {
                    lock.wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();

                response = null;
                lock.notifyAll();
            }
        }
    }
}*/


class FooBar {
    private int n;
    private boolean flag;

    public FooBar(int n) {
        this.n = n;
        this.flag = true;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            synchronized (this) {
                while (flag != true) {
                    this.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                flag = false;
                this.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            synchronized (this) {
                while (flag != false) {
                    this.wait();
                }

                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                flag = true;
                this.notify();
            }
        }
    }
}