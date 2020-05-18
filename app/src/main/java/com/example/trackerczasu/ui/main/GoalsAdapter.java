package com.example.trackerczasu.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackerczasu.ActivityType;
import com.example.trackerczasu.ActivityTypeList;
import com.example.trackerczasu.Goal;
import com.example.trackerczasu.GoalList;
import com.example.trackerczasu.MainActivity;
import com.example.trackerczasu.R;


public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.GoalsViewHolder> {
    private static ActivityTypeList typeList;
    public static GoalList goalList;



    private Context context;
    public static class GoalsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView goalName;
        public ProgressBar progressBar;
        public TextView percent_done;
        public TextView achieved;
        public ImageButton tick_button;
        public ImageButton in_progress_button;



        public GoalsViewHolder(@NonNull View v) {
            super(v);
            goalName = v.findViewById(R.id.name_view2);
            progressBar = v.findViewById(R.id.progressBar);
            percent_done = v.findViewById(R.id.textView8);
            achieved = v.findViewById(R.id.textView9);
            tick_button = v.findViewById(R.id.imageButton3tick);
            in_progress_button = v.findViewById(R.id.imageButton3inprogress);
        }

        @Override
        public void onClick(View v) {

        }
    }
    @NonNull
    @Override
    public GoalsAdapter.GoalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.type_row_view, parent, false);
        GoalsViewHolder gvh = new GoalsViewHolder(v);
        return gvh;
    }

    @Override
    public void onBindViewHolder(@NonNull GoalsAdapter.GoalsViewHolder holder, int position) {
        final Goal currentItem  = goalList.goalList.get(position);

        holder.goalName.setText(currentItem.nameOfGoal);

    }

    @Override
    public int getItemCount() {
        return goalList.size;
    }


}
// each data item is just a string in this case