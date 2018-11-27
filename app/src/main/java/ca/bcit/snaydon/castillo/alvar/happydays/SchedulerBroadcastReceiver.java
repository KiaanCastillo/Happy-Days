package ca.bcit.snaydon.castillo.alvar.happydays;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import java.util.Calendar;

public class SchedulerBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //should be asynchronous but idk how
        //createNotificationChannel(context);

        System.out.println("RECEIVER PROCCED");

        Log.d("RECEIVER", "Signal to create schedule");

        // businessLevel should be pulled from db
        int busyLevel = 0;

        ActivityScheduler scheduler = new ActivityScheduler(context);

        if (busyLevel == 0) {

            Log.d("RECEIVER", "Busy level not set, creating schedule");

            scheduler.scheduleDailyCheck();

        } else {

            Log.d("RECEIVER", "Busy level set, rebuilding schedule");

            scheduler.rebuildSchedule();
        }

    }



//    private static class Task extends AsyncTask <Context, Void, Void> {
//
//        private final PendingResult pendingResult;
//        private final Intent intent;
//
//        private Task(PendingResult pendingResult, Intent intent) {
//            this.pendingResult = pendingResult;
//            this.intent = intent;
//        }
//
//        @Override
//        protected void doInBackground(Context... context) {
//
//
//
//        }
//
//        @Override
//        protected void onPostExecute(Void params) {
//            super.onPostExecute(params);
//
//            pendingResult.finish();
//        }
//    }
}
