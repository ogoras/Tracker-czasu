package com.example.trackerczasu.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackerczasu.ActivityTypeList;
import com.example.trackerczasu.R;
import com.example.trackerczasu.UserActivities;


class ActivitiesAdapter extends RecyclerView.Adapter {
    private static UserActivities activityList;
    private static ActivityTypeList typeList;
    private Context context;
    private static final int VIEW_TYPE_TOP = 0; //aktywność na górze, zakończona
    private static final int VIEW_TYPE_MIDDLE = 1;  //aktywność w środku
    private static final int VIEW_TYPE_BOTTOM = 2;  //aktywność na dole
    private static final int VIEW_TYPE_CURRENT = 3; //aktywność trwająca
    private static final int VIEW_TYPE_START = 4;   //aktywność trwająca, jeśli nie ma poprzedników
    private static final int VIEW_TYPE_ONLY = 5;    //jedyna aktywność na liście, zakończona

    public ActivitiesAdapter(UserActivities activityList, ActivityTypeList typeList, Context context) {
            super();
            this.activityList = activityList;
            this.typeList = typeList;
            this.context = context;
    }

    public static class ViewHolderStopped extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView start_time;
        public TextView end_time;
        public ImageView icon;
        public FrameLayout item_line;
        public TextView name;
        public TextView tag;
        public TextView comment;
        public TextView duration;

        public ViewHolderStopped(View v) {
            super(v);
            //tu pobieramy uchwyty do elementów
        }

        @Override
        public void onClick(View v) {
            //tutaj co się stanie po kliknięciu w aktywność
        }
    }

    public static class ViewHolderCurrent extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public ImageButton stop_button;
        public TextView start_time;
        public ImageView icon;
        public FrameLayout item_line;
        public TextView name;
        public TextView tag;
        public TextView comment;
        public Chronometer timer;

        public ViewHolderCurrent(View v) {
            super(v);
            //tu pobieramy uchwyty do elementów
        }

        @Override
        public void onClick(View v) {
            //tutaj co się stanie po kliknięciu w aktywność
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() == 1) {
            if (activityList.getCurrentActivity() != null)
                return VIEW_TYPE_START;
            else return VIEW_TYPE_ONLY;
        }
        if (position == 0) {
            if (activityList.getCurrentActivity() != null)
                return VIEW_TYPE_CURRENT;
            return VIEW_TYPE_TOP;
        }
    else if (position == getItemCount() - 1) {
                return VIEW_TYPE_BOTTOM;
            }
            return VIEW_TYPE_MIDDLE;
        }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_CURRENT || viewType == VIEW_TYPE_START) {
            View v = (View) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.current_activity_view, parent, false);
            TypesAdapter.MyViewHolder vh = new TypesAdapter.MyViewHolder(v);
            return vh;
        }
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_view, parent, false);
        TypesAdapter.MyViewHolder vh = new TypesAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //TODO: tu wypełniamy element o numerze position
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
