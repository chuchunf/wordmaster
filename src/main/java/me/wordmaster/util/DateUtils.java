package me.wordmaster.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {
    private DateUtils() {
    }

    public static String toYYYYMMDD(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(date);
    }

    public static String nowAsYYYYMMDD() {
        return toYYYYMMDD(new Date());
    }
}
