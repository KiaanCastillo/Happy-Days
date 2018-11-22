package ca.bcit.snaydon.castillo.alvar.happydays;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityPromptFragment extends Fragment implements View.OnClickListener {

    private Activity myActivity;
    private boolean isNotification;

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

        Button startBtn = v.findViewById(R.id.btn_start);
        startBtn.setOnClickListener(this);
        Button anotherBtn = v.findViewById(R.id.btn_another);
        anotherBtn.setOnClickListener(this);
        Button stopBtn = v.findViewById(R.id.btn_stop);
        stopBtn.setOnClickListener(this);

        Log.e("PASSED_ACTIVITY", myActivity.getName() + "isNotification: " + isNotification);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_start:
                ActivityDetailFragment promptFragment = new ActivityDetailFragment();
                Bundle activityBundle = new Bundle();
                activityBundle.putSerializable("myActivity", myActivity);
                promptFragment.setArguments(activityBundle);
                ((MainActivity) getActivity()).loadFragment(promptFragment);
                break;
            case R.id.btn_another:
                break;
            case R.id.btn_stop:

        }
    }
}
