package com.hdy.conCurrence.pattern;

public class Test01 {
    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        new Thread(() -> {
            Object o = guardedObject.get();
        }).start();

        new Thread(() -> {
            guardedObject.complete(5);
        }).start();
    }
}





class GuardedObject {
    // 结果
    private Object response;

    public Object get() {
        synchronized (this) {
            while (response == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return response;
        }
    }

    public void complete(Object response) {
        synchronized (this) {
            // 给结果成员变量赋值
            this.response = response;
            this.notifyAll();
        }
    }
}
