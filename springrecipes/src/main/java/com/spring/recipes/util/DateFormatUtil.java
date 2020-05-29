package com.spring.recipes.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    public static String formatDate(Date date, String formatType){
       DateFormat dateFormat =  new SimpleDateFormat(formatType);
       return dateFormat.format(date);
    }

    public static Date parseStr(String strDate, String formatType) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(formatType);
        return dateFormat.parse(strDate);
    }
}
