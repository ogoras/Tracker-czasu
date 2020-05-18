package com.example.trackerczasu;

import java.io.Serializable;

public class ActivityType implements Serializable {

    public String name;
    public boolean hasColor = false;
    private int color;
    public int icon;

    public ActivityType (String name, int icon){
        this.name = name;
        this.icon = icon;
}

    public ActivityType (String name, int color, int icon) {
        this.name = name;
        this.color = color;
        hasColor = true;
        this.icon = icon;
    }

    public int getColor() {
        if (hasColor)
            return color;
        else throw new IllegalStateException("typ nie ma koloru");
    }

    public String getName(){
        return name;
    }

    public int getIcon(){
        return icon;
    }

    public void changeColor(int color) {
        this.color = color;
        hasColor = true;
    }


    public void changeName(String name)
    {
        this.name = name;
    }

    public long getTotalDuration(UserActivities ActivityList) {
        long sum = 0;
        for (TActivity A : ActivityList.list) {
            if (A.type == this.name)
                sum += A.getDuration();
        }
        return sum;
    }

    public long getTotalDuration(UserActivities ActivityList, long startTime, long endTime)
    {
        if (endTime <= startTime)
            throw new IllegalArgumentException("End time must be bigger than start time");
        long sum = 0;
        for (TActivity A : ActivityList.list) {
            if (A.type == this.name) {
                if (startTime <= A.startTime) {
                    if (endTime >= A.endTime)
                        sum += A.getDuration();
                    else if (endTime > A.startTime)
                        sum += (endTime - A.startTime);
                }
                else if (startTime < A.endTime) {
                    if (endTime >= A.endTime)
                        sum += (A.endTime - startTime);
                    else
                        sum += (endTime - startTime);
                }
            }
        }
        return sum;
    }
}
