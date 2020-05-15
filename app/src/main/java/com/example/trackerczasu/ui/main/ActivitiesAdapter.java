package com.example.trackerczasu.ui.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackerczasu.ActivityTypeList;
import com.example.trackerczasu.R;
import com.example.trackerczasu.UserActivities;

class ActivitiesAdapter extends RecyclerView.Adapter {
    private UserActivities activityList;
    private ActivityTypeList typeList;
    private Context context;

    public ActivitiesAdapter(UserActivities activityList, ActivityTypeList typeList, Context context) {
            super();
            this.activityList = activityList;
            this.typeList = typeList;
            this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public ImageButton track_button;
        public TextView textView;
        public ImageView imageView;
        public MyViewHolder(View v) {
            super(v);
            track_button = v.findViewById(R.id.imageButton);
            textView = v.findViewById(R.id.name_view2);
            imageView = v.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View v) {
            //tutaj co się stanie po kliknięciu w element listy
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return activityList.size;
    }
}
