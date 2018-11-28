package ca.bcit.snaydon.castillo.alvar.happydays;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

public class UserInitializationActivity extends AppCompatActivity {

    public static final int MAX_FAVS = 3;

    ArrayList<CheckBox> favMental;
    ArrayList<CheckBox> favPhysical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinitialization);

        favMental = new ArrayList<>();
        favPhysical = new ArrayList<>();

    }

    public void onCheckboxClickedMental(View view) {
        if (favMental.size() == 3) {
            CheckBox temp = (CheckBox) favMental.get(0);
            favMental.remove(0);
            temp.setChecked(false);
        }


        favMental.add((CheckBox) view);
        ((CheckBox) view).setChecked(true);
    }

    public void onCheckboxClickedPhysical(View view) {
        if (favPhysical.size() == 3) {
            CheckBox temp = (CheckBox) favPhysical.get(0);
            favPhysical.remove(0);
            temp.setChecked(false);
        }

        favPhysical.add((CheckBox) view);
        ((CheckBox) view).setChecked(true);
    }

    public String getActivity(View v) {
        switch (v.getId()) {
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
