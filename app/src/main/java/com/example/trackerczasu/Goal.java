package com.example.trackerczasu;

public class Goal {
    public String name;
    public String comment;
    public boolean isAchieved = false;
    private boolean isPeriodic;
    private long creationTime;
    public long timeSpan;
    public ActivityType targetType; //changed to single type instead of an array (no sense)

    Goal()
    {
        creationTime = System.currentTimeMillis()/1000;
    }
    Goal (String name)
    {
        this.name =  name;
        creationTime = System.currentTimeMillis()/1000;
    }
    Goal (String name, String comment)
    {
        this.name =  name;
        this.comment = comment;
        creationTime = System.currentTimeMillis()/1000;
    }

    public void addType(ActivityType activityType)
    {
        this.targetType = activityType;
    }
    public void deleteType()
    {
        this.targetType = null;
    }
    public void editName(String name)
    {
        this.name = name;
    }
    public void editPeriodic(boolean isPeriodic)
    {
        this.isPeriodic = isPeriodic;
    }
    public void editDuration(long timeSpan)
    {
        this.timeSpan = timeSpan;
    }
    public void editComment(String comment)
    {
        this.comment = comment;
    }
    public boolean updateIsAchieved() // update variable to reflect the truth
    {
        ;
        return true;
    }

}
