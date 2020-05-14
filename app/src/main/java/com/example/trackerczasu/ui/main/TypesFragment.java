package com.example.trackerczasu.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trackerczasu.ActivityTypeList;
import com.example.trackerczasu.R;
import com.example.trackerczasu.UserActivities;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TypesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TypesFragment extends Fragment {
    // TODO: Rename and change types of parameters
    private ActivityTypeList typeList;

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
    public static TypesFragment newInstance(ActivityTypeList typeList) {
        TypesFragment fragment = new TypesFragment();
        fragment.typeList = typeList;
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
        return inflater.inflate(R.layout.fragment_types, container, false);
    }
}
