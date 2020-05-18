package com.example.trackerczasu.ui.main;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.trackerczasu.ActivityType;
import com.example.trackerczasu.R;

import java.util.List;

class RowItem {
    int icon;
    String text;
    int color;
    boolean hasColor;
    public RowItem(ActivityType type) {
        icon = type.icon;
        text = type.name;
        if (type.hasColor)
            color = type.getColor();
        hasColor = type.hasColor;
    }
}

class TypeSpinnerAdapter extends ArrayAdapter<RowItem> {
    private TextView nameView;
    private ImageView imageView;
    LayoutInflater inflater;

    public TypeSpinnerAdapter(@NonNull Activity context, int resource, int textViewResourceId, @NonNull List<RowItem> objects) {
        super(context, resource, textViewResourceId, objects);
        inflater = context.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return rowview(convertView,position);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return rowview(convertView,position);
    }

    private View rowview(View convertView , int position){

        RowItem rowItem = getItem(position);

        View rowview = convertView;
        if (rowview==null) {

            rowview = inflater.inflate(R.layout.type_drop_view, null, false);

            nameView = (TextView) rowview.findViewById(R.id.title);
            imageView = (ImageView) rowview.findViewById(R.id.icon);
        }
        imageView.setImageResource(rowItem.icon);
        nameView.setText(rowItem.text);
        if(rowItem.hasColor)
            nameView.setTextColor(rowItem.color);

        return rowview;
    }
}