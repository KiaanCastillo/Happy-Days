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
            case 3:
                iv.setImageResource(R.drawable.ic_mood_3);
                break;
            case 4:
                iv.setImageResource(R.drawable.ic_mood_4);
                break;
            default:
                iv.setImageResource(R.drawable.ic_mood_5);
                break;
        }

        String topActivities[] = currentLog.getTopActivities();
        populateBestDisplays(topActivities.length, topActivities, listItem);

        return listItem;
    }

    private int getActivityIcon(String activityName) {
        switch (activityName) {
            case "Reading":
                return R.drawable.ic_reading;
            case "Journaling":
                return R.drawable.ic_journaling;
            case "Mindmaps":
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
        ImageView iv = (ImageView)listItem.findViewById(R.id.logs_best_1);
        iv.setImageResource(getActivityIcon(topActivities[0]));

        if (num > 1) {
            iv = (ImageView)listItem.findViewById(R.id.logs_best_2);
            iv.setImageResource(getActivityIcon(topActivities[1]));
        }

        if (num > 2) {
            iv = (ImageView)listItem.findViewById(R.id.logs_best_3);
            iv.setImageResource(getActivityIcon(topActivities[2]));
        }

    }
}
