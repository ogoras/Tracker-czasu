package com.example.trackerczasu;

import org.junit.Test;

import static org.junit.Assert.*;

public class InsertActivityTest {

    @Test (expected = IllegalArgumentException.class)
    public void insertActivity() {
        UserActivities userActivities = new UserActivities();
        TActivity tActivity = new TActivity("bcd", 37100, 37200);
        userActivities.addActivity(tActivity);
        tActivity = new TActivity("bcd", 37201, 37220);
        userActivities.addActivity(tActivity);

        userActivities.insertActivity("cde",37199,37200);
    }

    @Test
    public void insertActivity2() {
        UserActivities userActivities = new UserActivities();
        TActivity tActivity = new TActivity("bcd", 37100, 37200);
        userActivities.addActivity(tActivity);
        tActivity = new TActivity("bcd", 37201, 37220);
        userActivities.addActivity(tActivity);

        userActivities.insertActivity("cde",37200,37201);

    }
    @Test (expected = IllegalArgumentException.class)
    public void insertActivity3() {
        UserActivities userActivities = new UserActivities();
        TActivity tActivity = new TActivity("bcd", 37100, 37200);
        userActivities.addActivity(tActivity);
        tActivity = new TActivity("bcd", 37201, 37220);
        userActivities.addActivity(tActivity);

        userActivities.insertActivity("cde",37201,372202);
    }
}