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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView start_time;
        public ImageView icon;
        public FrameLayout item_line;
        public TextView name;
        public TextView tag;
        public TextView comment;

        public ViewHolder(View v) {
            super(v);
            start_time = v.findViewById(R.id.startView);
            icon = v.findViewById(R.id.icon);
            item_line = v.findViewById(R.id.item_line);
            name = v.findViewById(R.id.typeView);
            tag = v.findViewById(R.id.tagView);
            comment = v.findViewById(R.id.commentView);
        }
    }

    public static class ViewHolderStopped extends ViewHolder implements View.OnClickListener {
        public TextView end_time;
        public TextView duration;

        public ViewHolderStopped(View v) {
            super(v);
            end_time = v.findViewById(R.id.endView);
            duration = v.findViewById(R.id.durationView);
        }

        @Override
        public void onClick(View v) {
            //tutaj co się stanie po kliknięciu w aktywność
        }
    }

    public static class ViewHolderCurrent extends ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public ImageButton stop_button;
        public Chronometer timer;

        public ViewHolderCurrent(View v) {
            super(v);
            timer = v.findViewById(R.id.simpleChronometer);
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
            ViewHolderCurrent vh = new ViewHolderCurrent(v);
            return vh;
        }
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_view, parent, false);
        ViewHolderStopped vh = new ViewHolderStopped(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        //TODO: tu wypełniamy element o numerze position
        switch(holder.getItemViewType()) {
            case VIEW_TYPE_CURRENT:
            case VIEW_TYPE_TOP:
                // The top of the line has to be rounded
                holder1.item_line.setBackground(context.getDrawable(R.drawable.line_bg_top));
                break;
            case VIEW_TYPE_MIDDLE:
                // Only the color could be enough
                // but a drawable can be used to make the cap rounded also here
                holder1.item_line.setBackground(context.getDrawable(R.drawable.line_bg_middle));
                break;
            case VIEW_TYPE_BOTTOM:
                holder1.item_line.setBackground(context.getDrawable(R.drawable.line_bg_bottom));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 10;  //tu docelowo activityList.size
    }
}
