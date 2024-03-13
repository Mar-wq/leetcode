package com.hdy.conCurrence.id1226TheDiningPhilosophers;

import java.util.concurrent.locks.ReentrantLock;


/*class DiningPhilosophers {
    private final ReentrantLock[] lockList = {new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
    };
    public DiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int lChopstick = philosopher, rChopstick = (philosopher + 1) % 5;
        if (lockList[lChopstick].tryLock()) {
            try {
                pickLeftFork.run();
                if (lockList[rChopstick].tryLock()) {
                  try {
                      pickRightFork.run();
                      eat.run();
                  } finally {
                      putRightFork.run();
                      lockList[rChopstick].unlock();
                  }
                }
            } finally {
                putLeftFork.run();
                lockList[lChopstick].unlock();
            }
        }
    }
}*/


class DiningPhilosophers {
    private final Object[] chopsticks = new Object[] {
            new Object(),
            new Object(),
            new Object(),
            new Object(),
            new Object(),
    };
    public DiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int lChopstick = philosopher % 5, rChopstick = (philosopher + 1) % 5;
        if (philosopher != 4) {
            synchronized (chopsticks[lChopstick]) {
                synchronized (chopsticks[rChopstick]) {
                    pickLeftFork.run();
                    pickRightFork.run();

                    eat.run();

                    putLeftFork.run();
                    putRightFork.run();
                }
            }
        } else {
            synchronized (chopsticks[rChopstick]) {
                synchronized (chopsticks[lChopstick]) {
                    pickLeftFork.run();
                    pickRightFork.run();

                    eat.run();

                    putLeftFork.run();
                    putRightFork.run();
                }
            }
        }
    }
}
