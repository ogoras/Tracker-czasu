package com.example.trackerczasu;

public class Goal {
    public String name;
    public String comment;

    public boolean isAchieved;
    private boolean isPeriodic;


    private long creationTime;
    public long timeSpan;

  //  ActivityType [] targetTypes;

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

    public void addType()
    {

    }

    public void deleteType()
    {

    }

    public void editName()
    {

    }

    public void editPeriodic()
    {

    }

    public void editDuration()
    {

    }

    public void editComment()
    {

    }

}
