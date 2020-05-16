package com.example.trackerczasu.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.trackerczasu.ActivityType;
import com.example.trackerczasu.MainActivity;
import com.example.trackerczasu.R;

public class AddTypeActivity extends AppCompatActivity {
    public Button saveAddType;
    public TextView addTypeNameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_type);
        Button saveAddType = (Button) findViewById(R.id.save_type_button);
        final EditText addTypeNameEditText = (EditText) findViewById(R.id.add_type_name_editText);
        saveAddType.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                {
                    MainActivity.typeList.addType(new ActivityType(addTypeNameEditText.getText().toString(), R.drawable.food));
                    finish();
                }
            }
        });
    }


}
