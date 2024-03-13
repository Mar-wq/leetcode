package com.hdy.aboutTime.jdk8Time;

import java.time.Instant;

public class InStantDemo {
    public static void main(String[] args) {
        // 获取标准时间的Instant对象   （标准时间）
        Instant now = Instant.now();
        System.out.println(now);


        // 根据（秒/毫秒/纳秒） 获取Instant对象
        Instant instant = Instant.ofEpochMilli(3L);
        System.out.println(instant);


        // 比较两个instant
        System.out.println(instant.isAfter(now));


        //   注意这里产生的对象是新的    Instant对象实例都是不可变的
        Instant instant1 = Instant.ofEpochMilli(3000L);
        Instant instant2 = instant1.minusMillis(1000L);
    }
}
