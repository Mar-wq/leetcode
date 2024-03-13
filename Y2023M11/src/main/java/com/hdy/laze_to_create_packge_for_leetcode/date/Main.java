package com.hdy.laze_to_create_packge_for_leetcode.date;

import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;
// 1:无需package
// 2: 类名必须Main, 不可修改

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        LocalDate start = LocalDate.of(1900, 1, 1);
        LocalDate end = LocalDate.of(10000, 1, 1);
        Solution solution = new Solution();
        int days = solution.getDays(start, end);
        System.out.println(days);

        scan.close();
    }
}


class Solution {
    public int getDays(LocalDate start, LocalDate end) {
        int cnt = 0;
        while (end.isAfter(start)) {
            System.out.println(start);
            if (check(start)) {
                cnt++;
            }
            start = start.plusDays(1);
        }

        return cnt;
    }

    private boolean check(LocalDate start) {
        int year = start.getYear();
        int yearSum = 0;
        while (year > 0) {
            yearSum += year % 10;
            year /= 10;
        }

        Month month = start.getMonth();
        int value = month.getValue();
        int sumDayAndMonth = 0;
        while (value > 0) {
            sumDayAndMonth += value % 10;
            value /= 10;
        }

        int dayOfMonth = start.getDayOfMonth();
        while (dayOfMonth > 0) {
            sumDayAndMonth += dayOfMonth % 10;
            dayOfMonth /= 10;
        }


        return yearSum == sumDayAndMonth;
    }
}
