package Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// thread not safe ： wrong code
public class DateUtil {

    private static final  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static  String formatDate(Date date)throws ParseException{
        return sdf.format(date);
    }

    public static Date parse(String strDate) throws ParseException{

        return sdf.parse(strDate);
    }

    public static void main(String[] args) throws ParseException {
        Date month = new Date();
        System.out.println(month.getTime());
        System.out.println(System.currentTimeMillis());

    }

    
}