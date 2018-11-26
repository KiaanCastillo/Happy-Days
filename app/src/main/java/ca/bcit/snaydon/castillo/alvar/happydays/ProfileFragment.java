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
import android.widget.TextView;

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
    }

}
