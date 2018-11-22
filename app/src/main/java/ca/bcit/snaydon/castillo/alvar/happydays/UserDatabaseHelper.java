package ca.bcit.snaydon.castillo.alvar.happydays;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

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

    private static final String[] MENTAL_ACTIVITIES_LIST = {"Reading", "Journaling", "Mindmaps", "Stretching", "Meditating"};

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(createUserInfoTable());
        db.execSQL(createMentalActivitiesTable());
        db.execSQL(createPhysicalActivitiesTable());
        db.execSQL(createLogsTable());
    }

    public void initializeUser(SQLiteDatabase db, User user) {
        insertUser(db, user);
        initializeMentalActivitiesTable(db);
        initializePhysicalActivitiesTable(db);
    }

    private void insertUser(SQLiteDatabase db, User user) {
        //Storing user info in USER_INFO table
        ContentValues user_values = new ContentValues();
        user_values.put("FIRST_NAME", user.getFirst_name());
        user_values.put("LAST_NAME", user.getLast_name());
        db.insert(USER_INFO, null, user_values);
    }

    private void initializeMentalActivitiesTable(SQLiteDatabase db) {
        //Storing activity info in MENTAL_ACTIVITIES_INFO table
        for (String mActivity : MENTAL_ACTIVITIES_LIST) {
            ContentValues activities_values = new ContentValues();
            activities_values.put("NAME", mActivity);
            activities_values.put("AVG", 5);
            activities_values.put("FAV", 0);
            db.insert("MENTAL_ACTIVITIES_INFO", null, activities_values);
        }
    }

    private void initializePhysicalActivitiesTable(SQLiteDatabase db) {
        //Storing activity info in MENTAL_ACTIVITIES_INFO table
        for (String pActivity : PHYSICAL_ACTIVITIES_LIST) {
            ContentValues activities_values = new ContentValues();
            activities_values.put("NAME", pActivity);
            activities_values.put("AVG", 5);
            activities_values.put("FAV", 0);
            db.insert("PHYSICAL_ACTIVITIES_INFO", null, activities_values);
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
        sql += "MONTH INTEGER, ";
        sql += "DAY INTEGER, ";
        sql += "YEAR INTEGER, ";
        sql += "DAY_TYPE INTEGER, ";
        sql += "ACTIVITIES TEXT, ";
        sql += "ACTIVITIES_MOODS TEXT, ";
        sql += "OVERALL_MOOD INTEGER, ";
        sql += "WATER_CONSUMPTION INTEGER, ";
        sql += "NOTES TEXT);";
        return sql;
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
                            cursor.getInt(0),
                            cursor.getInt(1),
                            cursor.getInt(2),
                            cursor.getInt(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getInt(7),
                            cursor.getString(8));
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
}
