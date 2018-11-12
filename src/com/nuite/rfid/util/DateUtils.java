package com.nuite.rfid.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期时间转换工具
 *
 * @Author: yangchuang
 * @Date: 2018/11/8 15:57
 * @Version: 1.0
 */

public class DateUtils {

    private static final String DATE_TIME_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_TIME_MILLI = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String DATE_TIME_CN = "yyyy年MM月dd日 HH时mm分ss秒";

    public static Instant date2Instant(Date date) {
        return date.toInstant();
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime2Instant(localDateTime));
    }

    public static Instant localDateTime2Instant(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    public static Date instant2Date(Instant instant) {
        return Date.from(instant);
    }

    public static LocalDateTime instant2LocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static String format(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_MILLI);
        return localDateTime.format(formatter);
    }

    public static String format(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    public static String format(Instant instant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_MILLI);
        LocalDateTime localDateTime = instant2LocalDateTime(instant);
        return localDateTime.format(formatter);
    }

    public static String format(Instant instant, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = instant2LocalDateTime(instant);
        return localDateTime.format(formatter);
    }

    public static String format(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_MILLI);
        return formatter.format(date);
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static LocalDateTime parseToLocalDateTime(String datetime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(datetime, formatter);
    }

    public static Date parseToDate(String datetime, String pattern) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.parse(datetime);
    }

    public static Instant timestamp2Instant(long timestamp) {
        return Instant.ofEpochMilli(timestamp);
    }

    public static LocalDateTime timestamp2LocalDateTime(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    public static long localDateTime2Timestamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }



}
