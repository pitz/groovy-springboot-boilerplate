package com.pitzdev.groovyboot.utils

import java.text.SimpleDateFormat

class CustomDateUtils {

    public static final DATABASE_DATE_FORMAT = "yyyy-MM-dd"
    public static final DATABASE_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss"

    public static Date fromString(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(CustomDateUtils.DATABASE_DATE_FORMAT)
            return sdf.parse(date)
        } catch (Exception exception) {
            return null
        }
    }

    public static Date toDate(date) {
        if (date instanceof Date) return date
        return CustomDateUtils.fromString(date)
    }

}
