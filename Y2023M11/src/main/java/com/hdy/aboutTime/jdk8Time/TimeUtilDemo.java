package com.hdy.aboutTime.jdk8Time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeUtilDemo {
    // Duration

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        LocalDate birth = LocalDate.of(1997, 8, 2);

        Duration du = Duration.between(birth, now);
        long l = du.toDays();
        System.out.println(l);
    }
}
