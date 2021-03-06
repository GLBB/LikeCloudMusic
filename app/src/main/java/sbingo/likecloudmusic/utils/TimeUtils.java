package sbingo.likecloudmusic.utils;

import android.net.ParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Author: Sbingo
 * Date:   2016/12/12
 */

public class TimeUtils {

    public static final SimpleDateFormat DETAIL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat SIMPLE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat CHINA_FORMAT_DATE = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);

    public static String dateToSimpleString(Date date) {
        return SIMPLE_FORMAT_DATE.format(date);
    }

    public static String dateToDetailString(Date date) {
        return DETAIL_DATE_FORMAT.format(date);
    }

    public static Date stringToDate(String dateStr) throws java.text.ParseException {
        SimpleDateFormat format = null;
        if (isChineseDateType(dateStr)) {
            format = CHINA_FORMAT_DATE;
        } else if (isDateType(dateStr)) {
            format = SIMPLE_FORMAT_DATE;
        } else {
            format = DETAIL_DATE_FORMAT;
        }
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 判断是否今天
     *
     * @param date
     * @return
     */
    public static boolean isToday(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        Date today = new Date();
        return dateToSimpleString(date).equals(dateToSimpleString(today)) ? true : false;
    }

    /**
     * 获取明天
     *
     * @return
     */
    public static String getTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);
        return dateToSimpleString(calendar.getTime());
    }


    /**
     * 是否符合日期格式yyyy-MM-dd
     *
     * @param sDate
     * @return
     */
    public static boolean isDateType(String sDate) {
        return sDate.matches("\\d{2,4}-\\d{1,2}-\\d{1,2}");
    }

    public static boolean isChineseDateType(String date) {
        return date.matches("\\d{2,4}年\\d{1,2}月\\d{1,2}日");
    }

    /**
     * @param leftTime
     * @return 时分秒01:25:58 or 时分12:26
     */
    public static String getLeftTime(long leftTime) {
        final long HOUR = 3600 * 1000;
        final long MIN = 60 * 1000;
        final long SEC = 1000;
        int hour, min, sec;
        if (leftTime / HOUR >= 1) { //小时差大于1小时
            hour = (int) (leftTime / HOUR);
            if (leftTime % HOUR / MIN >= 1) { //分差大于1分钟
                min = (int) (leftTime % HOUR / MIN);
            } else {
                min = 0;
            }
            sec = (int) (leftTime % HOUR % MIN / SEC);
        } else {
            hour = 0;
            if (leftTime % HOUR / MIN >= 1) { //分差大于1分钟
                min = (int) (leftTime % HOUR / MIN);
            } else {
                min = 0;
            }
            sec = (int) (leftTime % HOUR % MIN / SEC);
        }
        String h = hour + "";
        String m = min + "";
        String s = sec + "";
        if (h.length() != 2) {
            h = "0" + h;
        }
        if (m.length() != 2) {
            m = "0" + m;
        }
        if (s.length() != 2) {
            s = "0" + s;
        }
        if (hour > 0) {
            return h + ":" + m + ":" + s;
        } else {
            return m + ":" + s;
        }
    }

}
