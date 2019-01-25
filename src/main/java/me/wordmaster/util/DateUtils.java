package me.wordmaster.util;

import java.text.SimpleDateFormat;
import java.util.Date;

final public class DateUtils {
    public static String toYYYYMMDD(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(date);
    }
}
