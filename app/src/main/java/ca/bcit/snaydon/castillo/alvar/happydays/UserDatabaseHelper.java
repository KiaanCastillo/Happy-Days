package ca.bcit.snaydon.castillo.alvar.happydays;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

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

    }

    private String createUserInfoTable() {
        String sql = "";
        sql += "CREATE TABLE USER_INFO (";
        sql += "FIRST_NAME TEXT, ";
        sql += "LAST_NAME TEXT);";

        return sql;
    }

    private void initializeUser(SQLiteDatabase db, User user) {
        //Storing user info in USER_INFO table
        ContentValues user_values = new ContentValues();
        user_values.put("FIRST_NAME", user.getFirst_name());
        user_values.put("LAST_NAME", user.getLast_name());
        db.insert(USER_INFO, null, user_values);

        //Storing activity info in MENTAL_ACTIVITIES_INFO table
        ContentValues activities_values = new ContentValues();
        List<Activity> m_activities_list = user.getM_activities_list();
        for (int i = 0; i < m_activities_list.size(); i++) {
            activities_values.put("NAME", m_activities_list.get(i).getName());
            activities_values.put("AVG", m_activities_list.get(i).getAvg_mood());
            activities_values.put("FAV", m_activities_list.get(i).isIs_favourite());
        }
        db.insert(MENTAL_ACTIVITIES_INFO, null, activities_values);


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
        sql += "DATE TEXT, ";
        sql += "DAY_TYPE INTEGER, ";
        sql += "ACTIVITIES TEXT, ";
        sql += "ACTIVITIES_MOODS TEXT, ";
        sql += "OVERALL_MOOD INTEGER, ";
        sql += "WATER_CONSUMPTION INTEGER, ";
        sql += "NOTES TEXT);";
        return sql;
    }
}
