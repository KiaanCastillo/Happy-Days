package ca.bcit.snaydon.castillo.alvar.happydays;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class ProfileFragment extends Fragment {

    public static final int MAX_FAVS = 3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        populate();
        return inflater.inflate(R.layout.fragment_profile, null);
    }

    private void populate() {
        SQLiteOpenHelper helper = new UserDatabaseHelper(getContext());
        String[] m_favourites = new String[MAX_FAVS];
        String[] p_favourites = new String[MAX_FAVS];

        try {
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select DISTINCT NAME from MENTAL_ACTIVITIES_INFO WHERE FAV IS 1", null);
            int ndx = 0;
            if (cursor.moveToFirst()) {
                do {
                    m_favourites[ndx++] = cursor.getString(0);
                } while (cursor.moveToNext());
            }

            ndx = 0;
            cursor = db.rawQuery("select DISTINCT NAME from PHYSICAL_ACTIVITIES_INFO WHERE FAV IS 1", null);
            if (cursor.moveToFirst()) {
                do {
                    p_favourites[ndx++] = cursor.getString(0);
                } while (cursor.moveToNext());
            }

        } catch (SQLiteException sqlex) {
            String msg = "[MainActivity / populate(profilefragment)] DB unavailable";
            msg += "\n\n" + sqlex.toString();

            Toast t = Toast.makeText(getContext(), msg, Toast.LENGTH_LONG);
            t.show();
        }

        //Mental Activities
        ImageView iv = (ImageView) getView().findViewById(R.id.m_fave_1);
        iv.setImageResource(getIcon(m_favourites[0], 0));
        iv = (ImageView) getView().findViewById(R.id.m_fave_2);
        iv.setImageResource(getIcon(m_favourites[1], 0));
        iv = (ImageView) getView().findViewById(R.id.m_fave_3);
        iv.setImageResource(getIcon(m_favourites[2], 0));

        //Physical Activities
        iv = (ImageView) getView().findViewById(R.id.p_fave_1);
        iv.setImageResource(getIcon(p_favourites[0], 0));
        iv = (ImageView) getView().findViewById(R.id.p_fave_2);
        iv.setImageResource(getIcon(p_favourites[1], 0));
        iv = (ImageView) getView().findViewById(R.id.p_fave_3);
        iv.setImageResource(getIcon(p_favourites[2], 0));

    }

    private int getIcon(String activityName, int type) {
        if (type == 0) { // 0 is for mental, 1 is for physical
            if (activityName.compareTo("reading") == 0)
                return R.drawable.ic_reading;
            if (activityName.compareTo("meditating") == 0)
                return R.drawable.ic_meditating;
            if (activityName.compareTo("mindmap") == 0)
                return R.drawable.ic_mindmap;
            if (activityName.compareTo("stretching") == 0)
                return R.drawable.ic_stretching;
            if (activityName.compareTo("journaling") == 0)
                return R.drawable.ic_journaling;
        }
        if (activityName.compareTo("biking") == 0)
            return R.drawable.ic_biking;
        if (activityName.compareTo("running") == 0)
            return R.drawable.ic_running;
        if (activityName.compareTo("workingout") == 0)
            return R.drawable.ic_workout;
        return R.drawable.ic_walking;
    }
}
