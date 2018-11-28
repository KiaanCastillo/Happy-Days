package ca.bcit.snaydon.castillo.alvar.happydays;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityMapFragment extends Fragment {

    private Route myRoute;

    public ActivityMapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_activity_map, container, false);

        if (getArguments() != null) {
            myRoute = (Route) getArguments().getSerializable("myRoute");
        }

        TextView test = v.findViewById(R.id.route_name);
        test.setText(myRoute.getName());

        return v;
    }

}
