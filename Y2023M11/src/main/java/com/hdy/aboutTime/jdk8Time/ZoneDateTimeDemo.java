package com.hdy.aboutTime.jdk8Time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZoneDateTimeDemo {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);

        // 通过Instant + 时区的方式指定获取时间对象
        Instant instant = Instant.ofEpochMilli(0L);
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
        System.out.println(zonedDateTime);


        // 3.  withXXX 修改时间系列的方法
        ZonedDateTime zonedDateTime1 = zonedDateTime.withYear(2013);
        System.out.println(zonedDateTime1);
    }
}
