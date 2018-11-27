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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LogsFragment extends Fragment {
    private SQLiteDatabase db;
    private Cursor cursor;
    private UserDatabaseHelper dbHelper;

    private ArrayList<Log> logList = new ArrayList<Log>();
    private ListView logsListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_logs, null);
        initLogsFragment(v);
        return v;
    }

    private void initLogsFragment(View v) {
        dbHelper = new UserDatabaseHelper(getContext());
        logsListView = (ListView) v.findViewById(R.id.logs_listview);
        logList = dbHelper.getAllLogs();

        LogsAdapter adapter = new LogsAdapter(getContext(), logList);
        logsListView.setAdapter(adapter);


    }
}
