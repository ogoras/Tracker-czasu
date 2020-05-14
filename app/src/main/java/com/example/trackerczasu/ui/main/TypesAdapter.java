package com.example.trackerczasu.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackerczasu.ActivityTypeList;
import com.example.trackerczasu.R;

class TypesAdapter extends RecyclerView.Adapter {
    private ActivityTypeList typeList;
    private Context context;

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

        }
    }

    public TypesAdapter(ActivityTypeList typeList, Context context) {
        super();
        this.typeList = typeList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.type_row_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
