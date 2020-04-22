package com.example.trackerczasu;

import android.graphics.Color;

public class ActivityType {
    private int id;

    public String name;
    private Color color;

    public Color getColor() {
        return color;
    }

    public void changeColor(Color color) {
        this.color = color;
    }


    public void changeName(String name)
    {
        this.name = name;
    }

    public long getTotalDuration(long startTime, long endTime)
    {
        return 0;
    }
}
