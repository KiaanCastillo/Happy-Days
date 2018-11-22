package ca.bcit.snaydon.castillo.alvar.happydays;


import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityPromptFragment extends Fragment implements View.OnClickListener {

    private Activity myActivity;
    private boolean isNotification;

    private Button startBtn;
    private Button anotherBtn;
    private Button stopBtn;

    public ActivityPromptFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_activity_prompt, container, false);
        if (getArguments() != null) {
            myActivity = (Activity) getArguments().getSerializable("myActivity");
            isNotification = (boolean) getArguments().getSerializable("isNotification");
        }

        Log.e("PROMPT_FRAGMENT", myActivity.getName());

        startBtn = v.findViewById(R.id.btn_start);
        startBtn.setOnClickListener(this);
        anotherBtn = v.findViewById(R.id.btn_another);
        anotherBtn.setOnClickListener(this);
        stopBtn = v.findViewById(R.id.btn_stop);
        stopBtn.setOnClickListener(this);

        fillInFields(v);

        Log.e("PASSED_ACTIVITY", myActivity.getName() + "isNotification: " + isNotification);

        return v;
    }

    public void fillInFields(View v) {
        TextView activityName = v.findViewById(R.id.activity_name);
        activityName.setText(myActivity.getName());
        TextView activityType = v.findViewById(R.id.activity_type);
        activityType.setText(myActivity.isPhysical() ? "Physical Activity" : "Mental Activity");
        ImageView activityLogo = v.findViewById(R.id.logo_image);
        activityLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), myActivity.getLogo(), null));
        if (!isNotification) {
            TextView prompt1 = v.findViewById(R.id.text_prompt1);
            prompt1.setText(getString(R.string.prompt_start));
            prompt1.setTextColor(Color.parseColor("#D3D3D3"));
            TextView prompt2 = v.findViewById(R.id.text_prompt2);
            prompt2.setVisibility(View.GONE);
            stopBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_start:
                ActivityDetailFragment promptFragment = new ActivityDetailFragment();
                Bundle activityBundle = new Bundle();
                activityBundle.putSerializable("myActivity", myActivity);
                promptFragment.setArguments(activityBundle);
                break;
            case R.id.btn_another:
                ((MainActivity) getActivity()).loadFragment(new ActivitiesFragment());
                break;
            case R.id.btn_stop:

        }
    }
}
