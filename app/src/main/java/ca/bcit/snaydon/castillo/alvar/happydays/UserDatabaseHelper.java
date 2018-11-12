package ca.bcit.snaydon.castillo.alvar.happydays;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
