package com.example.trackerczasu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.TextView;


public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        final TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(message.toUpperCase());

        //final TextView timerView = (TextView) findViewById(R.id.textView5);
        Chronometer simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer); // initiate a chronometer
        simpleChronometer.setBase(SystemClock.elapsedRealtime() /* - (System.currentTimeMillis() - testActivity.startTime*1000 ) */ );
        simpleChronometer.start(); // start a chronometer
    }
}
