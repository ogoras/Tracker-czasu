package com.example.trackerczasu.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.trackerczasu.ActivityTypeList;
import com.example.trackerczasu.GoalList;
import com.example.trackerczasu.R;
import com.example.trackerczasu.UserActivities;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment extends Fragment {
    // TODO: Rename and change types of parameters
    private static UserActivities activityList;
    private static ActivityTypeList typeList;
    private static GoalList goalList;

    public StatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatsFragment newInstance(UserActivities activityList, ActivityTypeList typeList, GoalList goalList) {
        StatsFragment fragment = new StatsFragment();
        fragment.activityList = activityList;
        fragment.typeList = typeList;
        fragment.goalList = goalList;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }
}
