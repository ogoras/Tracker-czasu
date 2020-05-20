package com.example.trackerczasu.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackerczasu.ActivityTypeList;
import com.example.trackerczasu.R;
import com.example.trackerczasu.UserActivities;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActivitiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivitiesFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private static UserActivities activityList;
    private static ActivityTypeList typeList;
    private Context context;

    public ActivitiesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ActivitiesFragment.
     */
    // TODO: Rename and change types and number of parameters
    @org.jetbrains.annotations.NotNull
    static ActivitiesFragment newInstance(UserActivities activityList, ActivityTypeList typeList) {
        ActivitiesFragment fragment = new ActivitiesFragment();
        ActivitiesFragment.activityList = activityList;
        ActivitiesFragment.typeList = typeList;
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
        View rootView = inflater.inflate(R.layout.fragment_types, container, false);
        context = getContext();
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        ActivitiesAdapter mAdapter = new ActivitiesAdapter(activityList, typeList, context);
        mAdapter.setOnItemListener(new ActivitiesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(context, EditActivityActivity.class);
                intent.putExtra("ACTIVITY_LIST", activityList);
                intent.putExtra("TYPE_LIST", typeList);
                intent.putExtra("POSITION", activityList.size - position - 1);
                context.startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);
        FloatingActionButton fab = rootView.findViewById(R.id.floatingActionButton3);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                {
                    Intent intent = new Intent(context, AddActivityActivity.class);
                    intent.putExtra("ACTIVITY_LIST", activityList);
                    intent.putExtra("TYPE_LIST", typeList);
                    startActivity(intent);
                }
            }
        });

        return rootView;
    }
}
