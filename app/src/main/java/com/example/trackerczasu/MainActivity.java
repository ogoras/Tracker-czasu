package com.example.trackerczasu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.trackerczasu.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static UserActivities activityList = new UserActivities();
    private static GoalList goalList = new GoalList();
    public static ActivityTypeList typeList = new ActivityTypeList();
    private static TabLayout tabs;
    private static ViewPager viewPager;
    private static SectionsPagerAdapter sectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), activityList, typeList, goalList);
        viewPager = findViewById(R.id.view_pager);
        tabs = findViewById(R.id.tabs);
        tabsSetup();


        typeList.addType(new ActivityType("Studying", R.drawable.school));
        typeList.addType(new ActivityType("Sleeping", R.drawable.sleep));
        typeList.addType(new ActivityType("Driving", R.drawable.car));
        typeList.addType(new ActivityType("Reading", R.drawable.book));
        typeList.addType(new ActivityType("Working", R.drawable.work));
        typeList.addType(new ActivityType("Music", R.drawable.music));
        typeList.addType(new ActivityType("Cooking", R.drawable.food));
    }

    public void tabsSetup() {
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);

        int[] tabIcons = {
                R.drawable.activities,
                R.drawable.types,
                R.drawable.goals,
                R.drawable.stats,
                R.drawable.settings
        };

        final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4, R.string.tab_text_5};

        for(int i=0; i<tabs.getTabCount(); i++){
            if(tabs.getTabAt(i) != null){
                try {                                       //czasami są problemy z załadowaniem ikon
                    tabs.getTabAt(i).setIcon(tabIcons[i]);
                }
                catch (Exception E) {
                    tabs.getTabAt(i).setText(TAB_TITLES[i]);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_example, menu);
        return true;
    }

    public void startTracking(ActivityType type) {
        TActivity currentActivity = activityList.getCurrentActivity();
        if(currentActivity!=null){
            if (!currentActivity.type.equals(type.getName())) {
                currentActivity.isCurrent = false;
                currentActivity.endTime = System.currentTimeMillis() / 1000;
                activityList.addActivity(new TActivity(type.getName()));
                saveData();
            }
        }
        else {
            activityList.addActivity(new TActivity(type.getName()));
            saveData();
        }
        tabsSetup();
    }

    public void saveData()
    {
        ArrayList<Object> typeListData = new ArrayList<Object>();
        typeListData.add(typeList);
        typeListData.add(activityList);
        try {
            File directory = getFilesDir();
            File file = new File(directory, "data.ser");

            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(typeListData);
            out.close();
            fileOut.close();
            System.out.println("State saved. (Serialized data is saved)");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void loadData ()
    {
        ArrayList<Object> deserialized = new ArrayList<Object>();

        try {
            File directory = getFilesDir(); //or getExternalFilesDir(null); for external storage
            File file = new File(directory, "data.ser");
            FileInputStream fileIn =  new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            deserialized = (ArrayList<Object>) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (SecurityException se) {
            se.printStackTrace();
            return;
        }

        ActivityTypeList retrievedActivityTypeList = (ActivityTypeList) deserialized.get(0);
        typeList = retrievedActivityTypeList;

        UserActivities retrievedActivityList;
        if (deserialized.size()>=2) {
            retrievedActivityList = (UserActivities) deserialized.get(1);
            activityList = retrievedActivityList;
        }
    }
}