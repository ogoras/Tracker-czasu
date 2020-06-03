package com.example.trackerczasu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SplitActivityTest {

    @Test(expected = IllegalArgumentException.class)
    public void splitActivity() {
        UserActivities userActivities = new UserActivities();
        TActivity tActivity = new TActivity("ble", 37100, 37200);


        userActivities.addActivity(tActivity);
        userActivities.addActivity(userActivities.splitActivity(userActivities.list.get(0), 37110));
        assertNotNull(userActivities.list.get(1));
        //System.out.println("List contains " + userActivities.size + " activities");
        userActivities.splitActivity(userActivities.list.get(0), 37200);
        userActivities.splitActivity(userActivities.list.get(0), 37100);


    }
}