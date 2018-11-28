package ca.bcit.snaydon.castillo.alvar.happydays;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Route {

    private Context myContext;
    private String activityName;



    private ArrayList<String> routeNames;

//    public Route()

    public Route(Context myContext, String activityName) {
        this.myContext = myContext;
        this.activityName = activityName;
    }

    public ArrayList<String> getRouteNames() {
        routeNames = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(getJSONFileName()));
            JSONArray features = obj.getJSONArray("features");

            for (int i = 0; i < features.length(); i++) {
                JSONObject featureObj = features.getJSONObject(i);
                JSONObject properties = featureObj.getJSONObject("properties");
                System.out.println(properties.toString());
                String name;
                if (!activityName.equals("Biking")) {
                    name = properties.getString("Name");
                } else {
                    name = properties.getString("FullName");
                }

                routeNames.add(name);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return routeNames;
    }

    private String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = myContext.getAssets().open(fileName);
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

    private String getJSONFileName() {
        String fileName;
        if (activityName.equals("Biking")) {
            fileName = "PARKS_MAJOR_NAMED_GREENWAYS.json";
        } else {
            fileName = "PARKS_TRAILS.json";
        }
        return fileName;
    }

}
