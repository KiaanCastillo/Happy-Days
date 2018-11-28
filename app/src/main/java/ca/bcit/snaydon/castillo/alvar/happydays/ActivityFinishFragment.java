package ca.bcit.snaydon.castillo.alvar.happydays;


import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityFinishFragment extends Fragment implements View.OnClickListener {

    private Activity myActivity;
    private ArrayList<ImageButton> moodBtns = new ArrayList<>();
    private int mood;

    public ActivityFinishFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_activity_finish, container, false);

        if (getArguments() != null) {
            myActivity = (Activity) getArguments().getSerializable("myActivity");
        }

        ImageButton btn_mood1 = v.findViewById(R.id.btn_mood1);
        ImageButton btn_mood2 = v.findViewById(R.id.btn_mood2);
        ImageButton btn_mood3 = v.findViewById(R.id.btn_mood3);
        ImageButton btn_mood4 = v.findViewById(R.id.btn_mood4);
        ImageButton btn_mood5 = v.findViewById(R.id.btn_mood5);
        moodBtns.add(btn_mood1);
        moodBtns.add(btn_mood2);
        moodBtns.add(btn_mood3);
        moodBtns.add(btn_mood4);
        moodBtns.add(btn_mood5);
        for (ImageButton imgBtn : moodBtns) {
            imgBtn.setOnClickListener(this);
        }

        Button submitBtn = v.findViewById(R.id.btn_save);
        submitBtn.setOnClickListener(this);

        fillInFields(v);

        return v;
    }

    public void fillInFields(View v) {
        TextView activityName = v.findViewById(R.id.activity_name);
        activityName.setText(myActivity.getName());
        TextView activityType = v.findViewById(R.id.activity_type);
        activityType.setText(myActivity.isPhysical() ? "Physical Activity" : "Mental Activity");
        ImageView activityLogo = v.findViewById(R.id.logo_image);
        activityLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), myActivity.getLogo(), null));
    }

    @Override
    public void onClick(View v) {
        for (ImageButton imgBtn : moodBtns) {
            imgBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
        }
        if (v.getId() == R.id.btn_save)
            finishActivityClick();
        else {
            ImageButton selected = v.findViewById(v.getId());
            selected.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));

            switch (v.getId()) {
                case R.id.btn_mood1:
                    mood = 1;
                    break;
                case R.id.btn_mood2:
                    mood = 2;
                    break;
                case R.id.btn_mood3:
                    mood = 3;
                    break;
                case R.id.btn_mood4:
                    mood = 4;
                    break;
                default:
                    mood = 5;
                    break;
            }
        }
    }

    public void finishActivityClick() {
        // Save Activity in Database
        UserDatabaseHelper dbHelper = new UserDatabaseHelper(getContext());
        TextView tv = ((MainActivity) getActivity()).findViewById(R.id.activity_name);
        String notes = ((MainActivity) getActivity()).findViewById(R.id.edit_notes).toString();
        dbHelper.addFinishedActivity(dbHelper.getReadableDatabase(), tv.getText().toString(), mood, notes);
        ((MainActivity) getActivity()).loadFragment(new HomeFragment());
    }


}
