package com.example.trackerczasu.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.trackerczasu.R;



public class GoalEdit extends AppCompatActivity {

    EditText exactNameOfActivityType;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_goal_edit);
        exactNameOfActivityType = (EditText) findViewById(R.id.exactNameEditText);


    }


}