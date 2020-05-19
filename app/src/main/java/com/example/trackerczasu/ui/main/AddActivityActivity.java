package com.example.trackerczasu.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trackerczasu.ActivityTypeList;
import com.example.trackerczasu.R;
import com.example.trackerczasu.UserActivities;

import java.util.ArrayList;
import java.util.List;

public class AddActivityActivity extends AppCompatActivity {
    private UserActivities activityList;
    private ActivityTypeList typeList;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        activityList = (UserActivities)intent.getSerializableExtra("ACTIVITY_LIST");
        typeList = (ActivityTypeList)intent.getSerializableExtra("TYPE_LIST");

        setContentView(R.layout.activity_add_activity);
        Spinner spinner = findViewById(R.id.spinner);
        List<RowItem> rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < typeList.size; i++) {

            RowItem item = new RowItem(typeList.get(i));
            rowItems.add(item);
        }

        SpinnerAdapter adapter = new TypeSpinnerAdapter(this, R.layout.type_drop_view, R.id.title, rowItems);
        spinner.setAdapter(adapter);
    }

}
