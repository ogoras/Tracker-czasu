package com.example.trackerczasu;
import java.util.ArrayList;
import java.util.List;

public class GoalList {
    public int size;
    public List<Goal> goalList;

    public GoalList()
    {
        this.goalList = new ArrayList<Goal>();
        size = 0;
    }

    public void addGoal(Goal G)
    {
        if (findGoal(G.nameOfGoal) == null) { //name of Goal object must be unique
            goalList.add(G);
            size++;
        }
    }
    public void deleteGoal(Goal G)
    {
        if(goalList.remove(G))
            size--;
    }
    public Goal findGoal(String name)
    {
        for (Goal G : goalList) {
            if (G.nameOfGoal == name)
                return G;
        }
        return null;
    }
}
