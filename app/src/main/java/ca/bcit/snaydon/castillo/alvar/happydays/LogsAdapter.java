package ca.bcit.snaydon.castillo.alvar.happydays;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LogsAdapter extends ArrayAdapter<Log> {

    Context context;
    private List<Log> logsList;

    public LogsAdapter(Context context, ArrayList<Log> logs) {
        super(context, 0, logs);
        this.context = context;
        logsList = logs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.log_list_row, parent, false);

        }

        Log currentLog = logsList.get(position);

        ImageView iv = (ImageView)listItem.findViewById(R.id.log_overall_mood_display);
        switch (currentLog.getOverallMood()) {
            case 1:
                iv.setImageResource(R.drawable.ic_mood_1);
                break;
            case 2:
                iv.setImageResource(R.drawable.ic_mood_2);
                break;
            case 5:
                iv.setImageResource(R.drawable.ic_mood_5);
                break;
            case 4:
                iv.setImageResource(R.drawable.ic_mood_4);
                break;
            default:
                iv.setImageResource(R.drawable.ic_mood_3);
                break;
        }

        String topActivities[] = currentLog.getTopActivities();
        populateBestDisplays(topActivities.length, topActivities, listItem);
        TextView tv = (TextView) listItem.findViewById(R.id.log_date);
        tv.setText(dateMaker(currentLog.getMonth(), currentLog.getDay()));

        return listItem;
    }

    private int getActivityIcon(String activityName) {
        switch (activityName) {
            case "Reading":
                return R.drawable.ic_reading;
            case "Journaling":
                return R.drawable.ic_journaling;
            case "Mindmap":
                return R.drawable.ic_mindmap;
            case "Stretching":
                return R.drawable.ic_stretching;
            case "Meditating":
                return R.drawable.ic_meditating;
            case "Walking":
                return R.drawable.ic_walking;
            case "Biking":
                return R.drawable.ic_biking;
            case "Running":
                return R.drawable.ic_running;
            default:
                return R.drawable.ic_workout;
        }
    }

    private void populateBestDisplays(int num, String topActivities[], View listItem) {
        ImageView iv;

        if (topActivities[0] != null) {
            iv = (ImageView) listItem.findViewById(R.id.logs_best_1);
            iv.setImageResource(getActivityIcon(topActivities[0]));
        }

        if (topActivities[1] != null) {
            iv = (ImageView)listItem.findViewById(R.id.logs_best_2);
            iv.setImageResource(getActivityIcon(topActivities[1]));
        }

        if (topActivities[2] != null) {
            iv = (ImageView)listItem.findViewById(R.id.logs_best_3);
            iv.setImageResource(getActivityIcon(topActivities[2]));
        }
    }

    private String dateMaker(int month, int day) {
        String date =  "";
        switch (month) {
            case 0:
                date += "Jan";
                break;
            case 1:
                date += "Feb";
                break;
            case 2:
                date += "Mar";
                break;
            case 3:
                date += "Apr";
                break;
            case 4:
                date += "May";
                break;
            case 5:
                date += "Jun";
                break;
            case 6:
                date += "Jul";
                break;
            case 7:
                date += "Aug";
                break;
            case 8:
                date += "Sep";
                break;
            case 9:
                date += "Oct";
                break;
            case 10:
                date += "Nov";
                break;
            default:
                date += "Dec";
                break;
        }

        date += " " + day;

        return date;
    }
}
