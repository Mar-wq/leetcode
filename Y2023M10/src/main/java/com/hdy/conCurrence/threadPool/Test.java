package com.hdy.conCurrence.threadPool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class Test {
    public static void main(String[] args) {
        TaskQueue<Runnable> tq = new TaskQueue<>(5);
        ThreadPool threadPool = new ThreadPool(tq, 2, 1, TimeUnit.SECONDS, ((queue, task) -> queue.put(task)));

        for (int i = 0; i < 5; i++) {
            int j = i;
            threadPool.submit(() -> System.out.println(j));
        }
    }
}

@FunctionalInterface
interface RejectPolicy<T> {
    void reject(TaskQueue<T> queue, T task);
}

class ThreadPool {
    // 任务队列
    private TaskQueue<Runnable> taskQueue;

    // 线程集合
    private HashSet<Worker> workers = new HashSet<>();

    // 核心线程数
    private  int coreSize;



    // 获取任务的超时时间
    private long timeout;

    private TimeUnit timeUnit;

    private RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(TaskQueue<Runnable> taskQueue, int coreSize, long timeout, TimeUnit timeUnit, RejectPolicy<Runnable> rejectPolicy) {
        this.taskQueue = taskQueue;
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectPolicy = rejectPolicy;
    }
    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            // 执行任务
            // 1）当task不为空，执行任务
            // 2）当task执行完毕，再接着从任务队列获取任务并执行
            while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }

            synchronized (workers) {
                workers.remove(this);
            }
        }
    }

    public void submit(Runnable task) {
        synchronized (workers) {
            // 当任务数没有超过coreSize时，直接交给worker对象执行
            // 如果任务数超过了coreSize时，加入任务队列暂存
            if (workers.size() <= coreSize) {
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
            } else {
                //taskQueue.put(task);
                // 1)死等
                // 2）带超时等待
                // 3）让调用者放弃任务执行
                // 4）让调用者抛出异常
                // 5）让调用者自己执行任务
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }
}


class TaskQueue<T> {
    // 1.任务队列
    private Deque<T> deque = new ArrayDeque<>();
    // 2. 容量
    private int capcity;

    // 3.锁
    private ReentrantLock lock = new ReentrantLock();

    // 4.任务队列为空的条件变量
    private Condition empty = lock.newCondition();

    // 5.任务队列满了的条件变量
    private Condition full = lock.newCondition();

    public TaskQueue(int capcity) {
        this.capcity = capcity;
    }

    // 非阻塞获取
    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            //将timeout 统一转换为纳秒
            long nanos = unit.toNanos(timeout);
            while (deque.isEmpty()) {
                try {
                    // 返回的是剩余时间
                    if (nanos <= 0) {
                        return null;
                    }

                    nanos = empty.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            T t = deque.removeFirst();
            full.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }


    // 阻塞获取
    public T take() {
        lock.lock();
        try {
            while (deque.isEmpty()) {
                try {
                    empty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            T t = deque.removeFirst();
            return t;
        } finally {
            lock.unlock();
        }
    }


    // 阻塞添加
    public void put(T element) {
        lock.lock();
        try {
            while (deque.size() == capcity) {
                try {
                    full.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            deque.addLast(element);
            empty.signal();
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            // 判断队列是否已满
            if (deque.size() == capcity) {
                rejectPolicy.reject(this, task);
            } else {
                // 有空闲
                deque.addLast(task);
                empty.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
