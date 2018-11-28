package ca.bcit.snaydon.castillo.alvar.happydays;

import com.mapbox.mapboxsdk.geometry.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Route implements Serializable {

    private String name;
    private List<String> jsonData;

    public Route(String name, List<String> jsonData) {
        this.name = name;
        this.jsonData = jsonData;
    }

    public String getName() { return name; }

    public ArrayList<LatLng> getCoordinates() {
        for (int i = 0; i < jsonData.size(); i++) {
            System.out.println(jsonData.get(i) + "\n");
        }
        ArrayList<LatLng> points = new ArrayList<>();
//        for (int i = 0; i < jsonData.size(); i++) {
//            try {
//                JSONObject geometry = new JSONObject(jsonData.get(i));
//                JSONArray coordinates = geometry.getJSONArray("coordinates");
//                for (int j = 0; j < coordinates.length(); j++) {
//                    JSONArray coord = coordinates.getJSONArray(j);
//                    LatLng latLng = new LatLng(coord.getDouble(1), coord.getDouble(0));
//                    points.add(latLng);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
        return points;
    }

}
