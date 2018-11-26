package ca.bcit.snaydon.castillo.alvar.happydays;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Log {

    private int id;

    private int month;

    private int day;

    private int year;

    private int dayType;

    private ArrayList<String> activities;

    private ArrayList<Integer> activities_moods;

    private int overallMood;

    private int waterConsumption;

    private String notes;

    public Log(int month, int day, int year, int dayType, String activitiesString, String moodsString, int waterConsumption,String notes) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.dayType = dayType;
        this.waterConsumption = waterConsumption;
        this.notes = notes;

        overallMood = calculateAvg();
    }

    private ArrayList<String> populateActivitiesList(String activitiesString) {
        ArrayList<String> activitiesList = new ArrayList<>();
        Scanner s = new Scanner(activitiesString);
        while (s.hasNext())
            activitiesList.add(s.next());
        return activitiesList;
    }

    private ArrayList<Integer> populateMoodList(String moodsString) {
        ArrayList<Integer> moodsList = new ArrayList<>();
        Scanner s = new Scanner(moodsString);
        while (s.hasNext())
            moodsList.add(s.nextInt());
        return moodsList;
    }

    private int calculateAvg() {
        int avg = 0;
        for (Integer i : activities_moods)
            avg += i;
        return avg;
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

    public void setYear(int year) {
        this.year = year;
    }

    public int getDayType() {
        return dayType;
    }

    public void setDayType(int dayType) {
        this.dayType = dayType;
    }

    public String getActivities() {
        StringBuilder activitiesString = new StringBuilder();
        for (String s : activities)
            activitiesString.append(s);
        return activitiesString.toString();
    }

    public void setActivities(ArrayList<String> activities) {
        this.activities = activities;
    }

    public String getActivities_moods() {
        StringBuilder activitiesMoodsString = new StringBuilder();
        for (Integer i : activities_moods)
            activitiesMoodsString.append(i);
        return activitiesMoodsString.toString();
    }

    public void setActivities_moods(ArrayList<Integer> activities_moods) {
        this.activities_moods = activities_moods;
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
