package org.devops.core.utils.util;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 *
 * @author lsf
 */
@Slf4j
public class DateUtil {

    // 默认日期格式
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    // 默认时间格式
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    // excek时间格式
    public static final String EXCEL_DATETIME_FORMAT = "yyyy/MM/dd";

    public static final String NEW_EXCEL_DATETIME_FORMAT = "yyyy/MM/dd HH:mm:ss";

    // excek时间格式
    public static final String EXCEL_DATETIME_FORMAT_SHORT = "yyyy/MM";

    // 时分秒
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    // 日期格式化
    private static DateFormat DATE_FORMAT = null;
    // 时间格式化
    private static DateFormat DATE_TIME_FORMAT = null;
    private static DateFormat TIME_FORMAT = null;

    static {
        DATE_FORMAT = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        DATE_TIME_FORMAT = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        TIME_FORMAT = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
    }
    private static final ThreadLocal<DateFormat> DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<DateFormat> DATETIME_FORMAT_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<DateFormat> TIME_FORMAT_THREAD_LOCAL = new ThreadLocal<>();


    private static DateFormat defaultDateFormat() {
        DateFormat dateFormat = DATE_FORMAT_THREAD_LOCAL.get();
        if (dateFormat == null) {
            dateFormat = (DateFormat) DATE_FORMAT.clone();
            DATE_FORMAT_THREAD_LOCAL.set(dateFormat);
        }
        return dateFormat;
    }

    private static DateFormat defaultDateTimeFormat() {
        DateFormat dateFormat = DATETIME_FORMAT_THREAD_LOCAL.get();
        if (dateFormat == null) {
            dateFormat = (DateFormat) DATE_TIME_FORMAT.clone();
            DATETIME_FORMAT_THREAD_LOCAL.set(dateFormat);
        }
        return dateFormat;
    }

    private static DateFormat defaultTimeFormat() {
        DateFormat dateFormat = TIME_FORMAT_THREAD_LOCAL.get();
        if (dateFormat == null) {
            dateFormat = (DateFormat) TIME_FORMAT.clone();
            TIME_FORMAT_THREAD_LOCAL.set(dateFormat);
        }
        return dateFormat;
    }


    /**
     * @param date1
     * @param date2
     * @return
     * @Description 比较天数
     * @author chensongming
     * @date 2017年8月3日 下午11:37:55
     * @lastModifier
     */
    public static int compareDays(Date date1, Date date2) {
        date2 = getDayStart(date2);
        date1 = getDayStart(date1);
        if (date1 == null || date2 == null) return 0;
        return (int) ((date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000));
    }

    /**
     * 日期格式化yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static Date formatDate(String date, String format) {
        if (StringUtil.isEmpty(date) || StringUtil.isEmpty(format)) {
            return null;
        }

        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            log.error("[exception 出错啦]", e);
        }
        return null;
    }

    /**
     * 时间格式化
     *
     * @param date
     * @return
     */
    public static Date formatDateTime(String date) {
        if (date == null) {
            return null;
        }
        try {
            return defaultDateTimeFormat().parse(date);
        } catch (ParseException e) {
            log.error("[exception 出错啦]", e);
        }
        return null;
    }

    /**
     * 日期格式化yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getDateTimeFormat(Date date) {
        if (date == null) {
            return null;
        }
        return defaultDateTimeFormat().format(date);
    }

    /**
     * 日期格式化yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String getDateFormat(Date date) {
        if (date == null) {
            return null;
        }
        return defaultDateFormat().format(date);
    }



    /**
     * 时间格式化
     *
     * @param date
     * @return HH:mm:ss
     */
    public static String getTimeFormat(Date date) {
        if (date == null) {
            return null;
        }
        return defaultTimeFormat().format(date);
    }

    /**
     * 日期格式化
     *
     * @param date
     * @param formatStr
     * @return
     */
    public static String getDateFormat(Date date, String formatStr) {
        if (date == null || StringUtil.isEmpty(formatStr)) {
            return null;
        }
        if (StringUtil.isNotEmpty(formatStr)) {
            return new SimpleDateFormat(formatStr).format(date);
        }
        return null;
    }

