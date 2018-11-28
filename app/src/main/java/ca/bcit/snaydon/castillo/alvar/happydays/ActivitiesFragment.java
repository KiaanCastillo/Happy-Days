package ca.bcit.snaydon.castillo.alvar.happydays;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ActivitiesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        boolean fromNotification = false;
        int id = 0;

        Bundle bundle = this.getArguments();
        if (bundle != null){
            fromNotification = bundle.getBoolean("NOTIFICATION");
            id = bundle.getInt("ID");
        }

        if (fromNotification) {
            MainActivity main = (MainActivity) getActivity();

            ActivityPromptFragment detailFragment = new ActivityPromptFragment();
            Bundle activityBundle = new Bundle();
            activityBundle.putSerializable("myActivity", new Activity(id));
            activityBundle.putSerializable("isNotification", true);
            detailFragment.setArguments(activityBundle);
            main.loadFragment(detailFragment);

        }


        return inflater.inflate(R.layout.fragment_activities, null);
    }




}
