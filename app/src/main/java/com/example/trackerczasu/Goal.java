package com.example.trackerczasu;

public class Goal {
    public String nameOfGoal = " ";
    public String comment;
    public boolean isAchieved = false;
    private boolean isPeriodic;
    private long creationTime;
    public int refersToTwoTypes = 0; /* 0 - the goal is the percentage of the total time tracked
                                     ** 1 - the goal is for a duration of a type to be <x> times higher than duration of another type */
    public float typesRatio = 0;
    // public long timeSpan;
    public String nameOfActivityType;
    public String nameOfSecondActivityType;
    public ActivityType targetType; //changed to single type instead of an array (no sense)


    public Goal()
    {
        creationTime = System.currentTimeMillis()/1000;
    }
   /* public Goal(String activityTypeName, float ratio) //for some reason this breaks the recyclerview
    {
        creationTime = System.currentTimeMillis()/1000;
        this.nameOfActivityType = activityTypeName;
        this.typesRatio = ratio;
    } */
    public Goal(String name, String activityTypeName, float ratio)
    {
        this.nameOfGoal =  name;
        creationTime = System.currentTimeMillis()/1000;
        this.nameOfActivityType = activityTypeName;
        this.typesRatio = ratio;
    }
    public Goal (String name, String activityTypeName, String secondActivityTypeName, float ratio)
    {
        this.nameOfGoal =  name;
        creationTime = System.currentTimeMillis()/1000;
        this.nameOfActivityType = activityTypeName;
        this.nameOfSecondActivityType = secondActivityTypeName;
        this.refersToTwoTypes = 1;
        this.typesRatio = ratio;
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