    /**
     * 日期格式化
     *
     * @param date
     * @return
     */
    public static Date formatDate(String date) {
        if (date == null) {
            return null;
        }
        try {
            return defaultDateFormat().parse(date);
        } catch (ParseException e) {
            log.error("[exception 出错啦]", e);
        }
        return null;
    }

    /**
     * 获取当前日期星期一日期
     *
     * @return date
     */
    public static Date getFirstDayOfWeek() {
        Calendar gregorianCalendar = Calendar.getInstance();
        gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek()); // Monday
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当前日期星期日日期
     *
     * @return date
     */
    public static Date getLastDayOfWeek() {
        Calendar gregorianCalendar = Calendar.getInstance();
        gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek() + 6); // Monday
        return gregorianCalendar.getTime();
    }

    /**
     * 获取日期星期一日期
     *
     * @param date
     * @return date
     */
    public static Date getFirstDayOfWeek(Date date) {
        if (date == null) {
            return null;
        }
        Calendar gregorianCalendar = Calendar.getInstance();
        gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek()); // Monday
        return gregorianCalendar.getTime();
    }

    /**
     * 获取日期星期日日期
     *
     * @param date
     * @return date
     */
    public static Date getLastDayOfWeek(Date date) {
        if (date == null) {
            return null;
        }
        Calendar gregorianCalendar = Calendar.getInstance();
        gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek() + 6); // Monday
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当前月的第一天
     *
     * @return date
     */
    public static Date getFirstDayOfMonth() {
        Calendar gregorianCalendar = Calendar.getInstance();
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当前月的最后一天
     *
     * @return
     */
    public static Date getLastDayOfMonth() {
        Calendar gregorianCalendar = Calendar.getInstance();
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        gregorianCalendar.add(Calendar.MONTH, 1);
        gregorianCalendar.add(Calendar.DAY_OF_MONTH, -1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取指定月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar gregorianCalendar = Calendar.getInstance();
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取指定月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        if (date == null) {
            return null;
        }
        Calendar gregorianCalendar = Calendar.getInstance();
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        gregorianCalendar.add(Calendar.MONTH, 1);
        gregorianCalendar.add(Calendar.DAY_OF_MONTH, -1);
        return gregorianCalendar.getTime();
    }

    /**
     * @return
     * @Description 获取当年第一天 00:00:00
     * @author xhz
     * @date 2017年4月26日 上午9:58:24
     * @lastModifier
     */
    public static Date getCurrYearFirstDay() {
        return getYearFirstDay(new Date());
    }

    /**
     * @param date
     * @return
     * @Description 获取指定年第一天 00:00:00
     * @author xhz
     * @date 2017年4月26日 上午10:01:49
     * @lastModifier
     */
    public static Date getYearFirstDay(Date date) {
        Calendar gregorianCalendar = Calendar.getInstance();
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.MONTH, 1);
        gregorianCalendar.set(Calendar.HOUR, 0);
        gregorianCalendar.set(Calendar.MINUTE, 0);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        gregorianCalendar.set(Calendar.SECOND, 0);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取日期前一天
     *
     * @param date
     * @return
     */
    public static Date getDayBefore(Date date) {
        if (date == null) {
            return null;
        }
        Calendar gregorianCalendar = Calendar.getInstance();
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(Calendar.DATE);
        gregorianCalendar.set(Calendar.DATE, day - 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取日期后一天
     *
     * @param date
     * @return
     */
    public static Date getDayAfter(Date date) {
        if (date == null) {
            return null;
        }
        Calendar gregorianCalendar = Calendar.getInstance();
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(Calendar.DATE);
        gregorianCalendar.set(Calendar.DATE, day + 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当前年
     *
     * @return
     */
    public static int getNowYear() {
        Calendar d = Calendar.getInstance();
        return d.get(Calendar.YEAR);
    }

    /**
     * @param date
     * @return
     * @Description 获取年
     * @author xhz
     * @date 2017年4月26日 下午2:53:04
     * @lastModifier
     */
    public static int getYear(Date date) {
        Calendar d = Calendar.getInstance();
        d.setTime(date);
        return d.get(Calendar.YEAR);
    }

    /**
     * @param date
     * @return
     * @Description 获取月份
     * @author xhz
     * @date 2017年4月26日 下午2:53:04
     * @lastModifier
     */
    public static int getMonth(Date date) {
        Calendar d = Calendar.getInstance();
        d.setTime(date);
        return d.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getNowMonth() {
        Calendar d = Calendar.getInstance();
        return d.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getMinute(Date date) {
        Calendar d = Calendar.getInstance();
        d.setTime(date);
        return d.get(Calendar.MINUTE);
    }

    /**
     * 获取当月的第几天
     *
     * @return
     */
    public static int getNowMonDay() {
        Calendar d = Calendar.getInstance();
        return d.get(Calendar.DATE);
    }

    /**
     * 获取当月天数
     *
     * @return
     */
    public static int getNowMonthDay() {
        Calendar d = Calendar.getInstance();
        return d.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取时间段的每一天
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 日期列表
     */
    public static List<Date> getEveryDay(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        Calendar gregorianCalendar = Calendar.getInstance();
        // 格式化日期(yy-MM-dd)
        startDate = getDayStart(startDate);
        endDate = getDayStart(endDate);
        List<Date> dates = new ArrayList<Date>();
        gregorianCalendar.setTime(startDate);
        dates.add(gregorianCalendar.getTime());
        while (gregorianCalendar.getTime().compareTo(endDate) < 0) {
            // 加1天
            gregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
            dates.add(gregorianCalendar.getTime());
        }
        return dates;
    }

    /**
     * 获取提前多少个月
     *
     * @param monty
     * @return
     */
    public static Date getFirstMonth(int monty) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -monty);
        return c.getTime();
    }

    /**
     * 增加时间（秒）
     *
     * @param date
     * @param seconds
     * @return
     */
    public static Date addSeconds(Date date, int seconds) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, seconds);
        return c.getTime();
    }

    /**
     * 增加时间（分）
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinutes(Date date, int minutes) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minutes);
        return c.getTime();
    }

    /**
     * 增加时间（时）
     *
     * @param date
     * @param hours
     * @return
     */
    public static Date addHours(Date date, int hours) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hours);
        return c.getTime();
    }


    /**
     * 增加时间（日）
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDay(Date date, int day) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, day);
        return c.getTime();
    }

    /**
     * 增加时间（月）
     *
     * @param date
     * @param month
     * @return
     */
    public static Date addMonth(Date date, int month) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }

    /**
     * @param dateA
     * @param dateB
     * @return
     * @Description 比较2个日期的大小
     * @author xhz
     * @date 2017年4月28日 下午4:36:26
     * @lastModifier
     */
    public static int compare(Date dateA, Date dateB) {
        Calendar calendarA = Calendar.getInstance();
        calendarA.setTime(dateA);
        Calendar calendarB = Calendar.getInstance();
        calendarB.setTime(dateB);
        return calendarA.compareTo(calendarB);
    }

    public static float compareHours(Date date1, Date date2) {
        long nd = 1000 * 24 * 60 * 60L;
        long nh = 1000 * 60 * 60L;
        long nm = 1000 * 60L;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = date1.getTime() - date2.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day * 24 + hour + Float.valueOf(min) / 60;
    }

    /**
     * 获取日期前一天的开始时间
     *
     * @param date
     * @param before(天数)
     * @return
     */
    public static Date getDayBeforeBegin(Date date, int before) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - before);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }


    /**
     * 获取日期前一天的结束时间
     *
     * @param date
     * @param before(天数)
     * @return
     */
    public static Date getDayBeforeEnd(Date date, int before) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - before);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取日期当天的开始时间
     *
     * @param date
     * @return
     */
    public static Date getDayStart(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取日期当天的结束时间
     *
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
}
