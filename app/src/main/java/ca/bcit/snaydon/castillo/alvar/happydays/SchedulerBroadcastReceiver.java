package ca.bcit.snaydon.castillo.alvar.happydays;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.Calendar;

public class SchedulerBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //should be asynchronous but idk how

        // businessLevel should be pulled from db
        int businessLevel = 0;

        ActivityScheduler scheduler = new ActivityScheduler(context);

        if (businessLevel == 0) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());

            long curTime = calendar.getTimeInMillis();

            calendar.set(Calendar.HOUR_OF_DAY, 6);

            long wakeTime = calendar.getTimeInMillis();

            String title = "Time for a check in";
            String description = "Ready for a happy day?";

            // change type to check-in fragment type
            if (curTime < wakeTime)
                scheduler.createNotification(" ", 6, 0, title, description);
            else
                scheduler.createNotification(" ", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), title, description);

        } else {

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
