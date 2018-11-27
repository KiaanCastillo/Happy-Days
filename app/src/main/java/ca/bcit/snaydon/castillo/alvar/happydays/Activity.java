package ca.bcit.snaydon.castillo.alvar.happydays;

import java.io.Serializable;

/**
 * Activity class. Holds information about an activity.
 */
public class Activity implements Serializable {

    private int id;
    private String name;
    private String description;
    private boolean isPhysical;
    private int avg_mood;
    private boolean is_favourite;

    public Activity(int id, String name, String description, boolean isPhysical) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isPhysical = isPhysical;
    }

    public Activity(int id) {
        Activity temp = getActivityByID(id);
        this.id = temp.getId();
        this.name = temp.getName();
        this.description = temp.getDescription();
        this.isPhysical = temp.isPhysical();
    }

    public Activity(String name) {
        this.name = name;
        avg_mood = 0;
        this.is_favourite = false;
    }

    public int getId() { return id; }
    public String getName() {return name; }
    public String getDescription() { return description; }
    public boolean isPhysical() { return isPhysical; }

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

    public static Activity[] ACTIVITIES = {
            new Activity(R.id.btn_reading, "Reading", "Description Reading", false),
            new Activity(R.id.btn_meditating, "Meditating", "Description Meditating", false),
            new Activity(R.id.btn_stretching, "Stretching", "Description Stretching", false),
            new Activity(R.id.btn_journaling, "Journaling", "Description Journaling", false),
            new Activity(R.id.btn_mindmap, "Mind Map", "Description Mind Map", false)
    };

    private Activity getActivityByID(int id) {
        int indx = 0;
        for (int i = 0; i < ACTIVITIES.length; i++) {
            if (ACTIVITIES[i].getId() == id) {
                indx = i;
                break;
            }
        }
        return ACTIVITIES[indx];
    }

}
