package ca.bcit.snaydon.castillo.alvar.happydays;

/**
 * Activity class. Holds information about an activity.
 */
public class Activity {

    /**
     * Name of the activity
     */
    private final String name;
    /**
     * Average mood score of this activity
     */
    private int avg_mood;
    /**
     * Is a favourite activity of the user
     */
    private boolean is_favourite;

    /**
     * Activity constructor
     * @param name
     *      name of the activity
     */
    public Activity(String name) {
        this.name = name;
        avg_mood = 0;
        this.is_favourite = false;
    }

    public String getName() {
        return name;
    }

    public int getAvg_mood() {
        return avg_mood;
    }

    public void setAvg_mood(int avg_mood) {
        this.avg_mood = avg_mood;
    }

    public boolean isIs_favourite() {
        return is_favourite;
    }

    public void setIs_favourite(boolean is_favourite) {
        this.is_favourite = is_favourite;
    }
}
