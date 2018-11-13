package ca.bcit.snaydon.castillo.alvar.happydays;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;
import android.support.v4.app.NotificationCompat;

import java.util.Random;

import static ca.bcit.snaydon.castillo.alvar.happydays.MainActivity.CHANNEL_ID;

public class ActivityScheduler {
    static final int TEMP_ID = 1;
    Context context;

    public ActivityScheduler(Context c) {
        context = c;
    }

    public void createNotification(int typeId, int hour, int minute) {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_activities_icon)
                .setContentTitle("Notification default")
                .setContentText("Notification content")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent startActivity = new Intent(context, MainActivity.class);
        // temp id should be fragment id if checkin, or random if another activity
        PendingIntent pending = PendingIntent.getActivity(context, TEMP_ID, startActivity, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        // if check in, repeat every 20 minutes
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);
    }

    public void scheduleDailyCheck() {
        // if time is later then morning check in time, set for next few minutes


    }

    public void scheduleActivity() {
        // set activity notification by type and time

    }

    public void runScheduleAlgorithm() {
        // generate activities for the day based on busyness

    }

    public void rebuildSchedule() {
        // create notifications based on activities scheduled in the database

    }
}
