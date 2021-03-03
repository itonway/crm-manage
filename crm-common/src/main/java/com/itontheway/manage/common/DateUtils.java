package com.itontheway.manage.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/12 20:01
 */
public class DateUtils {
    public static final String YEAR_MONTH_DAY_HOUR_MINTE_SECOND1 = "yyyyMMddHHmmss";
    public static final String YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String YEAR_MONTH_DAY_HOUR_MINTE_SECOND = "yyyy-MM-dd HH:mm:ss";
    // 解决 simpleDateFormat 线程不安全问题
    private static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<> ();

    private static SimpleDateFormat getDateFormat() {
        SimpleDateFormat dateFormat = local.get ();
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );
            local.set ( dateFormat );
        }
        return dateFormat;
    }

    public static String formatLocal(Date date) {
        return getDateFormat ().format ( date );
    }

    public static Date parseLocal(String dateStr) throws ParseException {
        return getDateFormat ().parse ( dateStr );
    }


    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalDate now1 = LocalDate.now();
        System.out.println(now1);
    }

}
