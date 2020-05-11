package com.example.trackerczasu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import yuku.ambilwarna.AmbilWarnaDialog;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.trackerczasu.MESSAGE";
    public static UserActivities activityList = new UserActivities();
    public static ActivityTypeList typeList = new ActivityTypeList();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private int color = Color.parseColor("#e9d92e");
    //Button goalsButton = (Button)findViewById(R.id.goalsButton); // when clicked goes to new Activity window and shows active goals and user can make new

    @Override
    protected void onCreate(Bundle savedInstanceState){

        loadData();

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
        String message = editText.getText().toString(); //name of activity
        ActivityType A = new ActivityType(message, color);

        if(!message.equals("")){
            typeList.addType(A);
            mAdapter = new MyAdapter(typeList, this);
            recyclerView.setAdapter(mAdapter);
        }

        saveData();
        editText.setText("");

    }

    // to enable change of Activity
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

        saveData(); //after the deletion this state should be saved (not required if user clicked cancelled that though)
    }

    public void pickColor(View view) {
        final Button button = (Button) findViewById(R.id.pick_color);
        int color = Color.parseColor("#79aaff");
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, color, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                button.setBackgroundColor(color);
                setColor(color);
            }

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                // cancel was selected by the user
            }
        });

        dialog.show();
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void saveData()
    {
        ArrayList<Object> typeListData = new ArrayList<Object>();
        typeListData.add(typeList);

        try {
            File directory = getFilesDir();
            File file = new File(directory, "data.ser");

            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(typeListData);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved.");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void loadData ()
    {
        ArrayList<Object> deserialized = new ArrayList<Object>();

        try {
            File directory = getFilesDir(); //or getExternalFilesDir(null); for external storage
            File file = new File(directory, "data.ser");
            FileInputStream fileIn =  new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            deserialized = (ArrayList<Object>) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (SecurityException se) {
            se.printStackTrace();
            return;
        }
        ActivityTypeList retrievedActivityTypeList = (ActivityTypeList) deserialized.get(0);
        typeList = retrievedActivityTypeList;

    }

}

