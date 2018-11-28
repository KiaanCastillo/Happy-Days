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

    public void createNotification(String activityType, int hour, int minute, String name, String description) {

        int activityId = 0;

        if (activityType.equals("Reading"))
            activityId = R.id.btn_reading;
        else if (activityType.equals("Journaling"))
            activityId = R.id.btn_journaling;
        else if (activityType.equals("Mindmaps"))
            activityId = R.id.btn_mindmap;
        else if (activityType.equals("Stretching"))
            activityId = R.id.btn_stretching;
        else if (activityType.equals("Meditating"))
            activityId = R.id.btn_meditating;
        else if (activityType.equals("Walking"))
            activityId = R.id.btn_walking;
        else if (activityType.equals("Biking"))
            activityId = R.id.btn_biking;
        else if (activityType.equals("Running"))
            activityId = R.id.btn_running;
        else if (activityType.equals("Workout"))
            activityId = R.id.btn_workout;

        Log.d("NOTIFICATION", "Type: " + activityId);

        Date now = new Date();
        int notificationId = (int) System.currentTimeMillis() + ((int) Math.random() * 1000);

        Log.d("Notification", "Creating notification, id: " + notificationId);

        // change to be addaptive to typeId
        Intent startActivity = new Intent(context, MainActivity.class);
        startActivity.putExtra("ACTIVITY_ID", activityId);
        startActivity.putExtra("NOTIFICATION", true);

        // temp id should be fragment id if checkin, or random if another activity
        PendingIntent activity = PendingIntent.getActivity(context, activityId, startActivity, PendingIntent.FLAG_UPDATE_CURRENT);

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

            createNotification("default", hour, min + 1, context.getString(R.string.note_title), context.getString(R.string.note_title));
        } else {

            createNotification("default",6, 30, context.getString(R.string.note_title), context.getString(R.string.note_title));
        }



    }

    public void generateActivties(int[] notifications, double bias) {
        // set activity notification by type and time

        double roll;

        String type;
        int i = 0;
        while (notifications[i] != 0) {
            roll = Math.random() + bias;
            Log.d("NOTIFICATION", "Type Roll " + roll);
            if (roll > 0.5) {
                //phyisical activity
                type = generateRandomPhysical();

            } else {
                //mental activity
                type = generateRandomMental();
            }

            //schedule in db
            createNotification(type, notifications[i], 0, context.getString(R.string.note_title), getDescription(type));

//            Calendar calendar = Calendar.getInstance();
//            calendar.setTimeInMillis(System.currentTimeMillis());
//            int hour = calendar.get(Calendar.HOUR_OF_DAY);
//            int min = calendar.get(Calendar.MINUTE);
            //createNotification(type, hour, min, context.getString(R.string.note_title), getDescription(type));

            i++;
        }

    }

    public void runScheduleAlgorithm() {
        // generate activities for the day based on busyness

        // get busy level
        int busyLevel = 2;

        int[] notificationTimes = {0,0,0,0};
        double bias = 0;

        switch (busyLevel) {
            case BUSY:
                bias = -0.4;
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
                bias = 0.3;
                notificationTimes[0] = 9;
                notificationTimes[1] = 14;
                notificationTimes[2] = 18;
                generateActivties(notificationTimes, bias);
                break;
                default:
                    break;
        }


    }

    private String generateRandomPhysical() {
        int sum = 0;
        int walking = (sum += 2); //avg mood + 2 if fav
        int biking = (sum += 2);
        int running = (sum += 2);
        sum += 2; // for workout

        double roll = Math.random() * sum;

        Log.d("NOTIFICATION", "Activity Roll " + roll);

        if (roll < walking) {
            //schedule walking in db

            return "Walking";

        } else if (roll < biking) {
            //schedule biking in db

            return "Biking";
        } else if (roll < running) {
            //schedule running in db

            return "Running";

        } else  {
            //schedule workout in db

            return "Workout";
        }
    }

    private String generateRandomMental() {

        int sum = 0;
        int reading = (sum += 2);
        int journaling = (sum += 2);
        int mindmap = (sum += 2);
        int stretching = (sum += 2);
        sum += 2; // for meditating

        double roll = Math.random() * sum;

        Log.d("NOTIFICATION", "Activity Roll " + roll);

        if (roll < reading) {
            //schedule reading
            return "Reading";

        } else if (roll < journaling) {
            //schedule journalling

            return "Journaling";

        } else if (roll < mindmap) {
            //schedule mindmap

            return "Mindmaps";

        } else if (roll < stretching) {
            //schedule stretching

            return "Stretching";

        } else {
            //schedule meditating

            return "Meditating";

        }
    }

    public void rebuildSchedule() {
        // create notifications based on activities scheduled in the database

        // for entries in table
        // call createNotification of type and time
    }

    public void debugNotification() {
        double roll;

        String type;
            roll = Math.random();
            if (roll > 0.5) {
                //phyisical activity
                type = generateRandomPhysical();

            } else {
                //mental activity
                type = generateRandomMental();
            }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);

            //schedule in db
            createNotification(type, hour, min, context.getString(R.string.note_title), getDescription(type));
    }

    private String getDescription(String name) {
        String desc;

        if (name.equals("Reading"))
            desc = "Read anything interesting lately?";
        else if (name.equals("Journaling"))
            desc = "Why don't jot down some of those thoughts?";
        else if (name.equals("Mindmaps"))
            desc = "Take a minute and map out your mind.";
        else if (name.equals("Stretching"))
            desc = "Sore muscles? Try loosing up your body.";
        else if (name.equals("Meditating"))
            desc = "Why not take a minute and relax your mind.";
        else if (name.equals("Walking"))
            desc = context.getString(R.string.note_walking);
        else if (name.equals("Biking"))
            desc = context.getString(R.string.note_biking);
        else if (name.equals("Running"))
            desc = "Now would be a good time for a run.";
        else if (name.equals("Workout"))
            desc = "Stressed? A short workout could help with that.";
        else
            desc = "Time for a check in.";

        return desc;
    }

}
