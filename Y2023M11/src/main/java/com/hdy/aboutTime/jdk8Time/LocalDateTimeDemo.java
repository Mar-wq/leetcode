package com.hdy.aboutTime.jdk8Time;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDemo {
    public static void main(String[] args) {


        // 类似与Calendar
        LocalDateTime ldt = LocalDateTime.of(2018, 5, 5, 8, 10, 10);
        String time = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss EE a"));
        System.out.println(time);
        Month month = ldt.getMonth();
        System.out.println(month.getValue());


        // with开头的方法表示修改，修改了则会产生一个新的LocalDateTime
        LocalDateTime ldt2 = ldt.withYear(2018);
        System.out.println(ldt2);

    }
}
