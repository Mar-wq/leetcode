package com.hdy.toPlay;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class Test02 {
    public static void main(String[] args) {
        Student1 stu = new Student1();

        AtomicReferenceFieldUpdater<Student1, String> updater = AtomicReferenceFieldUpdater.newUpdater(Student1.class, String.class, "name");
        updater.compareAndSet(stu, null, "z3");

        System.out.println(stu);
    }
}


class Student1 {
    volatile String name;



    @Override
    public String toString() {
        return "Student1{" +
                "name='" + name + '\'' +
                '}';
    }
}
