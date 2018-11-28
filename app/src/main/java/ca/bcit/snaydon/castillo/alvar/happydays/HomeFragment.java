package ca.bcit.snaydon.castillo.alvar.happydays;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    private SQLiteDatabase db;
    private Cursor cursor;
    private UserDatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, null);
        initHomeFragment(v);
        return v;
    }

    private void initHomeFragment(View v) {
        dbHelper = new UserDatabaseHelper(getContext());
        User user = dbHelper.getUser();
        TextView tv = v.findViewById(R.id.home_first_name);
        tv.setText(user.getFirst_name());

//        tv = v.findViewById(R.id.home_last_name);
//        tv.setText(user.getFirst_name());
    }
}
