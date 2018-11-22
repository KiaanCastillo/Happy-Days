package ca.bcit.snaydon.castillo.alvar.happydays;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityDetailFragment extends Fragment implements View.OnClickListener {

    private Activity myActivity;

    public ActivityDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_activity_detail, container, false);
        if (getArguments() != null) {
            myActivity = (Activity) getArguments().getSerializable("myActivity");
        }

        Log.e("DETAIL_FRAGMENT", myActivity.getName());

        Button finishBtn = v.findViewById(R.id.btn_finish);
        finishBtn.setOnClickListener(this);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        ActivityFinishFragment finishFragment = new ActivityFinishFragment();
        Bundle activityBundle = new Bundle();
        activityBundle.putSerializable("myActivity", myActivity);
        finishFragment.setArguments(activityBundle);
        ((MainActivity) getActivity()).loadFragment(finishFragment);
    }
}
