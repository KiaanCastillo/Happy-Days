package ca.bcit.snaydon.castillo.alvar.happydays;


import android.content.Intent;
import android.os.AsyncTask;
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

import com.mapbox.mapboxsdk.geometry.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityBrowseFragment extends ListFragment {

    private Activity myActivity;
    private ArrayList<Route> routeList;
//    private ArrayList<Route> selectedRoutes;


    public ActivityBrowseFragment() {
        // Required empty public constructor
    }

//    private ArrayList<Route> initBikingRoutes() {
//        ArrayList<Route> biking = new ArrayList<>();
//        biking.add(new Route("London Dublin Greenway"))
//    }
//
//    private ArrayList<Route> initNonBikingRoutes() {
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_activity_browse, container, false);

        if (getArguments() != null) {
            myActivity = (Activity) getArguments().getSerializable("myActivity");
        }

//        ArrayList<Route> selectedRoute;
//        if (myActivity.getName().equals("Biking")) {
//            selectedRoute = initBikingRoutes();
//        } else {
//            selectedRoute = initNonBikingRoutes();
//        }

        String stringJSON = loadJSONFromAsset(getJSONFileName());
//        HashMap<String, List<String>> routeMap = parseJSONString(stringJSON);
        routeList = parseJSONString(stringJSON);
        BrowseItemAdapter itemAdapter = new BrowseItemAdapter(getActivity(), routeList);
        setListAdapter(itemAdapter);
        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Bundle routeBundle = new Bundle();
        routeBundle.putSerializable("myRoute", routeList.get(position));
        routeBundle.putSerializable("myActivity", myActivity);
        ActivityMapFragment mapFragment = new ActivityMapFragment();
        mapFragment.setArguments(routeBundle);
        ((MainActivity) getActivity()).loadFragment(mapFragment);
    }

    public ArrayList<Route> initRouteList(HashMap<String, List<String>> routeMap) {
        ArrayList<Route> tempList = new ArrayList<>();
        for (String key : routeMap.keySet()) {
//            Route newRoute = new Route(key, routeMap.get(key));
            Route newRoute = new Route(key);
            tempList.add(newRoute);
        }
        return tempList;
    }

    public ArrayList<Route> parseJSONString(String json) {
        HashMap<String, List<String>> tempMap = new HashMap<>();
        ArrayList<Route> routeList = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray features = obj.getJSONArray("features");

            for (int i = 0; i < features.length(); i++) {
                JSONObject featureObj = features.getJSONObject(i);
                JSONObject properties = featureObj.getJSONObject("properties");
                JSONObject geometry = featureObj.getJSONObject("geometry");

                String routeName;
                if (properties.has("FullName"))
                    routeName = properties.getString("FullName");
                else
                    routeName = properties.getString("Name");

                if (routeName.equals(""))
                    routeName = "Other";

                Route myRoute = new Route(routeName);
                if (!routeList.contains(myRoute)) {
                    routeList.add(myRoute);
                }

//                if (tempMap.containsKey(routeName)) {
//                    List<String> featureStringList = tempMap.get(routeName);
//                    featureStringList.add(geometry.toString());
//                    tempMap.put(routeName, featureStringList);
//                }
//                else {
//                    List<String> featureStringList = new ArrayList<>();
//                    featureStringList.add(geometry.toString());
//                    tempMap.put(routeName, featureStringList);
//                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return routeList;
    }

    public String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public String getJSONFileName() {
        String fileName;
        if (myActivity.getName().equals("Biking")) {
            fileName = "PARKS_MAJOR_NAMED_GREENWAYS.json";
        } else {
            fileName = "PARKS_MAJOR_NAMED_GREENWAYS.json";
        }
        return fileName;
    }

}
