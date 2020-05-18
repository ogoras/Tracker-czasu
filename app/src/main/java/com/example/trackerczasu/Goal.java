package com.example.trackerczasu;

public class Goal {
    public String nameOfGoal;
    public String comment;
    public boolean isAchieved = false;
    private boolean isPeriodic;
    private long creationTime;
    public boolean refersToTwoTypes = false; /* false - the goal is the percentage of the total time tracked
                                            ** true - the goal is for a duration of a type to be <x> times higher than duration of another type */
   // public long timeSpan;
    public String nameOfActivityType;
    public String nameOfSecondActivityType;
    public ActivityType targetType; //changed to single type instead of an array (no sense)

    Goal()
    {
        creationTime = System.currentTimeMillis()/1000;
    }
    Goal (String name, String activityTypeName)
    {
        this.nameOfGoal =  name;
        creationTime = System.currentTimeMillis()/1000;
        this.nameOfActivityType = activityTypeName;
    }
    Goal (String name, String activityTypeName, String secondActivityTypeName)
    {
        this.nameOfGoal =  name;
        creationTime = System.currentTimeMillis()/1000;
        this.nameOfActivityType = activityTypeName;
        this.nameOfSecondActivityType = secondActivityTypeName;
        this.refersToTwoTypes = true;
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
        this.nameOfGoal = name;
    }
 /*   public void editPeriodic(boolean isPeriodic)
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
    */
    //public boolean updateIsAchieved() // update variable to reflect the truth


}
