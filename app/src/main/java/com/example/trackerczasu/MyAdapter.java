package com.example.trackerczasu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ActivityTypeList typeList;
    private MainActivity context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button track_button, delete_button;
        public TextView textView;
        public MyViewHolder(View v) {
            super(v);
            track_button = v.findViewById(R.id.track_button);
            delete_button = v.findViewById(R.id.delete_button);
            textView = v.findViewById(R.id.name_view);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ActivityTypeList myDataset, MainActivity context) {
        typeList = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_row_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(typeList.ActivityTypes.get(position).name);
        holder.textView.setTextColor(typeList.ActivityTypes.get(position).getColor());
        holder.track_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                context.startTracking(typeList.ActivityTypes.get(position));
            }
        });
        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                context.delType(typeList.ActivityTypes.get(position));
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return typeList.size;
    }
}