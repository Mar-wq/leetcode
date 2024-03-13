package com.hdy.toPlay;


import java.util.concurrent.atomic.AtomicReference;

public class Test01 {
    public static void main(String[] args) {
        Student s1 = new Student("z3", 44);

        // 创建原子引用包装类  设置主内存共享变量为s1
        AtomicReference<Student> studentAtomicReference = new AtomicReference<>(s1);

        while (true) {
            Student s2 = new Student("w5", 20);
            if (studentAtomicReference.compareAndSet(s1, s2)) {
                break;
            }
        }
    }
}




class MyObject {
    private int value;

    public MyObject(int value) {
        this.value = value;
    }

    public void doSomething() {
        System.out.println("Doing something with value: " + value);
    }
}


class Student {
    String name;
    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
