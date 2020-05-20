package com.example.trackerczasu.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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

    private OnItemClickListener typeListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemListener(OnItemClickListener listener){
        typeListener = listener;
    };

    public static class TypesViewHolder extends RecyclerView.ViewHolder  {

        public ImageButton track_button;
        public TextView name;
        public ImageView typeIcon;
        public Button typeColor;
        public TypesViewHolder(View v, final OnItemClickListener listener) {
            super(v);
            track_button = v.findViewById(R.id.imageButton);
            name = v.findViewById(R.id.name_view2);
            typeIcon = v.findViewById(R.id.imageView);
            typeColor = v.findViewById(R.id.pick_color2);

            v.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View itemView){
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            final ActivityType currentItem = typeList.get(position);
                            listener.onItemClick(position);

                        }
                    }
                }

            });
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
        TypesViewHolder tvh = new TypesViewHolder(v, typeListener);
        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TypesViewHolder holder, int position) {
        final ActivityType currentItem = typeList.get(position);

        holder.name.setText(currentItem.getName());
        holder.name.setTextColor(currentItem.getColor());
        holder.typeIcon.setImageResource(currentItem.getIcon());
        holder.track_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)context).startTracking(currentItem);
            }
        });
    }


    @Override
    public int getItemCount() {
        return typeList.size;
    }
}
