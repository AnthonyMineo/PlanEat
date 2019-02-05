package com.denma.planeat.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeAndDateUtils {

    public static String getDateWithGapFromToday(int gap){
        // get today date
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        // add the gap to today (0 = today 1 = tomorrow)
        calendar.add(Calendar.DATE, gap);
        date = calendar.getTime();
        // format to String (example : MAR05)
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEdd");
        return dateFormat.format(date);
    }

}
