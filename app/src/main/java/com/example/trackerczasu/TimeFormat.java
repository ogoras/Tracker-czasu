package com.example.trackerczasu;

import java.util.Calendar;
import java.util.Locale;

public class TimeFormat {
    public static String HourAndMinute(long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time*1000);
        return String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
    }
    public static String HMSDuration(long time){
        long durationInMillis = time * 1000;
        long second = (durationInMillis/1000) % 60;
        long minute = (durationInMillis / (1000 * 60)) % 60;
        long hour = (durationInMillis / (1000 * 60 * 60));
        if (hour==0)
            return String.format("%d:%02d", minute, second);
        return String.format("%d:%02d:%02d", hour, minute, second);
    }
    public static String DDMMYYYY_HHMM_Twolines(long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time*1000);
        return  String.format("%02d.%02d.%04d\n%02d:%02d",
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE));
    }
    public static int dayOfYear(long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time*1000);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }
    public static String dayAndMonth(long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time*1000);
        return  String.format("%02d %s", calendar.get(Calendar.DAY_OF_MONTH),
                calendar.getDisplayName(Calendar.MONTH,
                        Calendar.SHORT_FORMAT, Locale.getDefault()));
    }
}
