package ca.bcit.snaydon.castillo.alvar.happydays;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final int MAX_FAVOURITES = 6;
    private SQLiteDatabase db;
    private Cursor cursor;
    static final String CHANNEL_ID = "happy_days";
    private UserDatabaseHelper dbHelper;
    BottomNavigationView bottomNavigationView;
    boolean firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //CHECK IF LOG ALREADY EXISTS FOR THIS DAY
        //IF NOT, PROMPT FOR BUSY-NESS THEN MAKE A NEW LOG
        firstTime = true;
        if (!firstTime) {
        dbHelper = new UserDatabaseHelper(this);
        User user = new User("Bob", "Parker");
        dbHelper.initializeUser(dbHelper.getReadableDatabase(), user);
        Log log = new Log(11, 2, 2018, 3, "Running Biking Mindmap Stretching", "5 5 4 2", 4, "Yeet");
        dbHelper.insertLog(dbHelper.getReadableDatabase(), log);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        createNotificationChannel(this);

        Intent intent = getIntent();
        boolean fromNotification = intent.getBooleanExtra("NOTIFICATION", false);
        int activityId = intent.getIntExtra("ACTIVITY_ID", 0);

        if (fromNotification) {

            ActivitiesFragment actFrag = new ActivitiesFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean("NOTIFICATION", true);
            bundle.putInt("ID", activityId);
            actFrag.setArguments(bundle);

            loadFragment(actFrag);
        } else {

            loadFragment(new HomeFragment());
        }
        } else {
            Intent i = new Intent(MainActivity.this, OnboardingActivity.class);
            startActivity(i);
        }

    }

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
            return true;
        }
        return false;
    }

    public void loadActivities(View v) {
        Fragment fragment = new ActivitiesFragment();
        loadFragment(fragment);

        //Un checks all other navigation items
        int size = bottomNavigationView.getMenu().size();
        for (int i = 0; i < size; i++)
            bottomNavigationView.getMenu().getItem(i).setCheckable(false).setChecked(false);
    }

    // Will return appropriate fragment based on the fragment Id passed to the main activity through
    // a notification intent
    public Fragment getFragmentFromId(int fragmentId) {

        Fragment newFragment = null;

        switch (fragmentId) {

            default:
                break;
        }

        return newFragment;
    }

    private void createNotificationChannel(final Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = "HappyDaysChannel";
            String description = "Channel description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_logs:
                fragment = new LogsFragment();
                break;
            case R.id.navigation_profile:
                fragment = new ProfileFragment();
                break;
            case R.id.navigation_menu:
                fragment = new MenuFragment();
                break;
        }

        int size = bottomNavigationView.getMenu().size();
        for (int i = 0; i < size; i++)
            bottomNavigationView.getMenu().getItem(i).setCheckable(true);

        return loadFragment(fragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (cursor != null)
            cursor.close();

        if (db != null)
            db.close();
    }

    public void onActivityClick(View view) {
        ActivityPromptFragment promptFragment = new ActivityPromptFragment();
        Bundle activityBundle = new Bundle();
        activityBundle.putSerializable("myActivity", new Activity(view.getId()));
        activityBundle.putSerializable("isNotification", false);
        promptFragment.setArguments(activityBundle);
        loadFragment(promptFragment);
    }

    public void finishActivityClick(View view) {
        // Save Activity in Database
        loadFragment(new HomeFragment());
    }

    public void onDebugClick(View view) {
        ActivityScheduler scheduler = new ActivityScheduler(this);
        scheduler.debugNotification();
        //scheduler.runScheduleAlgorithm();
    }
}
