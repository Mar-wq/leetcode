package com.hdy.aboutTime;

import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(1, 3);
        Date time = calendar.getTime();
        System.out.println(time);
    }
}
