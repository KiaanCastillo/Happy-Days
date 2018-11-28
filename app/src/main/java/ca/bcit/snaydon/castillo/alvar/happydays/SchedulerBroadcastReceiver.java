package ca.bcit.snaydon.castillo.alvar.happydays;

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

        Log.d("RECEIVER", "Signal to create schedule");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int mon = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //check log in day and month of db
        // if no log set dayChecked to false, else true
        boolean dayChecked = false;

        ActivityScheduler scheduler = new ActivityScheduler(context);

        if (!dayChecked) {

            Log.d("RECEIVER", "Busy level not set, creating schedule");

            // change type to check-in fragment type
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
