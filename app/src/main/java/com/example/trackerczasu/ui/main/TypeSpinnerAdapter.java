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
    public RowItem(ActivityType type) {
        icon = type.icon;
        text = type.name;
        color = type.getColor();
    }
}

class TypeSpinnerAdapter extends ArrayAdapter<RowItem> {
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
        final TextView nameView;
        final ImageView imageView;

        RowItem rowItem = getItem(position);

        View rowview = convertView;
        if (rowview==null)
            rowview = inflater.inflate(R.layout.type_drop_view, null, false);

        nameView = (TextView) rowview.findViewById(R.id.title);
        imageView = (ImageView) rowview.findViewById(R.id.icon);
        imageView.setImageResource(rowItem.icon);
        nameView.setText(rowItem.text);
        nameView.setTextColor(rowItem.color);

        return rowview;
    }
}