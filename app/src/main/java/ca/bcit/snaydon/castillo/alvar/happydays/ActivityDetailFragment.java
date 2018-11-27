package ca.bcit.snaydon.castillo.alvar.happydays;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityDetailFragment extends Fragment {

    private Activity myActivity;

    public ActivityDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            myActivity = (Activity) getArguments().getSerializable("myActivity");
        }

        Log.e("PASSED_ACTIVITY", myActivity.getName());

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_detail, container, false);
    }

}
