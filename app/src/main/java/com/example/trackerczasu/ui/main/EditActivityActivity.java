package com.example.trackerczasu.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.trackerczasu.MainActivity;
import com.example.trackerczasu.R;
import com.example.trackerczasu.TActivity;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.trackerczasu.TimeFormat.DDMMYYYY_HHMM_Twolines;

public class EditActivityActivity extends AddActivityActivity {
    Button delete_button;
    int position;
    TActivity tActivity, clone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Edit activity");
        position = intent.getIntExtra("POSITION", 0);
        tActivity = activityList.list.get(position);
        startTime = tActivity.startTime;
        endTime = tActivity.endTime;
        name = tActivity.type;
        int typePosition = 0;
//        try {
//            clone = (TActivity) tActivity.clone()
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }

        setContentView(R.layout.activity_edit_activity);
        Spinner spinner = findViewById(R.id.spinner);
        List<RowItem> rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < typeList.size; i++) {

            RowItem item = new RowItem(typeList.get(i));
            rowItems.add(item);
            if (typeList.get(i).name.equals(tActivity.type))
                typePosition = i;
        }

        SpinnerAdapter adapter = new TypeSpinnerAdapter(this, R.layout.type_drop_view, R.id.title, rowItems);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(typePosition);

        startButton = findViewById(R.id.button2);
        startButton.setText(DDMMYYYY_HHMM_Twolines(startTime));
        endButton = findViewById(R.id.button3);
        endButton.setText(DDMMYYYY_HHMM_Twolines(endTime));
        tagView = findViewById(R.id.editText2);
        tagView.setText(tActivity.tag);
        commentView = findViewById(R.id.editText3);
        commentView.setText(tActivity.comment);

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
                MainActivity.deleteActivity(tActivity);
                try {
                    MainActivity.insertActivity(name, startTime, endTime, tagView.getText().toString(), commentView.getText().toString());
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra("SHOULD_SAVE", true);
                    startActivity(intent);
                }
                catch (IllegalArgumentException e){
                    try {
                        MainActivity.insertActivity(tActivity);
                    }
                    catch (IllegalArgumentException e1){
                        ;
                    }
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete_button = findViewById(R.id.button5);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.deleteActivity(tActivity);
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("SHOULD_SAVE", true);
                startActivity(intent);
            }
        });
    }
}
