package com.hdy.aboutTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTimeClass {
    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年M月d日 HH:mm:ss");
/*        String format = simpleDateFormat.format(date);
        System.out.println(format);*/
        String time = "2018年6月7日 16:48:53";
        Date d1 = simpleDateFormat.parse(time);
        System.out.println(d1);
    }
}
