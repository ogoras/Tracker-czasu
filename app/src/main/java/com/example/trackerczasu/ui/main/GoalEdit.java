package com.example.trackerczasu.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trackerczasu.Goal;
import com.example.trackerczasu.MainActivity;
import com.example.trackerczasu.R;



public class GoalEdit extends AppCompatActivity {

    EditText exactNameOfActivityType;
    EditText goalName;
    EditText percentageText;
    private Context context = this;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_goal_edit);
        exactNameOfActivityType = (EditText) findViewById(R.id.exactNameEditText);
        goalName = (EditText) findViewById(R.id.goalNameEditText2);
        percentageText = (EditText) findViewById(R.id.percentOfTimeEditText);



        Button saveButton = (Button) findViewById(R.id.saveBtn);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float ratio = Float.parseFloat(percentageText.getText().toString())/100;
                boolean typeFound = false;
                for (int i=0; i<MainActivity.typeList.size; i++)
                    if ( exactNameOfActivityType.getText().toString().equalsIgnoreCase(MainActivity.typeList.ActivityTypes.get(i).name) )
                    {
                        typeFound = true;
                        break;
                    }

                if (goalName.getText().toString().isEmpty())
                    goalName.setText("Goal " + (MainActivity.goalList.size + 1));
                if (typeFound == true)
                {
                    try {
                        Goal goal = new Goal(goalName.getText().toString(),exactNameOfActivityType.getText().toString(), ratio);
                        MainActivity.goalList.addGoal(goal);
                        System.out.println("Goal added");
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra("SHOULD_SAVE", true);
                        startActivity(intent);
                    }
                    catch (IllegalArgumentException e){
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    finish();
                }
                else System.out.println("Given type not found");

            }
        });


    }


}
