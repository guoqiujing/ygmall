package cn.myzqu.ygmall.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 的川 on 2018/7/17.
 */
public class DateUtil {

    public static Date strToDateLong(String strDate) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           ParsePosition pos = new ParsePosition(0);
           Date strtodate = formatter.parse(strDate, pos);
           return strtodate;
    }

}
