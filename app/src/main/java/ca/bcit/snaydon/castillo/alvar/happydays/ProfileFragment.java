package ca.bcit.snaydon.castillo.alvar.happydays;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    private SQLiteDatabase db;
    private Cursor cursor;
    private UserDatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, null);
        initProfileFragment(v);
        return v;
    }

    private void initProfileFragment(View v) {
        dbHelper = new UserDatabaseHelper(getContext());
        User user = dbHelper.getUser();
        TextView tv = v.findViewById(R.id.profile_first_name);
        tv.setText(user.getFirst_name());

        tv = v.findViewById(R.id.profile_last_name);
        tv.setText(user.getLast_name());

        ArrayList<String> favActivities = dbHelper.getFavouriteActivities();
        ImageView iv = (ImageView) v.findViewById(R.id.m_fave_1);
        iv.setImageResource(getActivityIcon(favActivities.get(0)));

        iv = (ImageView) v.findViewById(R.id.m_fave_2);
        iv.setImageResource(getActivityIcon(favActivities.get(1)));

        iv = (ImageView) v.findViewById(R.id.m_fave_3);
        iv.setImageResource(getActivityIcon(favActivities.get(2)));

        iv = (ImageView) v.findViewById(R.id.p_fave_1);
        iv.setImageResource(getActivityIcon(favActivities.get(3)));

        iv = (ImageView) v.findViewById(R.id.p_fave_2);
        iv.setImageResource(getActivityIcon(favActivities.get(4)));

        iv = (ImageView) v.findViewById(R.id.p_fave_3);
        iv.setImageResource(getActivityIcon(favActivities.get(5)));
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

}
