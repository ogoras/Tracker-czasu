package com.example.trackerczasu.ui.main;

import android.content.Context;
import android.os.SystemClock;
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
import com.example.trackerczasu.MainActivity;
import com.example.trackerczasu.R;
import com.example.trackerczasu.TActivity;
import com.example.trackerczasu.UserActivities;

import static com.example.trackerczasu.TimeFormat.HMSDuration;
import static com.example.trackerczasu.TimeFormat.HourAndMinute;


class ActivitiesAdapter extends RecyclerView.Adapter {
    private static final long GAP_TIME = 60;
    private static UserActivities activityList;
    private static ActivityTypeList typeList;
    private Context context;
    private static final int VIEW_TYPE_TOP = 0; //aktywność na górze, zakończona
    private static final int VIEW_TYPE_MIDDLE = 1;  //aktywność w środku
    private static final int VIEW_TYPE_BOTTOM = 2;  //aktywność na dole
    private static final int VIEW_TYPE_CURRENT = 3; //aktywność trwająca
    private static final int VIEW_TYPE_START = 4;   //aktywność trwająca, jeśli nie ma poprzedników
    private static final int VIEW_TYPE_ONLY = 5;    //jedyna aktywność na liście, zakończona
    private static final int VIEW_TYPE_EMPTY = 6;   //puste pole pod spodem

    public ActivitiesAdapter(UserActivities activityList, ActivityTypeList typeList, Context context) {
            super();
            this.activityList = activityList;
            this.typeList = typeList;
            this.context = context;
    }

    private static class ViewHolderEmpty extends RecyclerView.ViewHolder {
        public ViewHolderEmpty(View v) {
            super(v);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView start_time;
        public ImageView icon;
        public FrameLayout item_line;
        public TextView name;
        public TextView tag;
        public TextView comment;
        public View dot;

        public ViewHolder(View v) {
            super(v);
            start_time = v.findViewById(R.id.startView);
            icon = v.findViewById(R.id.icon);
            item_line = v.findViewById(R.id.item_line);
            name = v.findViewById(R.id.typeView);
            tag = v.findViewById(R.id.tagView);
            comment = v.findViewById(R.id.commentView);
            dot = v.findViewById(R.id.dot);
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
            stop_button = v.findViewById(R.id.imageButton2);
        }

        @Override
        public void onClick(View v) {
            //tutaj co się stanie po kliknięciu w aktywność
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1){
            return VIEW_TYPE_EMPTY;
        }
        else if (getItemCount() == 2) {
            if (activityList.getCurrentActivity() != null)
                return VIEW_TYPE_START;
            else return VIEW_TYPE_ONLY;
        }
        if (position == 0) {
            if (activityList.getCurrentActivity() != null) {
                if (activityList.getCurrentActivity().startTime > activityList.list.get(activityList.size - position - 2).endTime + GAP_TIME)
                    return VIEW_TYPE_START;
                else return VIEW_TYPE_CURRENT;
            }
            else if (activityList.list.get(activityList.size - position - 1).startTime > activityList.list.get(activityList.size - position - 2).endTime + GAP_TIME)
                return VIEW_TYPE_ONLY;
            else return VIEW_TYPE_TOP;
        }
        else if (position == getItemCount() - 2) {
            if (activityList.list.get(activityList.size - position).startTime > activityList.list.get(activityList.size - position - 1).endTime + GAP_TIME)
                return  VIEW_TYPE_ONLY;
            else return VIEW_TYPE_BOTTOM;
            }
        else if (activityList.list.get(activityList.size - position - 1).startTime > activityList.list.get(activityList.size - position - 2).endTime + GAP_TIME) {
            if (activityList.list.get(activityList.size - position).startTime > activityList.list.get(activityList.size - position - 1).endTime + GAP_TIME)
                return VIEW_TYPE_ONLY;
            else return VIEW_TYPE_BOTTOM;
        }
        else if (activityList.list.get(activityList.size - position).startTime > activityList.list.get(activityList.size - position - 1).endTime + GAP_TIME)
            return VIEW_TYPE_TOP;
        else return VIEW_TYPE_MIDDLE;
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
        else if (viewType == VIEW_TYPE_EMPTY) {
            View v = (View) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.empty_row_view, parent, false);
            ViewHolderEmpty vh = new ViewHolderEmpty(v);
            return vh;
        }
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_view, parent, false);
        ViewHolderStopped vh = new ViewHolderStopped(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() != VIEW_TYPE_EMPTY) {
            TActivity tActivity = activityList.list.get(activityList.size - 1 - position);
            ViewHolder holder1 = (ViewHolder) holder;
            //TODO: tu wypełniamy element o numerze position
            switch (holder.getItemViewType()) {
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
            holder1.comment.setText(tActivity.comment);
            holder1.icon.setImageResource(typeList.findType(tActivity.type).getIcon());
            holder1.name.setText(tActivity.type);
            holder1.tag.setText(tActivity.tag);
            holder1.name.setTextColor(typeList.findType(tActivity.type).getColor());
            switch (holder.getItemViewType()){
                case VIEW_TYPE_START:
                    holder1.start_time.setText(HourAndMinute(tActivity.startTime));
                case VIEW_TYPE_CURRENT:
                    ViewHolderCurrent holder2 = (ViewHolderCurrent)holder;
                    holder2.timer.setBase(SystemClock.elapsedRealtime() - (System.currentTimeMillis() - tActivity.startTime*1000 ) );
                    holder2.timer.start();
                    holder2.stop_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            activityList.getCurrentActivity().endTime = System.currentTimeMillis()/1000;
                            activityList.getCurrentActivity().isCurrent = false;
                            ((MainActivity)context).saveData();
                            ((MainActivity)context).tabsSetup();
                        }
                    });
                    break;
                case VIEW_TYPE_ONLY:
                case VIEW_TYPE_BOTTOM:
                    holder1.start_time.setText(HourAndMinute(tActivity.startTime));
                case VIEW_TYPE_MIDDLE:
                case VIEW_TYPE_TOP:
                    ViewHolderStopped holder3 = (ViewHolderStopped)holder;
                    holder3.end_time.setText(HourAndMinute(tActivity.endTime));
                    holder3.duration.setText(HMSDuration(tActivity.getDuration()));
            }
        }
    }

    @Override
    public int getItemCount() {
        return activityList.size + 1;
    }
}
