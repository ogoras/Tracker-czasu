package com.example.trackerczasu.ui.main;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.trackerczasu.ActivityType;
import com.example.trackerczasu.ActivityTypeList;
import com.example.trackerczasu.GoalList;
import com.example.trackerczasu.R;
import com.example.trackerczasu.TActivity;
import com.example.trackerczasu.TimeFormat;
import com.example.trackerczasu.UserActivities;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment extends Fragment {
    // TODO: Rename and change types of parameters
    private static UserActivities activityList; //pozyskam czasy aktywnosci
    private static ActivityTypeList typeList; //stad wezme nazwe i kolor
    private static GoalList goalList;
    private PieChart pieChart;
    private BarChart barChart;
    private ArrayList<PieEntry> pieDataValues; // pary uporządkowane wartości i ich etykiet
    private ArrayList<BarEntry> barDataValues;
    private int [] colorsArray; //odpowiadające parom wartości z dataValues kolory, kolejność ma znaczenie, długość colorsArray musi być równa dlugości dataValues
    private Button generateBtn;
    private EditText numOfDaysTE;
    private int numOfDays;

    public StatsFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatsFragment newInstance(UserActivities activityList, ActivityTypeList typeList, GoalList goalList) {
        StatsFragment fragment = new StatsFragment();
        fragment.activityList = activityList;
        fragment.typeList = typeList;
        fragment.goalList = goalList;
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_stats, parent, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        numOfDaysTE = (EditText) getView().findViewById(R.id.numOfDaysTE);
        generateBtn = (Button) getView().findViewById(R.id.generateBtn);
        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createBlankDiagrams();
                numOfDays = Integer.parseInt(numOfDaysTE.getText().toString());
                createPieDataValues();
                buildPieChart();
                createBarDataValues();
                buildBarChart();
            }
        });

    }
    private void buildPieChart(){
        pieChart = (PieChart) getView().findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.BLACK);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setDragDecelerationFrictionCoef(0.99f);

        PieDataSet pieDataSet = new PieDataSet(pieDataValues,"");
        pieDataSet.setSliceSpace(1f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(colorsArray);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(15);
        pieData.setValueTextColor(Color.WHITE);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
    private void buildBarChart(){
        barChart = (BarChart) getView().findViewById(R.id.barchart);
        BarDataSet barDataSet = new BarDataSet(barDataValues,"");
        barDataSet.setColors(colorsArray);
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.invalidate();
    }
    private void createPieDataValues(){
        pieDataValues = new ArrayList<PieEntry>();
        ArrayList<Integer> colorsArrayList = new ArrayList<>();
        int currDayOfYear = TimeFormat.dayOfYear(System.currentTimeMillis()/1000); //obecny dzien
        for(ActivityType actTp : typeList.ActivityTypes)
        {
            if(actTp.getName().equals(""))
                continue;
            int actTimeSum = 0;
            for(TActivity act : activityList.list)
            {
                if(act.isCurrent == true || act.type != actTp.getName())
                    continue;
                colorsArrayList.add(actTp.getColor());
                int actDayOfYear = TimeFormat.dayOfYear(act.startTime); //dzien aktywnosci
                if(actDayOfYear <= currDayOfYear && actDayOfYear >= (currDayOfYear-numOfDays+1) )
                    actTimeSum += act.getDuration();
            }
            if(actTimeSum == 0)
                continue;
            pieDataValues.add(new PieEntry(actTimeSum,actTp.getName() + "\n" + TimeFormat.HourAndMinute(actTimeSum))); //suma w godzinach, niestety value wymaga int
        }
        colorsArray = new int[colorsArrayList.size()];
        for(int i=0; i<colorsArrayList.size(); i++)
            colorsArray[i] = (int)colorsArrayList.get(i);

    }
    //kazdy BarEntry to sredni czas aktywnosci w wybranym odgornie okresie
    private void createBarDataValues(){
        barDataValues = new ArrayList<BarEntry>();
        barDataValues.add(new BarEntry(1,1)); //x traktujemy jako liczbe porzadkowa czyli ktore zajmie miejsce od lewej do prawej na wykresie
        barDataValues.add(new BarEntry(2,2));
        barDataValues.add(new BarEntry(3,0));
        colorsArray = new int[]{Color.RED, Color.GREEN};
    }
    private void createBlankDiagrams(){


    }

}
