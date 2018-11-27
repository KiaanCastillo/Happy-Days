package ca.bcit.snaydon.castillo.alvar.happydays;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Vector;

public class Log {

    public static final int MAX_FAVS = 3;

    private int id;

    private int month;

    private int day;

    private int year;

    private int dayType;

    private Map<String, Integer> activities;

    private int overallMood;

    private int waterConsumption;

    private String notes;

    public Log(int id, int month, int day, int year, int dayType, String activitiesString, String moodsString, int waterConsumption, String notes) {
        this.id = id;
        this.month = month;
        this.day = day;
        this.year = year;
        this.dayType = dayType;
        this.waterConsumption = waterConsumption;
        this.notes = notes;
        populateActivitiesList(activitiesString, moodsString);
        overallMood = calculateAvg();
        sortActivities();
    }

    private void populateActivitiesList(String activitiesString, String moodsString) {
        Scanner aScanner = new Scanner(activitiesString);
        Scanner mScanner = new Scanner(moodsString);
        while (aScanner.hasNext() && mScanner.hasNext())
            activities.put(aScanner.next(), mScanner.nextInt());
    }

    public int calculateAvg() {
        ArrayList<Integer> moods = (ArrayList<Integer>) activities.values();
        int average = 0;
        for (Integer i : moods)
            average += i;

        average = (int) Math.ceil(average / moods.size());
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

    private void sortActivities() {
        List<String> mapKeys = new ArrayList<>(activities.keySet());
        List<Integer> mapValues = new ArrayList<>(activities.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<String, Integer> sortedMap =
                new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            int val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                int comp1 = activities.get(key);
                int comp2 = val;

                if (comp1 == comp2) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        activities = sortedMap;
    }

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
        return overallMood;
    }

    public void setOverallMood(int overallMood) {
        this.overallMood = overallMood;
    }

    public int getWaterConsumption() {
        return waterConsumption;
    }

    public void setWaterConsumption(int waterConsumption) {
        this.waterConsumption = waterConsumption;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
