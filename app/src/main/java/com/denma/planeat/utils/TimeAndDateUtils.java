package com.denma.planeat.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeAndDateUtils {

    public static Date getDateWithGapFromToday(int gap){
        // get today date
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        // add the gap to today (0 = today 1 = tomorrow)
        calendar.add(Calendar.DATE, gap);
        date = calendar.getTime();

        return date;
    }

    public static int formatDateToInt_yyyyMMdd(Date date){
        // format to int
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMdd");
        int dateToReturn = Integer.parseInt(dateFormat.format(date));
        return dateToReturn;
    }

    public static String formatDateToString_EEEdd(Date date){
        // format to String (example : MAR05)
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEdd");
        return dateFormat.format(date);
    }

}
