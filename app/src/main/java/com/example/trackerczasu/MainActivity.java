package com.example.trackerczasu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.trackerczasu.MESSAGE";
    public static UserActivities ActivityList = new UserActivities();
    public static ActivityTypeList TypeList = new ActivityTypeList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public static TActivity testActivity;

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        if (testActivity == null || !message.equals(testActivity.type))
            testActivity = new TActivity(message);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}

