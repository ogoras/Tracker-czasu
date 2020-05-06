package com.example.trackerczasu;

import java.util.ArrayList;
import java.util.List;

public class ActivityTypeList { //list containing objects of type ActivityType and its size
    public int size;

    public List<ActivityType> ActivityTypes;

    ActivityTypeList () {
        ActivityTypes = new ArrayList<ActivityType>();
        size = 0;
    }

    public void addType(ActivityType A)
    {
        ActivityTypes.add(A);
        size++;
    }

    public void deleteType(ActivityType A)
    {
        if(ActivityTypes.remove(A))
            size--;
    }

    public boolean findType(String name)  //checks if activity of name exists in ActivityTypes list
    {
        for (ActivityType A : ActivityTypes) {
            if (A.name == name)
                return true;
        }
        return false;
    }
}
