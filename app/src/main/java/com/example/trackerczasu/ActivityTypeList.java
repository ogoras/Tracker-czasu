package com.example.trackerczasu;

import java.io.Serializable;
import java.util.ArrayList;

public class ActivityTypeList implements Serializable { //list containing objects of type ActivityType and its size
    public int size;

    public ArrayList<ActivityType> ActivityTypes;

    public ActivityTypeList () {
        ActivityTypes = new ArrayList<ActivityType>();
        size = 0;
    }

    public void addType(ActivityType A){
        if ( findType(A.name) == null) {
            ActivityTypes.add(A);
            size++;
        }
    }

    public void deleteType(ActivityType A) {
        if(ActivityTypes.remove(A))
            size--;
    }

    public ActivityType findType(String name) { //checks if activity of name exists in ActivityTypes list
        for (ActivityType A : ActivityTypes) {
            if (A.name == name)
                return A;
        }
        return null;
    }

    public ActivityType get(int position) {
        ActivityType currentItem = ActivityTypes.get(position);
        return currentItem;
    }
}
