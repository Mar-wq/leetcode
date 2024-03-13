package com.hdy.toPlay.annomousClassUseLocalVarible;

public class Test {
    public static void main(String[] args) {
        Runnable runnable = createRunnable();
        runnable.run();
    }

    private static Runnable createRunnable() {
        final int a = 10;
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(a);
            }
        };
    }
}
