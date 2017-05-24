package com.mmt.microlove.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Description 时间工具类
 * Created by wuqiuyun on 2017/5/16
 */
public class TimeUtil {

    private static String mYear;
    private static String mMonth;
    private static String mDay;
    private static String mWay;

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static SimpleDateFormat sf = null;

    /**
     * @Description 时间戳转换成字符串
     */
    public static String getDateToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    /**
     * @Description 将字符串转为时间戳
     */
    public static long getStringToDate(String str) {
        sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * @Description 获取当前时间
     */
    public static String getCurrentTime(int type) {
        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);//当前年
        int month = (cal.get(Calendar.MONTH)) + 1;//当前月
        int day_of_month = cal.get(Calendar.DAY_OF_MONTH);//当前月的第几天：即当前日
        int hour = cal.get(Calendar.HOUR_OF_DAY);//当前时：HOUR_OF_DAY-24小时制；HOUR-12小时制
        int minute = cal.get(Calendar.MINUTE);//当前分
        int second = cal.get(Calendar.SECOND);//当前秒
        int ampm = cal.get(Calendar.AM_PM);//0-上午；1-下午
        int week_of_year = cal.get(Calendar.WEEK_OF_YEAR);//当前年的第几周
        int week_of_month = cal.get(Calendar.WEEK_OF_MONTH);//当前月的第几周
        int day_of_year = cal.get(Calendar.DAY_OF_YEAR);//当前年的第几天

        if (type == 1) {
            //获取年-月-日
            return year + "-" + month + "-" + day_of_month;
        } else {
            return "";
        }
    }

    /**
     * @Description 判断给定字符串时间是否为今日
     */
    public static boolean isToday(String currentTime, String signTime) {
        boolean isToday = false;
        Date time = toDate(currentTime);
        Date today = toDate(signTime);
        if (time != null) {
            String nowDate = dateFormater2.get().format(today);
            String timeDate = dateFormater2.get().format(time);
            LogUtil.d(Constants.TAG, "nowDate:" + nowDate + "  timeDate:" + timeDate);
            if (nowDate.equals(timeDate)) {
                isToday = true;
            }
        }
        return isToday;
    }

    /**
     * @Description 将字符串转位日期类型
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * @Description String 转换 Date
     */
    public static Date stringToDate(String str, String format) {
        try {
            return new SimpleDateFormat(format).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String getStringData() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return mYear + "/" + mMonth + "/" + mDay + " 星期" + mWay;
    }
}