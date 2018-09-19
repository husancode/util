package com.husan.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 *     日期加减，截断
 *     日期四舍五入可以参考commons-lang3的DateUtils方法
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/8/9
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class DateUtil {

    /**
     * 日期加减
     * @param srcDate
     * @param amount
     * @param field  : Calendar 的日期常量标识
     * @return
     */
    public static Date dateAdd(Date srcDate, int amount, int field){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(srcDate);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 将日期按秒加减
     * @param srcDate
     * @param amount
     * @return
     */
    public static Date addBySecond(Date srcDate, int amount){
        return dateAdd(srcDate, amount, Calendar.SECOND);
    }

    /**
     * 将日期按小时加减
     * @param srcDate
     * @param amount
     * @return
     */
    public static Date addByHour(Date srcDate, int amount){
        return dateAdd(srcDate, amount, Calendar.HOUR_OF_DAY);
    }

    public static Date addByDay(Date srcDate, int amount){
        return dateAdd(srcDate, amount, Calendar.DAY_OF_MONTH);
    }

    public static Date addByMonth(Date srcDate, int amount){
        return dateAdd(srcDate, amount, Calendar.MONTH);
    }

    public static Date addByYear(Date srcDate, int amount){
        return dateAdd(srcDate, amount, Calendar.YEAR);
    }

    public static Date truncateByFormat(Date srcDate, String format) throws ParseException {
        return DateFormatUtil.parse(DateFormatUtil.fastFormat(srcDate, format), format);
    }

    public static Date truncateByField(Date srcDate, int field){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(srcDate);
        calendar.set(field, 0);
        return calendar.getTime();
    }

    public static boolean isSameDay(Date date1, Date date2){
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    public static void main(String[] args) throws ParseException {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal);
        int cd = cal.get(Calendar.ERA);
        System.out.println(cd);
        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.DAY_OF_YEAR));

    }


}
