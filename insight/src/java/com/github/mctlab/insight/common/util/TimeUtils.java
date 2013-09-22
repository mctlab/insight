/** TimeUtils */
package com.github.mctlab.insight.common.util;

import java.util.Locale;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 时间工具
 */
public class TimeUtils {

    //-- public finals --//

    public static final long SECOND_MILLIS = 1000;
    public static final long MINUTE_MILLIS = SECOND_MILLIS * 60;
    public static final long HOUR_MILLIS = MINUTE_MILLIS * 60;
    public static final long DAY_MILLIS = HOUR_MILLIS * 24;

    public static final int DAY_OF_WEEK = 7;
    public static final int DAY_OF_MONTH = 30;
    public static final int DAY_OF_YEAR = 365;

    //-- private finals --//

    protected static final DateTimeFormatter simpleFormatter =
            DateTimeFormat.forPattern("yyyyMMdd HH:mm:ss").withLocale(Locale.CHINA);
    protected static final DateTimeFormatter simpleDateFormatter =
            DateTimeFormat.forPattern("yyyyMMdd").withLocale(Locale.CHINA);

    protected static final DateTimeFormatter cFormatter =
            DateTimeFormat.forPattern("yyyy年M月d日 HH:mm:ss").withLocale(Locale.CHINA);
    protected static final DateTimeFormatter cDateFormatter =
            DateTimeFormat.forPattern("yyyy年M月d日").withLocale(Locale.CHINA);

    protected static final DateTimeFormatter formatter =
            DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").withLocale(Locale.CHINA);
    protected static final DateTimeFormatter dateFormatter =
            DateTimeFormat.forPattern("yyyy-MM-dd").withLocale(Locale.CHINA);

    protected static final DateTimeFormatter timeFormatter =
            DateTimeFormat.forPattern("HH:mm:ss").withLocale(Locale.CHINA);

    //-- properties --//
    //-- constructors --//

    private static TimeUtils singleton = new TimeUtils();

    /**
     * 获得singleton
     */
    public static TimeUtils getInstance() {
        return singleton;
    }

    //-- destructors --//
    //-- implements --//
    //-- un-implements --//
    //-- methods --//
    //-- functions --//
    //-- utils --//

    /* date */

    /**
     * get string like 20080101
     */
    public static String getSimpleDate(long time) {
        return simpleDateFormatter.print(time);
    }

    /**
     * parse string like 20080101
     */
    public static long parseSimpleDate(String s) {
        return simpleDateFormatter.parseMillis(s);
    }

    /**
     * get string like 20080101 00:00:00
     */
    public static String getSimpleDateTime(long time) {
        return simpleFormatter.print(time);
    }

    /**
     * parse string like 20080101 00:00:00
     */
    public static long parseSimpleDateTime(String s) {
        return simpleFormatter.parseMillis(s);
    }

    /**
     * 获得今天的日期
     *
     * @see #getSimpleDate(long)
     */
    public static String getSimpleToday() {
        return getSimpleDate(System.currentTimeMillis());
    }

    /**
     * 获得昨天的日期
     *
     * @see #getSimpleDate(long)
     */
    public static String getSimpleYesterday() {
        return getSimpleDate(System.currentTimeMillis() - DAY_MILLIS);
    }

    /**
     * 获得明天的日期
     *
     * @see #getSimpleDate(long)
     */
    public static String getSimpleTomorrow() {
        return getSimpleDate(System.currentTimeMillis() + DAY_MILLIS);
    }

    /**
     * get string like 2008年1月1日
     */
    public static String getCDate(long time) {
        return cDateFormatter.print(time);
    }

    /**
     * parse string like 2008年1月1日
     */
    public static long parseCDate(String s) {
        return cDateFormatter.parseMillis(s);
    }

    /**
     * get string like 2008年1月1日 00:00:00
     */
    public static String getCDateTime(long time) {
        return cFormatter.print(time);
    }

    /**
     * parse string like 2008年1月1日 00:00:00
     */
    public static long parseCDateTime(String s) {
        return cFormatter.parseMillis(s);
    }

    /**
     * get string like 2008-01-01
     */
    public static String getDate(long time) {
        return dateFormatter.print(time);
    }

    /**
     * parse string like 2008-01-01
     */
    public static long parseDate(String s) {
        return dateFormatter.parseMillis(s);
    }

    /**
     * get string like 2008-01-01 00:00:00
     */
    public static String getDateTime(long time) {
        return formatter.print(time);
    }

    /**
     * parse string like 2008-01-01 00:00:00
     */
    public static long parseDateTime(String s) {
        return formatter.parseMillis(s);
    }

    /**
     * 获得今天的日期
     *
     * @see #getDate(long)
     */
    public static String getToday() {
        return getDate(System.currentTimeMillis());
    }

    /**
     * 获得今天0点的时间
     */
    public static long getTodayTime() {
        return parseDate(getToday());
    }

    /**
     * 获得昨天的日期
     *
     * @see #getDate(long)
     */
    public static String getYesterday() {
        return getDate(System.currentTimeMillis() - DAY_MILLIS);
    }

    /**
     * 获得明天的日期
     *
     * @see #getDate(long)
     */
    public static String getTomorrow() {
        return getDate(System.currentTimeMillis() + DAY_MILLIS);
    }

    /* time */

    /**
     * get string like 00:00:00
     */
    public static String getTime(long time) {
        return timeFormatter.print(time);
    }

    /* duration */

    /**
     * 计算当前时间和start之间的时间差
     */
    public static long getTimeCost(long start) {
        return getTimeCost(System.currentTimeMillis(), start);
    }

    /**
     * 计算end和start之间的时间差
     */
    public static long getTimeCost(long end, long start) {
        return end - start;
    }

    /**
     * 计算当前时间和start之间的时间差对应的字符串
     * <li>返回格式形如: 00:00:00</li>
     */
    public static String getTimeCostStr(long start) {
        return getTimeCostStr(System.currentTimeMillis(), start);
    }

    /**
     * 计算end和start之间的时间差对应的字符串
     * <li>返回格式形如: 00:00:00</li>
     */
    public static String getTimeCostStr(long end, long start) {
        long cost = getTimeCost(end, start);

        StringBuilder sb = new StringBuilder();
        long hour = cost / HOUR_MILLIS;
        sb.append(String.format("%02d:", hour));
        cost -= HOUR_MILLIS * hour;
        long minute = cost / MINUTE_MILLIS;
        sb.append(String.format("%02d:", minute));
        cost -= MINUTE_MILLIS * minute;
        long second = cost / SECOND_MILLIS;
        sb.append(String.format("%02d", second));

        return sb.toString();
    }

    /**
     * 计算当前时间和start之间的时间差对应的天数
     */
    public static int getDayCost(long start) {
        return getDayCost(System.currentTimeMillis(), start);
    }

    /**
     * 计算end和start之间的时间差对应的天数
     * <li>目前的算法是, 不足一天的按四舍五入计算</li>
     */
    public static int getDayCost(long end, long start) {
        long cost = getTimeCost(end, start);
        double day = (double) cost / DAY_MILLIS;
        return (int) Math.round(day);
    }

    //-- getters & setters --//
    //-- iWritables --//
    //-- inner classes --//
}
