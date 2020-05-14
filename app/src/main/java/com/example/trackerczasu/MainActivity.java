package com.example.trackerczasu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.trackerczasu.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static UserActivities activityList = new UserActivities();
    private static ActivityTypeList typeList = new ActivityTypeList();
    private static GoalList goalList = new GoalList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), activityList, typeList, goalList);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
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
}