package com.example.demo.util.dateUtile;


import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Author longtao
 * @Date 2020/10/26
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

    /**
     * @Author longtao
     * @Date 2020/10/26
     * @Describe 获取当前日期
     **/
    public static Date getNowTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * @Author longtao
     * @Date 2020/10/26
     * @Describe 获取N天后的日期   N可以为负数（N天前）
     **/
    public static Date getBeforeDaysTime(int beforeDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, beforeDay);
        return calendar.getTime();
    }

    /**
     * @Author longtao
     * @Date 2020/10/26
     * @Describe 获取N月后的日期   N可以为负数（N天前）
     **/
    public static Date getBeforeMonthsTime(int beforeMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, beforeMonth);
        return calendar.getTime();
    }
    /**
     * @Author longtao
     * @Date   2020/10/28
     * @Describe 获取两个日期之间的年数
     **/
    public static int getIntervalYear(Date startDate, Date endDate){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(endDate);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(startDate);

        int endYear = calendar1.get(Calendar.YEAR);
        int startYear = calendar2.get(Calendar.YEAR);

        return endYear-startYear;

    }

    /**
     * @Author longtao
     * @Date 2020/10/27
     * @Describe 获取两个日期之间的月份数
     **/
    public static int getIntervalMonth(Date startDate, Date endDate) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(endDate);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(startDate);
        //获取日期在本年的天数
        int endMonths = calendar1.get(Calendar.MONTH);
        int startMonths = calendar2.get(Calendar.MONTH);

        return endMonths - startMonths;
    }


    /**
     * @Author longtao
     * @Date 2020/10/27
     * @Describe 获取两个日期之间的天数
     **/
    public static int getIntervalDays(Date startDate, Date endDate) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(endDate);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(startDate);
        //获取日期在本年的天数
        int endDays = calendar1.get(Calendar.DAY_OF_YEAR);
        int startDays = calendar2.get(Calendar.DAY_OF_YEAR);

        return endDays - startDays;
    }

    /**
     * @Author longtao
     * @Date 2020/10/27
     * @Describe 获取两个日期之间的小时数
     **/
    public static int getIntervalHours(Date startDate, Date endDate) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(endDate);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(startDate);
        //获取日期在本年的天数
        int endDays = calendar1.get(Calendar.DAY_OF_YEAR);
        int startDays = calendar2.get(Calendar.DAY_OF_YEAR);
        //获取时间在当天的小时数
        int endhours = calendar1.get(Calendar.HOUR_OF_DAY);
        int starthours = calendar2.get(Calendar.HOUR_OF_DAY);

        return (endDays * 24 + endhours) - (startDays * 24 + starthours);
    }

    public static void main(String[] args) throws Exception {
        Date endDate = new Date();
        Thread.sleep(5000);
        Date startDate = getBeforeDaysTime(-5);
        int days = getIntervalDays(startDate, endDate);
        int hours = getIntervalHours(startDate, endDate);
        int months = getIntervalMonth(startDate, endDate);
        log.info("days = {}", days);
        log.info("hours = {}", hours);
        log.info("month = {}", months);
    }
}
