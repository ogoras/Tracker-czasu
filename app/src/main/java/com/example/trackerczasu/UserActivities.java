package com.example.trackerczasu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserActivities implements Serializable {
    public int size;
    public List<TActivity> List;

    UserActivities () {
        List = new ArrayList<TActivity>();
        size = 0;
    }

    public void addActivity(TActivity A)
    {
        List.add(A);
        size++;
    }

    public void endActivity(TActivity ActivityToEnd)
    {
        ActivityToEnd.isCurrent = false;
        ActivityToEnd.endTime = System.currentTimeMillis()/1000;
    }

    public TActivity getCurrentActivity()
    {
        for (TActivity A : List) {
            if (A.isCurrent == true)
                return A;
        }
        return null;
    }

    public void deleteActivity(TActivity ActivityToDelete)
    {
        if (List.remove(ActivityToDelete))
            size--;
    }

    public TActivity splitActivity(TActivity ActivityToSplit, long splitTime) // zwraca pierwszą połowę, a drugą połowę przypisuje do ActivityToSplit
    {
        if (splitTime <= ActivityToSplit.startTime || splitTime >= ( ActivityToSplit.isCurrent ? System.currentTimeMillis()/1000 : ActivityToSplit.endTime ) )
            throw new IllegalArgumentException("Activity split time " + splitTime + " out of activity bounds");
        else {
            TActivity FirstHalf = new TActivity(ActivityToSplit.type, ActivityToSplit.startTime, splitTime);
            ActivityToSplit.startTime = splitTime;
            return FirstHalf;
        }
    }
}
