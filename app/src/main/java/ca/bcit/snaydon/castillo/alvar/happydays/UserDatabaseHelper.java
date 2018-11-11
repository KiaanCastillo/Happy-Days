package ca.bcit.snaydon.castillo.alvar.happydays;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    /**
     * Name of the database
     */
    private static final String DB_NAME = "userdatabase.sqlite";
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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private String createUserInfoTable() {
        String sql = "";
        sql += "CREATE TABLE USER_INFO (";
        sql += "FIRST_NAME TEXT";
        sql += "LAST_NAME TEXT, ";

        //Don't know if there is a better way to do this...
        sql += "MENTAL_ACTIVITY_ONE TEXT, ";
        sql += "MENTAL_ACTIVITY_TWO TEXT, ";
        sql += "MENTAL_ACTIVITY_THREE TEXT, ";

        sql += "PHYSICAL_ACTIVITY_ONE TEXT, ";
        sql += "PHYSICAL_ACTIVITY_TWO TEXT, ";
        sql += "PHYSICAL_ACTIVITY_THREE TEXT); ";
        return sql;
    }

    private String createLogsTable() {
        String sql = "";
        sql += "CREATE TABLE LOGS (";
        sql += "DATE TEXT";
        sql += "DAY_TYPE INTEGER";
        sql += "FIRST_NAME TEXT";
        sql += "PHYSICAL_ACTIVITY_THREE TEXT); ";
        return sql;
    }

    private String createUserActivitiesInfoTable() {
        String sql = "";
        sql += "CREATE TABLE USER_ACTIVITIES_INFO (";
        sql += "FIRST_NAME TEXT";
        sql += "LAST_NAME TEXT, ";

        //Don't know if there is a better way to do this...
        sql += "MENTAL_ACTIVITY_ONE TEXT); ";
        sql += "MENTAL_ACTIVITY_TWO TEXT); ";
        sql += "MENTAL_ACTIVITY_THREE TEXT); ";

        sql += "PHYSICAL_ACTIVITY_ONE TEXT); ";
        sql += "PHYSICAL_ACTIVITY_TWO TEXT); ";
        sql += "PHYSICAL_ACTIVITY_THREE TEXT); ";
        return sql;
    }
}
