package com.example.trackerczasu.ui.main;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.trackerczasu.ActivityTypeList;
import com.example.trackerczasu.Goal;
import com.example.trackerczasu.GoalList;
import com.example.trackerczasu.MainActivity;
import com.example.trackerczasu.R;
import com.example.trackerczasu.UserActivities;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4, R.string.tab_text_5};
    private final Context mContext;
    private static UserActivities activityList;
    private static ActivityTypeList typeList;
    private static GoalList goalList;

    public SectionsPagerAdapter(Context context, FragmentManager fm, UserActivities activityList, ActivityTypeList typeList, GoalList goalList) {
        super(fm);
        mContext = context;
        this.activityList = activityList;
        this.typeList = typeList;
        this.goalList = goalList;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                return ActivitiesFragment.newInstance(activityList, typeList);
            case 1:
                return TypesFragment.newInstance(typeList);
            case 2:
                return GoalsFragment.newInstance(activityList, typeList, goalList);
            case 3:
                return StatsFragment.newInstance(activityList, typeList, goalList);
            case 4:
                return SettingsFragment.newInstance();
        }
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float width = displayMetrics.widthPixels/displayMetrics.xdpi;
//        float height = displayMetrics.heightPixels/displayMetrics.ydpi;
        return (width > 3.0) ? mContext.getResources().getString(TAB_TITLES[position]) : "";
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 5;
    }
}