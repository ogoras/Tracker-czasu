package com.example.trackerczasu.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackerczasu.ActivityTypeList;
import com.example.trackerczasu.Goal;
import com.example.trackerczasu.GoalList;
import com.example.trackerczasu.R;
import com.example.trackerczasu.UserActivities;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GoalsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoalsFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private static UserActivities activityList;
    private static ActivityTypeList typeList;
    private static GoalList goalList;
    private View rootView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    private Context context;

    public TextView x_field;


    public GoalsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GoalsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GoalsFragment newInstance(UserActivities activityList, ActivityTypeList typeList, GoalList goalList) {
        GoalsFragment fragment = new GoalsFragment();

        fragment.activityList = activityList;
        fragment.typeList = typeList;
        fragment.goalList = goalList;
        return fragment;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        




        long totalDurationOfAllActivities = 0;
        for (int i=0; i<typeList.size; i++) {
            totalDurationOfAllActivities += typeList.get(i).getTotalDuration(activityList);
        }

        for (int i=0; i<goalList.size; i++) {
            if (goalList.goalList.get(i).refersToTwoTypes == 0 )
            {
                if (typeList.findType(goalList.goalList.get(i).nameOfActivityType).getTotalDuration(activityList) >
                        (goalList.goalList.get(i).typesRatio * totalDurationOfAllActivities) ) {
                    goalList.goalList.get(i).isAchieved = true;
                }
                else {
                    goalList.goalList.get(i).isAchieved = false;
                }

            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        rootView = inflater.inflate(R.layout.fragment_goals, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.GoalsRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new GoalsAdapter(activityList,typeList,goalList, context);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }
}
