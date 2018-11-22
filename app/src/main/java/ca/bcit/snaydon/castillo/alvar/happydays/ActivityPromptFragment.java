package ca.bcit.snaydon.castillo.alvar.happydays;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityPromptFragment extends Fragment {

    private Activity myActivity;
    private boolean isNotification;

    public ActivityPromptFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            myActivity = (Activity) getArguments().getSerializable("myActivity");
            isNotification = (boolean) getArguments().getSerializable("isNotification");
        }

        Log.e("PASSED_ACTIVITY", myActivity.getName() + "isNotification: " + isNotification);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_prompt, container, false);
    }

}
