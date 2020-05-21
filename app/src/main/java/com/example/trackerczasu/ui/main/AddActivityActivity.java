package com.example.trackerczasu.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trackerczasu.ActivityTypeList;
import com.example.trackerczasu.MainActivity;
import com.example.trackerczasu.R;
import com.example.trackerczasu.UserActivities;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.trackerczasu.TimeFormat.DDMMYYYY_HHMM_Twolines;

public class AddActivityActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    UserActivities activityList;
    ActivityTypeList typeList;
    Intent intent;
    Button startButton, endButton, addButton;
    long startTime = System.currentTimeMillis()/1000 - 3600, endTime = System.currentTimeMillis()/1000;
    SwitchDateTimeDialogFragment dateTimeDialogFragment;
    String name;
    EditText tagView, commentView;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Add activity");
        super.onCreate(savedInstanceState);
        intent = getIntent();
        activityList = (UserActivities)intent.getSerializableExtra("ACTIVITY_LIST");
        typeList = (ActivityTypeList)intent.getSerializableExtra("TYPE_LIST");
        name = typeList.get(0).name;

        setContentView(R.layout.activity_add_activity);
        Spinner spinner = findViewById(R.id.spinner);
        List<RowItem> rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < typeList.size; i++) {

            RowItem item = new RowItem(typeList.get(i));
            rowItems.add(item);
        }

        SpinnerAdapter adapter = new TypeSpinnerAdapter(this, R.layout.type_drop_view, R.id.title, rowItems);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        startButton = findViewById(R.id.button2);
        startButton.setText(DDMMYYYY_HHMM_Twolines(startTime));
        endButton = findViewById(R.id.button3);
        endButton.setText(DDMMYYYY_HHMM_Twolines(endTime));
        tagView = findViewById(R.id.editText2);
        commentView = findViewById(R.id.editText3);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateTimeDialogFragment = createDatePicker("Czas początkowy", System.currentTimeMillis()/1000, startTime);
                dateTimeDialogFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Date date) {
                        if(date.getTime() < System.currentTimeMillis() - 60000)
                            startTime = date.getTime() / 1000;
                        else
                            startTime = System.currentTimeMillis()/1000 - 60;
                        startButton.setText(DDMMYYYY_HHMM_Twolines(startTime));
                        if(startTime > endTime){
                            if (System.currentTimeMillis()/1000 - startTime > 3600)
                                endTime = startTime + 3600;
                            else
                                endTime = System.currentTimeMillis()/1000;
                            endButton.setText(DDMMYYYY_HHMM_Twolines(endTime));
                        }
                    }

                    @Override
                    public void onNegativeButtonClick(Date date) {
                        ;
                    }
                });

                dateTimeDialogFragment.show(getSupportFragmentManager(), "dialog_time");
            }
        });



        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateTimeDialogFragment = createDatePicker("Czas końcowy", System.currentTimeMillis()/1000, endTime);
                dateTimeDialogFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Date date) {
                        if (date.getTime() < System.currentTimeMillis())
                            endTime = date.getTime() / 1000;
                        else
                            endTime = System.currentTimeMillis() / 1000;
                        endButton.setText(DDMMYYYY_HHMM_Twolines(endTime));
                        if (endTime < startTime) {
                            startTime = endTime - 3600;
                            startButton.setText(DDMMYYYY_HHMM_Twolines(startTime));
                        }
                    }

                    @Override
                    public void onNegativeButtonClick(Date date) {
                        ;
                    }
                });

                dateTimeDialogFragment.show(getSupportFragmentManager(), "dialog_time");
            }
        });
        
        addButton = findViewById(R.id.button4);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainActivity.insertActivity(name, startTime, endTime, tagView.getText().toString(), commentView.getText().toString());
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra("SHOULD_SAVE", true);
                    startActivity(intent);
                }
                catch (IllegalArgumentException e){
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private SwitchDateTimeDialogFragment createDatePicker(String title) {

        SwitchDateTimeDialogFragment dateTimeDialogFragment = SwitchDateTimeDialogFragment.newInstance(
                title,
                "OK",
                "Cancel"
        );

// Assign values
        dateTimeDialogFragment.startAtCalendarView();
        dateTimeDialogFragment.set24HoursMode(true);
        dateTimeDialogFragment.setDefaultDateTime(new Date(System.currentTimeMillis()-86400));

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
        dateTimeDialogFragment.setDefaultDateTime(new Date(startTime*1000 + 10));
        return dateTimeDialogFragment;
    }

    public SwitchDateTimeDialogFragment createDatePicker(String title, long endTime) {
        SwitchDateTimeDialogFragment dateTimeDialogFragment = createDatePicker(title);
        dateTimeDialogFragment.setMaximumDateTime(new Date(endTime*1000));
        dateTimeDialogFragment.setDefaultDateTime(new Date(endTime*1000 - 10));
        return dateTimeDialogFragment;
    }

    public SwitchDateTimeDialogFragment createDatePicker(long startTime, String title, long endTime) {
        if (startTime > endTime)
            throw new IllegalArgumentException("End time must be bigger than start time");
        SwitchDateTimeDialogFragment dateTimeDialogFragment = createDatePicker(startTime, title);
        dateTimeDialogFragment.setMaximumDateTime(new Date(endTime*1000));
        dateTimeDialogFragment.setDefaultDateTime(new Date((startTime + endTime)*500)); // w połowie dozwolonego przedziału
        return dateTimeDialogFragment;
    }

    public SwitchDateTimeDialogFragment createDatePicker(String title, long endTime, long defaultTime) {
        SwitchDateTimeDialogFragment dateTimeDialogFragment = createDatePicker(title, endTime);
        dateTimeDialogFragment.setDefaultDateTime(new Date(defaultTime*1000));
        return dateTimeDialogFragment;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        name = ((RowItem)parent.getItemAtPosition(position)).text;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
