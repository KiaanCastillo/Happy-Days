package ca.bcit.snaydon.castillo.alvar.happydays;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineListener;
import com.mapbox.android.core.location.LocationEnginePriority;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerPlugin;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.CameraMode;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.RenderMode;;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityMapFragment extends Fragment implements OnMapReadyCallback, LocationEngineListener, PermissionsListener, View.OnClickListener {

    private Activity myActivity;
    private Route myRoute;
    private MapView mapView;
    private MapboxMap map;
    private PermissionsManager permissionsManager;
    private LocationEngine locationEngine;
    private LocationLayerPlugin locationLayerPlugin;
    private ArrayList<LatLng> points = new ArrayList<>();
    private Location originLocation;

    public ActivityMapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize MapBox
        Mapbox.getInstance(requireActivity(), getString(R.string.access_token));

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_activity_map, container, false);

        Button finishBtn = v.findViewById(R.id.btn_finish);
        finishBtn.setOnClickListener(this);

        if (getArguments() != null) {
            myRoute = (Route) getArguments().getSerializable("myRoute");
            myActivity = (Activity) getArguments().getSerializable("myActivity");
        }

        points.add(new LatLng(49.18785839047904, -122.95712421852141));
        points.add(new LatLng(49.28715887127316, -122.9771206742634));

        return v;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        mapView = v.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        map = mapboxMap;
        enableLocation();

        points = initPoints();
        // Load and Draw GeoJSON
        if (points.size() > 0) {

            // Draw polyline on map
            map.addPolyline(new PolylineOptions()
                    .addAll(points)
                    .color(Color.parseColor("#3bb2d0"))
                    .width(4));
        }
    }

    private void enableLocation() {
        if (PermissionsManager.areLocationPermissionsGranted(getContext())) {
            initializeLocationEngine();
            initializeLocationLayer();
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(getActivity());
        }
    }

    @SuppressWarnings("MissingPermission")
    private void initializeLocationEngine(){
        locationEngine = new LocationEngineProvider(getContext()).obtainBestLocationEngineAvailable();
        locationEngine.setPriority(LocationEnginePriority.BALANCED_POWER_ACCURACY);
        locationEngine.activate();

        Location lastLocation = locationEngine.getLastLocation();
        if(lastLocation != null){
            originLocation = lastLocation;
            setCameraPosition(lastLocation);
        } else {
            locationEngine.addLocationEngineListener(this);
        }
    }

    @SuppressLint("WrongConstant")
    @SuppressWarnings("MissingPermission")
    private void initializeLocationLayer() {
        locationLayerPlugin = new LocationLayerPlugin(mapView, map, locationEngine);
        locationLayerPlugin.setLocationLayerEnabled(true);
        locationLayerPlugin.setCameraMode(CameraMode.TRACKING);
        locationLayerPlugin.setRenderMode(RenderMode.NORMAL);
    }

    private void setCameraPosition(Location location) {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),
                location.getLongitude()), 13.0));
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onConnected() {
        locationEngine.requestLocationUpdates();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            originLocation = location;
            setCameraPosition(location);
        }
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        // Present toast or dialog.

    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            enableLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Bundle routeBundle = new Bundle();
        routeBundle.putSerializable("myActivity", myActivity);
        ActivityFinishFragment mapFragment = new ActivityFinishFragment();
        mapFragment.setArguments(routeBundle);
        ((MainActivity) getActivity()).loadFragment(mapFragment);
    }

    public ArrayList<LatLng> initPoints() {
        ArrayList<LatLng> newPoints = new ArrayList<>();
        newPoints.add(new LatLng(49.20105168346303, -122.95759787805977));
        newPoints.add(new LatLng(49.20166130033205, -122.95647309982671));
        newPoints.add(new LatLng(49.20271053628306, -122.95452550115124));
        newPoints.add(new LatLng(49.20377475115951, -122.95256182903869));
        newPoints.add(new LatLng(49.20468903752043, -122.95085324129026));
        newPoints.add(new LatLng(49.204796619703586, -122.95065547581157));
        newPoints.add(new LatLng(49.205089682435414, -122.95103285228488));
        newPoints.add(new LatLng(49.2062487721426, -122.94887189823));
        newPoints.add(new LatLng(49.207353046789464, -122.94682550639115));
        newPoints.add(new LatLng(49.20830846686669, -122.94504500868656));
        newPoints.add(new LatLng(49.20919306152897, -122.94341591478401));
        newPoints.add(new LatLng(49.210112167937545, -122.94171040246218));
        newPoints.add(new LatLng(49.21103361665923, -122.94000330276188));
        newPoints.add(new LatLng(49.21152906830311, -122.93908262761788));
        newPoints.add(new LatLng(49.211866627469824, -122.93846204540431));
        newPoints.add(new LatLng(49.21191078434635, -122.93836572896139));
        newPoints.add(new LatLng(49.212696512481465, -122.93691100116735));
        newPoints.add(new LatLng(49.21369106411142, -122.93507788121889));
        newPoints.add(new LatLng(49.21466546611929, -122.93327326166487));
        newPoints.add(new LatLng(49.21392370038678, -122.93233090059634));
        newPoints.add(new LatLng(49.21543919966537, -122.92952009996682));

        return newPoints;
    }

}
