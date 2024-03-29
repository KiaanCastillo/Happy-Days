package ca.bcit.snaydon.castillo.alvar.happydays;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Log {

    public static final int MAX_FAVS = 3;

    private int id;

    private int month;

    private int day;

    private int year;

    private int dayType;

    private Map<String, Integer> activities;

    private int overallMood;

    private String notes;

    public Log(int month, int day, int year, int dayType, String activitiesString, String moodsString, String notes) {
        activities = new HashMap<>();
        this.month = month;
        this.day = day;
        this.year = year;
        this.dayType = dayType;
        this.notes = notes;
        populateActivitiesList(activitiesString, moodsString);
        overallMood = calculateAvg();
//        sortActivities();
    }

    private void populateActivitiesList(String activitiesString, String moodsString) {
        if (activitiesString.isEmpty())
            ;
        else {
            Scanner aScanner = new Scanner(activitiesString);
            Scanner mScanner = new Scanner(moodsString);
            while (aScanner.hasNext() && mScanner.hasNext())
                activities.put(aScanner.next(), mScanner.nextInt());
        }
    }

    public int calculateAvg() {
        if (activities.size() == 0)
            return 0;
        int average = 0;
        for (Integer i : activities.values())
            average += i;

        average = (int) Math.ceil(average / activities.size());
        return average;
    }

    public String[] getTopActivities() {
        String topActivities[] = new String[MAX_FAVS];
        int counter = 0;
        for (String activity : activities.keySet()) {
            if (counter < 3)
                topActivities[counter] = activity;

            counter++;
        }
        return topActivities;
    }

//    private void sortActivities() {
//        List<String> mapKeys = new ArrayList<>(activities.keySet());
//        List<Integer> mapValues = new ArrayList<>(activities.values());
//
//        Map<String, Integer> sortedMap = new HashMap<>();
//        int largest = mapValues.get(0);
//        int indexCounter = 0;
//        for (int j = 1; j < mapValues.size(); j++) {
//            for (int i = 1; i < mapValues.size(); i++) {
//                if (mapValues.get(i) > largest) {
//                    indexCounter = i;
//                    largest = mapValues.get(i);
//                }
//            }
//        }
//
//
//        activities = sortedMap;
//    }

    public int getID() { return id; }

    public void setID(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public String getActivities() {
        String activitiesString = "";
        for (String activity : activities.keySet())
            activitiesString += activity + " ";
        return activitiesString;
    }

    public String getActivitiesMoods() {
        String moodString = "";
        for (String activity : activities.keySet())
            moodString += activities.get(activity) + " ";
        return moodString;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDayType() {
        return dayType;
    }

    public void setDayType(int dayType) {
        this.dayType = dayType;
    }

    public int getOverallMood() {
        overallMood = calculateAvg();
        return overallMood;
    }

    public void setOverallMood(int overallMood) {
        this.overallMood = overallMood;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void addToActivities(String newActivity, int newMood) {
        activities.put(newActivity, newMood);
    }

    public void addToNotes(String notes) {
        this.notes += " " + notes;
    }
}
