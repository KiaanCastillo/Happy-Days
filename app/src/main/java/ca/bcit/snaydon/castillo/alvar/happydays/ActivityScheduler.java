package ca.bcit.snaydon.castillo.alvar.happydays;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static ca.bcit.snaydon.castillo.alvar.happydays.MainActivity.CHANNEL_ID;

public class ActivityScheduler {
    static final int CHILL = 0;
    static final int NORMAL = 1;
    static final int BUSY = 2;
    Context context;

    public ActivityScheduler(Context c) {
        context = c;
    }

    public void createNotification(int typeId, int hour, int minute, String name, String description) {

        Date now = new Date();
        int notificationId = Integer.parseInt(new SimpleDateFormat("ddHHmmss",  Locale.US).format(now));

        Log.d("Notification", "Creating notification, id: " + notificationId);

        // change to be addaptive to typeId
        Intent startActivity = new Intent(context, MainActivity.class);

        // temp id should be fragment id if checkin, or random if another activity
        PendingIntent activity = PendingIntent.getActivity(context, typeId, startActivity, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_activities_icon)
                .setContentTitle(name)
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(activity)
                .setAutoCancel(true);


        Notification notification = mBuilder.build();

        Intent notificationIntent = new Intent(context, ActivityReceiver.class);
        notificationIntent.putExtra(ActivityReceiver.NOTIFICATION_ID, notificationId);
        notificationIntent.putExtra(ActivityReceiver.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        Log.d("Notification", "Notification set for: " + hour + ":" + minute);

        // if check in, repeat every 20 minutes
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    public void scheduleDailyCheck() {

        // if time is later then morning check in time, set for next few minutes

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);

        if ((hour >= 7) || (hour > 6 && min > 30)) {

            createNotification(0, hour, min + 1, "Time for a check in", "Ready to start your day?");
        } else {

            createNotification(0,6, 30, "Time for a check in", "Ready to start your day?");
        }



    }

    public void generateActivties(int[] notifications, double bias) {
        // set activity notification by type and time

        double roll;

        int i = 0;
        while (i != 0) {
            roll = Math.random() + bias;
            if (roll > 0.5) {
                //phyisical activity
            } else {
                //mental activity
            }

        }

    }

    public void runScheduleAlgorithm() {
        // generate activities for the day based on busyness

        // get busy level
        int busyLevel = 0;

        int[] notificationTimes = {0,0,0,0};
        double bias = 0;

        switch (busyLevel) {
            case BUSY:
                bias = 0.3;
                notificationTimes[0] = 12;
                notificationTimes[1] = 18;
                generateActivties(notificationTimes, bias);
                break;
            case NORMAL:
                notificationTimes[0] = 10;
                notificationTimes[1] = 15;
                notificationTimes[2] = 19;
                generateActivties(notificationTimes, bias);
                break;
            case CHILL:
                bias = -0.3;
                notificationTimes[0] = 9;
                notificationTimes[1] = 14;
                notificationTimes[2] = 18;
                generateActivties(notificationTimes, bias);
                break;
                default:
                    break;
        }


    }

    public void rebuildSchedule() {
        // create notifications based on activities scheduled in the database

    }


}
