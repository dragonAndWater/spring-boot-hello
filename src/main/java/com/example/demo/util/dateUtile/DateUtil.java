package com.example.demo.util.dateUtile;


import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/**
 * @Author longtao
 * @Date   2020/10/26
 * @Describe 时间工具类
 **/
@Slf4j
public class DateUtil {
    private static final DateTimeFormatter DATE_IOS_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    private static final DateTimeFormatter DATE_IOS_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA);
    private static final DateTimeFormatter DATE_ID_TIME = DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.CHINA);



    public static String getNowDate() {
        return DATE_IOS_DATE.format(ZonedDateTime.now());
    }

    public static String getIdTime() {
        return DATE_ID_TIME.format(ZonedDateTime.now());
    }



    public static void main(String[] args) {
        log.info("nowTime = "+getNowTime());
        log.info("beforeTime = "+getBeforeMonthsTime(-2));
    }

    /**
     * @Author longtao
     * @Date   2020/10/26
     * @Describe 获取当前日期
     **/
    public static Date getNowTime(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * @Author longtao
     * @Date   2020/10/26
     * @Describe 获取N天后的日期   N可以为负数（N天前）
     **/
    public static Date getBeforeDaysTime(int beforeDay){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,beforeDay);
        return calendar.getTime();
    }

    /**
     * @Author longtao
     * @Date   2020/10/26
     * @Describe 获取N月后的日期   N可以为负数（N天前）
     **/
    public static Date getBeforeMonthsTime(int beforeMonth){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,beforeMonth);
        return calendar.getTime();
    }




}
