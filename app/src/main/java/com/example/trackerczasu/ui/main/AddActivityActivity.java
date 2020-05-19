package com.example.trackerczasu.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trackerczasu.ActivityTypeList;
import com.example.trackerczasu.R;
import com.example.trackerczasu.UserActivities;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    public SwitchDateTimeDialogFragment createDatePicker(String title) {
        Date date;

        SwitchDateTimeDialogFragment dateTimeDialogFragment = SwitchDateTimeDialogFragment.newInstance(
                title,
                "OK",
                "Cancel"
        );

// Assign values
        dateTimeDialogFragment.startAtCalendarView();
        dateTimeDialogFragment.set24HoursMode(true);

// Define new day and month format
        try {
            dateTimeDialogFragment.setSimpleDateMonthAndDayFormat(new SimpleDateFormat("dd MMMM", Locale.getDefault()));
        } catch (SwitchDateTimeDialogFragment.SimpleDateMonthAndDayFormatException e) {
            ;
        }
        return dateTimeDialogFragment;
    }

    public SwitchDateTimeDialogFragment createDatePicker(long startTime, String title) {
        SwitchDateTimeDialogFragment dateTimeDialogFragment = createDatePicker(title);
        dateTimeDialogFragment.setMinimumDateTime(new Date(startTime*1000));
        return dateTimeDialogFragment;
    }

    public SwitchDateTimeDialogFragment createDatePicker(String title, long endTime) {
        SwitchDateTimeDialogFragment dateTimeDialogFragment = createDatePicker(title);
        dateTimeDialogFragment.setMaximumDateTime(new Date(endTime*1000));
        return dateTimeDialogFragment;
    }

    public SwitchDateTimeDialogFragment createDatePicker(long startTime, String title, long endTime) {
        SwitchDateTimeDialogFragment dateTimeDialogFragment = createDatePicker(startTime, title);
        dateTimeDialogFragment.setMaximumDateTime(new Date(endTime*1000));
        return dateTimeDialogFragment;
    }
}
