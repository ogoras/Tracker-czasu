package com.example.trackerczasu;

public class TimeFormat {
    public static String HourAndMinutePL(long time){
        long durationInMillis = time * 1000 + 7200000; //na razie działa tylko dla czasu letniego w Polsce
        long minute = (durationInMillis / (1000 * 60)) % 60;
        long hour = (durationInMillis / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d", hour, minute);
    }
    public static String HourAndMinute(long time){
        long durationInMillis = time * 1000;
        long minute = (durationInMillis / (1000 * 60)) % 60;
        long hour = (durationInMillis / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d", hour, minute);
    }
    public static String HourAndMinuteAndSecond(long time){
        long durationInMillis = time * 1000;
        long second = (durationInMillis/1000) % 60;
        long minute = (durationInMillis / (1000 * 60)) % 60;
        long hour = (durationInMillis / (1000 * 60 * 60)) % 24;
        if (hour==0)
            return String.format("%02d:%02d", minute, second);
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
