package com.example.trackerczasu;
import java.util.ArrayList;
import java.util.List;

public class GoalList {
    public int size;
    public List<Goal> goalList;

    GoalList()
    {
        this.goalList = new ArrayList<Goal>();
        size = 0;
    }

    public void addGoal(Goal G)
    {
        goalList.add(G);
        size++;
    }
    public void deleteGoal(Goal G)
    {
        if(goalList.remove(G))
            size--;
    }
    public boolean findType(String name)
    {
        for (Goal G : goalList) {
            if (G.name == name)
                return true;
        }
        return false;
    }
}
