package com.example.trackerczasu;

import org.junit.Test;

import static android.graphics.Color.parseColor;
import static org.junit.Assert.*;

public class ActivityTypeListTest {

    @Test
    public void addTypeTest() {
            ActivityTypeList list = new ActivityTypeList();
            ActivityType t = new ActivityType("TestType", parseColor("#6f347c"), R.drawable.music);
            assertNotNull(t);
            list.addType(t);
            assertEquals(t.name, list.get(0).name);
            assertEquals(t.icon, list.get(0).icon);
    }

    @Test
    public void deleteType() {
        ActivityTypeList list = new ActivityTypeList();
        list.ActivityTypes.add(new ActivityType("test", R.drawable.music));
        list.deleteType(list.get(0));
        assertNotNull(list);
        assertEquals(0,list.size);
    }
    /*If you run a test that calls an API from the Android SDK that you do not mock,
    you'll receive an error that says this method is not mocked.
    That's because the android.jar file used to run unit tests does not contain any actual code
    (those APIs are provided only by the Android system image on a device).*/


}

