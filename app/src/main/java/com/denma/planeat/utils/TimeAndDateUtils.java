package com.denma.planeat.utils;

import android.util.Log;

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

    public static String formatIntDateToStringToShow(int intDate){
        String stringDate = String.valueOf(intDate);
        String year = stringDate.substring(0, 4);
        String month = stringDate.substring(4, 6);
        String day = stringDate.substring(6);
        stringDate = day + "/" + month + "/" + year;
        return stringDate;
    }

    public static int formatStringDateToShowToIntToSave(String dateToShow){
        String day = dateToShow.substring(0,2);
        String month = dateToShow.substring(3, 5);
        String year = dateToShow.substring(6);
        int dateToSave = Integer.valueOf(year + month + day);

        return dateToSave;
    }

}
