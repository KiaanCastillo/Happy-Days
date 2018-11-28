package ca.bcit.snaydon.castillo.alvar.happydays;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


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

        Button finishBtn = v.findViewById(R.id.btn_detail);
        finishBtn.setOnClickListener(this);

        fillInFields(v);

        // Inflate the layout for this fragment
        return v;
    }

    public void fillInFields(View v) {
        TextView activityName = v.findViewById(R.id.activity_name);
        activityName.setText(myActivity.getName());
        TextView activityType = v.findViewById(R.id.activity_type);
        activityType.setText(myActivity.isPhysical() ? "Physical Activity" : "Mental Activity");
        TextView activityDesc = v.findViewById(R.id.activity_desc);
        activityDesc.setText(myActivity.getDescription());
        ImageView activityLogo = v.findViewById(R.id.logo_image);
        activityLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), myActivity.getLogo(), null));
        if (myActivity.isPhysical() && !myActivity.getName().equals("Workout")) {
            Button browseBtn = v.findViewById(R.id.btn_detail);
            browseBtn.setText(R.string.btn_routes);
        }
    }

    @Override
    public void onClick(View v) {
        Button routeBtn = v.findViewById(R.id.btn_detail);
        Bundle activityBundle = new Bundle();
        activityBundle.putSerializable("myActivity", myActivity);
        if (routeBtn.getText().toString().equals("Finish")) {
             ActivityFinishFragment finishFragment = new ActivityFinishFragment();
            finishFragment.setArguments(activityBundle);
            ((MainActivity) getActivity()).loadFragment(finishFragment);
        } else {
            ActivityBrowseFragment browseFragment = new ActivityBrowseFragment();
            browseFragment.setArguments(activityBundle);
            ((MainActivity) getActivity()).loadFragment(browseFragment);
        }
    }
}
