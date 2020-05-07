package text_interface;

public class MinmaxGoal extends Goal {

    public long min;
    public long max;

    public boolean isMin;
    public boolean isMax;

    MinmaxGoal(String minOrMaxIndicator, long minOrMax) //to mógłby być teoretycznie boolean, a nie String
    {
        if (minOrMaxIndicator == "max")
            {
            this.max = minOrMax;
            isMin = false;
            isMax = true;
            }
        else
            {
            this.min = minOrMax;
            isMin = true;
            isMax = false;
            }

    }

    public void changeMin()
    {

    }
    public void changeMax()
    {

    }
    public void deleteMin()
    {

    }
    public void deleteMax()
    {

    }
}


