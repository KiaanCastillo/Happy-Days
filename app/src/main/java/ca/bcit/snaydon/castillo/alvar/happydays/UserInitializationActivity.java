package ca.bcit.snaydon.castillo.alvar.happydays;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class UserInitializationActivity extends AppCompatActivity {

    public static final int MAX_FAVS = 3;

    private UserDatabaseHelper dbHelper;

    EditText etFirst;
    EditText etLast;

    ArrayList<CheckBox> favMental;
    ArrayList<CheckBox> favPhysical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinitialization);

        dbHelper = new UserDatabaseHelper(this);

        favMental = new ArrayList<>();
        favPhysical = new ArrayList<>();

        etFirst = (EditText) findViewById(R.id.first_name_init);
        etLast = (EditText) findViewById(R.id.last_name_init);

    }

    public void onCheckboxClickedMental(View view) {
        if (favMental.size() == MAX_FAVS) {
            CheckBox temp = (CheckBox) favMental.get(0);
            favMental.remove(0);
            temp.setChecked(false);
        }


        favMental.add((CheckBox) view);
        ((CheckBox) view).setChecked(true);
    }

    public void onCheckboxClickedPhysical(View view) {
        if (favPhysical.size() == MAX_FAVS) {
            CheckBox temp = (CheckBox) favPhysical.get(0);
            favPhysical.remove(0);
            temp.setChecked(false);
        }

        favPhysical.add((CheckBox) view);
        ((CheckBox) view).setChecked(true);
    }

    public void createUser(View view) {
        if (authenticate()) {
            User user = new User(etFirst.getText().toString(), etLast.getText().toString());
            ArrayList<String> favMentalStrings = new ArrayList<>();
            ArrayList<String> favPhysicalStrings = new ArrayList<>();
            for (View v : favMental)
                favMentalStrings.add(getActivity(v));

            for (View v : favPhysical)
                favPhysicalStrings.add(getActivity(v));

            dbHelper.initializeUser(dbHelper.getReadableDatabase(), user, favMentalStrings, favPhysicalStrings);
            Intent i = new Intent(UserInitializationActivity.this, MainActivity.class);
            startActivity(i);
        }
    }

    private boolean authenticate() {
        if (etFirst.getText().toString().isEmpty())
            return false;

        if (etLast.getText().toString().isEmpty())
            return false;

        if (favMental.size() < 3)
            return false;

        if (favPhysical.size() < 3)
            return false;

        return true;
    }

    public String getActivity(View view) {
        switch (view.getId()) {
            case R.id.pick_reading:
                return "Reading";
            case R.id.pick_meditating:
                return "Meditating";
            case R.id.pick_mindmap:
                return "Mindmap";
            case R.id.pick_stretching:
                return "Stretching";
            case R.id.pick_biking:
                return "Biking";
            case R.id.pick_running:
                return "Running";
            case R.id.pick_workout:
                return "Workout";
            default:
                return "Walking";
        }
    }
}
