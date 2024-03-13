package com.hdy.conCurrence.id1117BuildingH2o;

import java.util.concurrent.atomic.AtomicInteger;

class H2O {
    private int state = 0;
    private Object lock = new Object();
    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {


        synchronized (lock) {
            while (state == 2) {
                lock.wait();
            }

            state++;
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            lock.notifyAll();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        synchronized (lock) {
            while (state != 2) {
                lock.wait();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            state = 0;
            releaseOxygen.run();
            lock.notifyAll();
        }
    }
}
