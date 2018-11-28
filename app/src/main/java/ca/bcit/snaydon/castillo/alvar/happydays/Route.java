package ca.bcit.snaydon.castillo.alvar.happydays;

import android.os.AsyncTask;

import com.mapbox.mapboxsdk.geometry.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Route implements Serializable {

    private String name;
    private List<String> jsonData;
    ArrayList<LatLng> routePoints;

    public Route(String name, List<String> jsonData) {
        this.name = name;
        this.jsonData = jsonData;
    }

    public Route(String name) {
        this.name = name;
    }

    public String getName() { return name; }

//    public ArrayList<LatLng> getCoordinates() {
//        switch (this.name) {
//            case "London Dublin Greenway":
//                break;
//            case "Central Valley Greenway":
//                break;
//            case "Brunette Fraser Regional Greenway":
//                break;
//            case "":
//                break;
//            case "":
//                break;
//            case "":
//                break;
//            case "":
//                break;
//            case "":
//                break;
//            case "":
//                break;
//            case "":
//                break;
//            case " ":
//
//
//        }
//    }

//    public ArrayList<LatLng> convertStringCoords(String coords) {
//
//    }

        private class JSONParse extends AsyncTask<Void, Void, ArrayList<LatLng>> {
        @Override
        protected ArrayList<LatLng> doInBackground(Void... voids) {
            for (int i = 0; i < jsonData.size(); i++) {
                System.out.println("JsonData: " + jsonData.get(i) + "\n");
            }
            ArrayList<LatLng> points = new ArrayList<>();
            for (int i = 0; i < jsonData.size(); i++) {
                try {
                    JSONObject geometry = new JSONObject(jsonData.get(i));
                    JSONArray coordinates = geometry.getJSONArray("coordinates");
                    for (int j = 0; j < coordinates.length(); j++) {
                        JSONArray coord = coordinates.getJSONArray(j);
                        LatLng latLng = new LatLng(coord.getDouble(1), coord.getDouble(0));
                        points.add(latLng);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return points;
        }

        @Override
        protected void onPostExecute(ArrayList<LatLng> points) {
            super.onPostExecute(points);
            routePoints = points;
        }
    }

}
