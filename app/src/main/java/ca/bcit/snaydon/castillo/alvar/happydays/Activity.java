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
    private int logoId;

    private static String[] MENTAL_DESCRIPTIONS = {
        "Reading is a great way to gain knowledge or go on an adventure without ever leaving the room!",
        "Meditating can help you clear your mind and pivot you away from distracting thoughts and instead focusing on the present.",
        "Stretching helps increase blood flow by releasing all the tension in your muscles and joints!",
        "Journaling provides a healthy outlet to express yourself when you are feeling overwhelmed with emotion.",
        "Mind Maps are a great way to release lingering thoughts or ideas you may have built up overtime."
    };

    private static String[] PHYSICAL_DESCRIPTIONS = {
        "Biking is a great way to exercise without the heavy stress that running has on your knees.",
        "Running can be a great way to increase your endurance and help you keep a sharp mind!",
        "Working out can be as simple as doing pushups at home or going to a nearby gym and hitting the weights!",
        "Walking is a great way to start a habit of exercising or a way to de-stress after a long day of work."
    };

    public Activity(int id, String name, String description, int logoId, boolean isPhysical) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logoId = logoId;
        this.isPhysical = isPhysical;
    }

    public Activity(int id) {
        Activity temp = getActivityByID(id);
        this.id = temp.getId();
        this.name = temp.getName();
        this.description = temp.getDescription();
        this.logoId = temp.getLogo();
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
    public int getLogo() { return this.logoId; }
    public int getAvg_mood() { return avg_mood; }
    public void setAvg_mood(int avg_mood) { this.avg_mood = avg_mood; }
    public boolean isIs_favourite() { return is_favourite; }
    public void setIs_favourite(boolean is_favourite) { this.is_favourite = is_favourite; }

    public static Activity[] ACTIVITIES = {
            new Activity(R.id.btn_reading, "Reading", MENTAL_DESCRIPTIONS[0], R.drawable.ic_reading, false),
            new Activity(R.id.btn_meditating, "Meditating", MENTAL_DESCRIPTIONS[1], R.drawable.ic_meditating, false),
            new Activity(R.id.btn_stretching, "Stretching", MENTAL_DESCRIPTIONS[2], R.drawable.ic_stretching, false),
            new Activity(R.id.btn_journaling, "Journaling", MENTAL_DESCRIPTIONS[3], R.drawable.ic_journaling, false),
            new Activity(R.id.btn_mindmap, "Mind Map", MENTAL_DESCRIPTIONS[4], R.drawable.ic_mindmap, false),
            new Activity(R.id.btn_biking, "Biking", PHYSICAL_DESCRIPTIONS[0], R.drawable.ic_biking, true),
            new Activity(R.id.btn_running, "Running", PHYSICAL_DESCRIPTIONS[1], R.drawable.ic_running, true),
            new Activity(R.id.btn_workout, "Workout", PHYSICAL_DESCRIPTIONS[2], R.drawable.ic_workout, true),
            new Activity(R.id.btn_walking, "Walking", PHYSICAL_DESCRIPTIONS[3], R.drawable.ic_walking, true)
    };

    private Activity getActivityByID(int id) {
        int index = 0;
        for (int i = 0; i < ACTIVITIES.length; i++) {
            if (ACTIVITIES[i].getId() == id) {
                index = i;
                break;
            }
        }
        return ACTIVITIES[index];
    }

}
