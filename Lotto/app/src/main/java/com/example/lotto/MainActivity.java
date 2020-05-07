package com.example.lotto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText lowerLimitEditText = (EditText)findViewById(R.id.lowerLimitEditText);
        final EditText upperLimitEditText = (EditText)findViewById(R.id.upperLimitEditText);
        final Button drawButton = (Button)findViewById(R.id.drawButton);
        final TextView resultTextView = (TextView)findViewById(R.id.resultTextView);
        final TextView clickTextView = (TextView)findViewById(R.id.clicksTextView);

        drawButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    int lowerLimit;
                    if(lowerLimitEditText.getText().toString().matches(""))
                        lowerLimit = 0;
                    else
                        lowerLimit = Integer.parseInt(lowerLimitEditText.getText().toString());

                    int upperLimit;
                    if(upperLimitEditText.getText().toString().matches(""))
                        upperLimit = 0;
                    else
                        upperLimit = Integer.parseInt(upperLimitEditText.getText().toString());

                    resultTextView.setText(RandGen.generateRandomIntInRange(lowerLimit,upperLimit)+"");
                    DrawNumbers.rawDrawNumbersList.add(Integer.parseInt(resultTextView.getText().toString()));
                    resultTextView.setTextSize(50);
                    DrawNumbers.clicks++;
                    clickTextView.setText(DrawNumbers.clicks+"");
                }
                catch(WrongRangeException exception1)
                {
                    resultTextView.setTextSize(20);
                    resultTextView.setText(exception1.statement());
                }
                setupPieChart();
            }
        }
        );

    }

    private void setupPieChart()
    {
        DrawNumbers.createDiagramData();
        List<PieEntry> pieEntries = new ArrayList<PieEntry>();
        for(int i=0; i<DrawNumbers.diagramDataSize; i++)
            pieEntries.add(new PieEntry(DrawNumbers.dFrequencies[i], String.valueOf(DrawNumbers.dData[i])));

        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setColors(RandGen.generateColorsVector(DrawNumbers.diagramDataSize));
        PieData data = new PieData(dataSet);

        PieChart chart = (PieChart)findViewById(R.id.chart);
        chart.setData(data);
        //chart.animateY(1000);
        chart.setDrawEntryLabels(true);
        chart.setUsePercentValues(true);
        chart.clearDisappearingChildren();
        chart.getDescription().setEnabled(false); // bez opisu
        chart.getLegend().setEnabled(false); //bez kolorowych kwadracikow na dole
        chart.invalidate();

    }
}