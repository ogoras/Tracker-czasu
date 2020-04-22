package com.example.trackerczasu;

import java.util.ArrayList;
import java.util.List;

public class UserActivities {
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

    public void endActivity(TActivity ActivityToEnd) //to samo, co niżej - po czym identyfikujemy TActivity - id, nazwa czy obiekt ma być przekazywany
    {
        ActivityToEnd.isCurrent = false;
        ActivityToEnd.endTime = System.currentTimeMillis()/1000;
    }

    public TActivity getCurrentActivity() //tak jak wyzej
    {
        for (TActivity A : List) {
            if (A.isCurrent = true)
                return A;
        }
        return null;
    }

    public void deleteActivity(TActivity ActivityToDelete) //tak jak wyzej
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
