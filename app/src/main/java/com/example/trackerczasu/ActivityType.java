package text_interface;

//import android.graphics.Color;
//import android.view.animation.AccelerateInterpolator;

public class ActivityType {
    private int id;

    public String name;
/*    private Color color;

    ActivityType (String name){
        this.name = name;
        this.color = Color.valueOf(0xff8f61ff);   //kolor domyslny
}

    ActivityType (String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void changeColor(Color color) {
        this.color = color;
    }
*/

    public void changeName(String name)
    {
        this.name = name;
    }

    public long getTotalDuration(UserActivities ActivityList) {
        long sum = 0;
        for (TActivity A : ActivityList.List) {
            if (A.type == this.name)
                sum += A.getDuration();
        }
        return sum;
    }

    public long getTotalDuration(UserActivities ActivityList, long startTime, long endTime)
    {
        if (endTime <= startTime)
            throw new IllegalArgumentException("End time must be bigger than start time");
        long sum = 0;
        for (TActivity A : ActivityList.List) {
            if (A.type == this.name) {
                if (startTime <= A.startTime) {
                    if (endTime >= A.endTime)
                        sum += A.getDuration();
                    else if (endTime > A.startTime)
                        sum += (endTime - A.startTime);
                }
                else if (startTime < A.endTime) {
                    if (endTime >= A.endTime)
                        sum += (A.endTime - startTime);
                    else
                        sum += (endTime - startTime);
                }
            }
        }
        return sum;
    }
}
