package com.example.trackerczasu.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackerczasu.ActivityType;
import com.example.trackerczasu.ActivityTypeList;
import com.example.trackerczasu.MainActivity;
import com.example.trackerczasu.R;


public class TypesAdapter extends RecyclerView.Adapter<TypesAdapter.TypesViewHolder> {
    private static ActivityTypeList typeList;
    private Context context;

    public static class TypesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public ImageButton track_button;
        public TextView name;
        public ImageView typeIcon;
        public TypesViewHolder(View v) {
            super(v);
            track_button = v.findViewById(R.id.imageButton);
            name = v.findViewById(R.id.name_view2);
            typeIcon = v.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View v) {
        //tutaj co się stanie po kliknięciu w element listy
        }
    }

    public TypesAdapter(ActivityTypeList typeList, Context context) {
        super();
        this.typeList = typeList;
        this.context = context;
    }

    @NonNull
    @Override
    public TypesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.type_row_view, parent, false);
        TypesViewHolder tvh = new TypesViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TypesViewHolder holder, int position) {
        final ActivityType currentItem = typeList.get(position);

        holder.name.setText(currentItem.getName());
        holder.typeIcon.setImageResource(currentItem.getIcon());
        holder.track_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)context).startTracking(currentItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return typeList.size;   //do zmiany na typeList.size
    }
}
