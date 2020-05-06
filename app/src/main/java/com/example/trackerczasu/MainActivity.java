package com.example.trackerczasu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.trackerczasu.MESSAGE";
    public static UserActivities activityList = new UserActivities();
    public static ActivityTypeList typeList = new ActivityTypeList();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.typesView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(typeList, this);
        recyclerView.setAdapter(mAdapter);
    }
    public static TActivity testActivity;

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Button button = (Button) findViewById(R.id.button);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        ActivityType A = new ActivityType(message);

        if(!message.equals("")){
            typeList.addType(A);
            mAdapter = new MyAdapter(typeList, this);
            recyclerView.setAdapter(mAdapter);
        }

        editText.setText("");

    }

    public void startTracking(ActivityType type) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        String message = type.name;
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void delType(ActivityType type) {
        typeList.deleteType(type);
        mAdapter = new MyAdapter(typeList, this);
        recyclerView.setAdapter(mAdapter);
    }
}

