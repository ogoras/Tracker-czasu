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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TypesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TypesFragment extends Fragment {
    // TODO: Rename and change types of parameters
    private static ActivityTypeList typeList;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    private Context context;
    private View rootView;

    public TypesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TypesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TypesFragment newInstance(Context context, ActivityTypeList typeList) {
        TypesFragment fragment = new TypesFragment();
        fragment.typeList = typeList;
        fragment.context = context;
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
        rootView = inflater.inflate(R.layout.fragment_types, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new TypesAdapter(typeList, context);
        recyclerView.setAdapter(mAdapter);
        fab = rootView.findViewById(R.id.floatingActionButton3);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                {
                    Intent intent = new Intent(context, AddTypeActivity.class);
                    startActivity(intent);
                }
            }
        });
        return rootView;
    }
}
