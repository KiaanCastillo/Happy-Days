package ca.bcit.snaydon.castillo.alvar.happydays;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    /**
     * Name of the database
     */
    private static final String DB_NAME = "user.sqlite";
    /**
     * Version of the database
     */
    private static final int DB_VERSION = 1;
    /**
     * Name of the USER_INFO table
     */
    private final String USER_INFO = "USER_INFO";
    /**
     * Name of the MENTAL_ACTIVITIES_INFO table
     */
    private final String MENTAL_ACTIVITIES_INFO = "MENTAL_ACTIVITIES_INFO";
    /**
     * Name of the PHYSICAL_ACTIVITIES_INFO table
     */
    private final String PHYSICAL_ACTIVITIES_INFO = "PHYSICAL_ACTIVITIES_INFO";
    /**
     * Name of the LOGS table
     */
    private final String LOGS = "LOGS";
    /**
     * Context
     */
    private Context context;

    private static final String[] MENTAL_ACTIVITIES_LIST = {"Reading", "Journaling", "Mindmap", "Stretching", "Meditating"};

    private static final String[] PHYSICAL_ACTIVITIES_LIST = {"Walking", "Biking","Running", "Workout"};

    public UserDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createUserInfoTable());
        db.execSQL(createMentalActivitiesTable());
        db.execSQL(createPhysicalActivitiesTable());
        db.execSQL(createLogsTable());
        db.execSQL(createNotifScheduleTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(createUserInfoTable());
        db.execSQL(createMentalActivitiesTable());
        db.execSQL(createPhysicalActivitiesTable());
        db.execSQL(createLogsTable());
        db.execSQL(createNotifScheduleTable());
    }

    public void initializeUser(SQLiteDatabase db, User user, ArrayList<String> favMental, ArrayList<String> favPhysical) {
        insertUser(db, user);
        initializeMentalActivitiesTable(db, favMental);
        initializePhysicalActivitiesTable(db, favPhysical);
    }

    private void initializeMentalActivitiesTable(SQLiteDatabase db, ArrayList<String> favMental) {
        //Storing activity info in MENTAL_ACTIVITIES_INFO table
        for (String mActivity : MENTAL_ACTIVITIES_LIST) {
            ContentValues activities_values = new ContentValues();
            activities_values.put("NAME", mActivity);
            activities_values.put("AVG", 5);
            if (favMental.contains(mActivity))
                activities_values.put("FAV", 1);
            else
                activities_values.put("FAV", 0);
            db.insert("MENTAL_ACTIVITIES_INFO", null, activities_values);
        }
    }

    private void initializePhysicalActivitiesTable(SQLiteDatabase db, ArrayList<String> favPhysical) {
        //Storing activity info in MENTAL_ACTIVITIES_INFO table
        for (String pActivity : PHYSICAL_ACTIVITIES_LIST) {
            ContentValues activitiesValues = new ContentValues();
            activitiesValues.put("NAME", pActivity);
            activitiesValues.put("AVG", 5);
            if (favPhysical.contains(pActivity))
                activitiesValues.put("FAV", 1);
            else
                activitiesValues.put("FAV", 0);
            db.insert("PHYSICAL_ACTIVITIES_INFO", null, activitiesValues);
        }
    }

    public void insertUser(SQLiteDatabase db, User user) {
        //Storing user info in USER_INFO table
        ContentValues userValues = new ContentValues();
        userValues.put("FIRST_NAME", user.getFirst_name());
        userValues.put("LAST_NAME", user.getLast_name());
        db.insert(USER_INFO, null, userValues);
    }

    public void insertLog(SQLiteDatabase db, Log log) {
        ContentValues logValues = new ContentValues();
        logValues.put("MONTH", log.getMonth());
        logValues.put("DAY", log.getDay());
        logValues.put("YEAR", log.getYear());
        logValues.put("DAY_TYPE", log.getDayType());
        logValues.put("ACTIVITIES", log.getActivities());
        logValues.put("ACTIVITIES_MOODS", log.getActivitiesMoods());
        logValues.put("OVERALL_MOOD", log.getOverallMood());
        logValues.put("NOTES", log.getNotes());
        db.insert(LOGS, null, logValues);
    }

    public void updateLog(SQLiteDatabase db, Log log, int log_id) {
        ContentValues logValues = new ContentValues();
        logValues.put("ACTIVITIES", log.getActivities());
        logValues.put("ACTIVITIES_MOOD", log.getActivitiesMoods());
        logValues.put("OVERALL_MOOD", log.getOverallMood());
        logValues.put("NOTES", log.getNotes());
        db.update(LOGS, logValues, "_id = " + log_id, null);
    }

    public void addFinishedActivity(SQLiteDatabase db, String activityName, int activityMood, String notes) {

        try {
            db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT ACTIVITIES, ACTIVITIES_MOOD, NOTES FROM LOGS",null);

            // move to the first record
            if (cursor.moveToFirst()) {
//                user = new User(
//                        cursor.getString(0),
//                        cursor.getString(1));
            }
            cursor.close();
        } catch (SQLiteException sqlex) {
            sqlex.printStackTrace();
        }
    }

    private String createUserInfoTable() {
        String sql = "";
        sql += "CREATE TABLE USER_INFO (";
        sql += "FIRST_NAME TEXT, ";
        sql += "LAST_NAME TEXT);";

        return sql;
    }

    private String createMentalActivitiesTable() {
        String sql = "";
        sql += "CREATE TABLE MENTAL_ACTIVITIES_INFO (";
        sql += "NAME TEXT, ";
        sql += "AVG INTEGER, ";
        sql += "FAV NUMERIC);"; //boolean

        return sql;
    }

    private String createPhysicalActivitiesTable() {
        String sql = "";
        sql += "CREATE TABLE PHYSICAL_ACTIVITIES_INFO (";
        sql += "NAME TEXT, ";
        sql += "AVG INTEGER, ";
        sql += "FAV NUMERIC);"; //boolean

        return sql;
    }

    private String createLogsTable() {
        String sql = "";
        sql += "CREATE TABLE LOGS (";
        sql += "_id INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += "MONTH INTEGER, ";
        sql += "DAY INTEGER, ";
        sql += "YEAR INTEGER, ";
        sql += "DAY_TYPE INTEGER, ";
        sql += "ACTIVITIES TEXT, ";
        sql += "ACTIVITIES_MOODS TEXT, ";
        sql += "OVERALL_MOOD INTEGER, ";
        sql += "NOTES TEXT);";
        return sql;
    }

    private String createNotifScheduleTable() {
        String sql = "";
        sql += "CREATE TABLE NOTIF_SCHEDULE (";
        sql += "ACTIVITY STRING, ";
        sql += "HOUR INTEGER);";
        return sql;
    }

    public User getUser() {
        User user = null;

        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM USER_INFO",null);

            // move to the first record
            if (cursor.moveToFirst()) {
                user = new User(
                        cursor.getString(0),
                        cursor.getString(1));
            }
            cursor.close();
        } catch (SQLiteException sqlex) {
            sqlex.printStackTrace();
        }


        return user;
    }

    public ArrayList<String> getFavouriteActivities() {
        ArrayList<String> favActivities = new ArrayList<>();

        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT NAME FROM MENTAL_ACTIVITIES_INFO WHERE FAV = 1",null);

            // move to the first record
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    favActivities.add(cursor.getString(0));
                    cursor.moveToNext();
                }
            }

            cursor = db.rawQuery("SELECT NAME FROM PHYSICAL_ACTIVITIES_INFO WHERE FAV = 1",null);

            // move to the first record
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    favActivities.add(cursor.getString(0));
                    cursor.moveToNext();
                }
            }

            cursor.close();
        } catch (SQLiteException sqlex) {
            sqlex.printStackTrace();
        }


        return favActivities;
    }

    public ArrayList<Log> getAllLogs() {
        ArrayList<Log> logs = new ArrayList<>();
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM LOGS",null);

            // move to the first record
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    Log log = new Log(
                            cursor.getInt(1),
                            cursor.getInt(2),
                            cursor.getInt(3),
                            cursor.getInt(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7));
                    log.setID(cursor.getInt(0));
                    logs.add(log);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        } catch (SQLiteException sqlex) {
            sqlex.printStackTrace();
        }

        return logs;
    }

    public Log getTodayLog() {
        Log todayLog = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM LOGS WHERE MONTH = " + month + " AND DAY = " + day + " AND YEAR = " + year, null);

            // move to the first record
            if (cursor.moveToFirst()) {
                todayLog = new Log(
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7));
                todayLog.setID(cursor.getInt(0));
            }
            cursor.close();
        } catch (SQLiteException sqlex) {
            sqlex.printStackTrace();
        }

        return todayLog;
    }
}
