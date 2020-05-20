package com.example.trackerczasu;

import java.io.Serializable;

public class TActivity implements Serializable, Cloneable {
    private int id;

    public boolean isCurrent;
    public String type;
    public String tag;
    public String comment;
    public long startTime; // in seconds
    public long endTime;

    TActivity(String type) {
        this.type = type;
        startTime = System.currentTimeMillis()/1000;
        isCurrent = true;
    }

    TActivity(String type, long startTime, long endTime) {
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        isCurrent = false;
    }

    public long getDuration() {
        return isCurrent ? System.currentTimeMillis()/1000 - startTime : endTime - startTime;
    }

    public boolean editType(ActivityTypeList List, String name){
        if(List.findType(name)!=null) {
            type = name;
            return true;
        }
        else
            return false;
    }

    public void editTag(String name){
        tag = name;
    }

    public void editComment(String name){
        comment = name;
    }

    public void editTime(long start, long end){
        startTime = start;
        endTime= end;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        TActivity t = new TActivity(type, startTime, endTime);
        t.isCurrent = isCurrent;
        t.type = type;
        t.tag = tag;
        t.comment = comment;
        return t;
    }
}
