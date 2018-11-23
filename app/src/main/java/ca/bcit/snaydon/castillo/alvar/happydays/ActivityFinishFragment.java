package ca.bcit.snaydon.castillo.alvar.happydays;


import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityFinishFragment extends Fragment implements View.OnClickListener {

    private Activity myActivity;
    private ArrayList<ImageButton> moodBtns = new ArrayList<>();

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
        ImageButton selected = v.findViewById(v.getId());
        selected.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
    }
}
