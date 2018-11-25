package ca.bcit.snaydon.castillo.alvar.happydays;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityBrowseFragment extends ListFragment {

    private Activity myActivity;

    public ActivityBrowseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_activity_browse, container, false);

        if (getArguments() != null) {
            myActivity = (Activity) getArguments().getSerializable("myActivity");
        }

        MainActivity parentActivity = (MainActivity) getActivity();
        ArrayList<String> browseList = getBrowseList(myActivity.getName());
        BrowseItemAdapter browseItemAdapter = new BrowseItemAdapter(parentActivity, browseList);
        setListAdapter(browseItemAdapter);
        return v;
    }

    public ArrayList<String> getBrowseList(String activityName) {
        ArrayList<String> browseList;
        if (activityName.equals("Biking")) {
            browseList = new ArrayList<>(Arrays.asList(Activity.BIKING_LIST));
        } else {
            browseList = new ArrayList<>(Arrays.asList(Activity.RUNNING_WALKING_LIST));
        }
        return browseList;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Bundle activityBundle = new Bundle();
        activityBundle.putSerializable("myActivity", myActivity);
        String curItem = (String) l.getItemAtPosition(position);
        if (curItem.equalsIgnoreCase("No Route")) {
            ActivityFinishFragment finishFragment = new ActivityFinishFragment();
            finishFragment.setArguments(activityBundle);
            ((MainActivity) getActivity()).loadFragment(finishFragment);
        } else {

        }
    }
}
