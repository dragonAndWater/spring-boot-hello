package com.example.demo.util.dateUtile;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtil {
    private static final DateTimeFormatter DATE_IOS_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    private static final DateTimeFormatter DATE_IOS_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA);
    private static final DateTimeFormatter DATE_ID_TIME = DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.CHINA);


    public static String getNowTime() {
        return DATE_IOS_TIME.format(ZonedDateTime.now());
    }

    public static String getNowDate() {
        return DATE_IOS_DATE.format(ZonedDateTime.now());
    }

    public static String getIdTime() {
        return DATE_ID_TIME.format(ZonedDateTime.now());
    }

}
