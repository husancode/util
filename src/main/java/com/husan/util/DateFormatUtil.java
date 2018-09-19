package com.husan.util;

import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * <p>
 *     date格式化，format使用FastDateFormat
 *     或者使用joda-time包的DateTimeFormat和ISODateTimeFormat,
 *     ISO格式时间（国际化涉及时区问题）可以使用joda-time
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/8/14
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class DateFormatUtil {

    public static final String STAND_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final FastDateFormat standFastFormat = FastDateFormat.getInstance(STAND_FORMAT);

    public static final String ISO_8601_Date = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static final TimeZone UTC =  TimeZone.getTimeZone("UTC");

    public static final TimeZone GMT_8 = TimeZone.getTimeZone("GMT+8:00");

    public static String standFastFormat(Date date){
        return standFastFormat.format(date);
    }

    public static String fastFormat(Date date, String pattern){
        return FastDateFormat.getInstance(pattern, TimeZone.getDefault()).format(date);
    }

    public static String fastFormat(Date date, String pattern, TimeZone timeZone){
        return FastDateFormat.getInstance(pattern, timeZone).format(date);
    }

    public static String fastFormat(Date date, String pattern, Locale locale){
        return FastDateFormat.getInstance(pattern, locale).format(date);
    }

    public static String format(Date date, String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static Date parse(String dateStr, String pattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.parse(dateStr);
    }

    public static Date parse(String dateStr, String pattern, TimeZone timeZone) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(timeZone);
        return format.parse(dateStr);
    }

    public static void main(String[] args) throws ParseException {
        Date now = new Date();
        System.out.println(now.getTime());
        String nowStr = fastFormat(now, ISO_8601_Date, UTC);
        System.out.println(nowStr);
        Date hel = parse(nowStr, ISO_8601_Date, UTC);
        System.out.println(hel.getTime());
        System.out.println(hel.getTime() - now.getTime());
        System.out.println(hel);
    }

    /*private static final DateTimeZone GMT = new FixedDateTimeZone("GMT", "GMT", 0, 0);
    private static DateTimeFormatter dayFormatter = DateTimeFormat.forPattern("yyyyMMdd").withZone(GMT);
    private static DateTimeFormatter secondFormatter = DateTimeFormat.forPattern("yyyyMMdd'T'HHmmss'Z'").withZone(GMT);*/


}
