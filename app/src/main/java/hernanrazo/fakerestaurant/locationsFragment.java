package hernanrazo.fakerestaurant;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class locationsFragment extends Fragment implements OnMapReadyCallback,
                                                           GoogleApiClient.ConnectionCallbacks,
                                                           GoogleApiClient.OnConnectionFailedListener,
                                                           LocationListener {

    private static final int LOCATION_REQUEST = 1;
    private static final float DEFAULT_ZOOM = 12.0f;
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mGoogleMap;
    private FusedLocationProviderClient mFusedLocClient;
    private boolean mLocationPermissionGranted = false;
    MapView mapView;
    View mView;
    Location mLastKnownLocation;
    Marker marker;

    public locationsFragment() {}

    //function for asking for permission
    public void locationPermissionCheck() {

        if(ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            mLocationPermissionGranted = true;

            //show explanation for allowing permission
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                //request permission without explanation
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_REQUEST);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        mLocationPermissionGranted = false;

        switch (requestCode) {
            case LOCATION_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    mLocationPermissionGranted = true;

                    // permission was granted, set user's location
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {
                    // permission denied
                    Toast.makeText(getActivity(), "permission denied", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void updateLocationUI() {

        if (mGoogleMap == null) {
            return;
        }

        try {
            if (mLocationPermissionGranted) {

                mGoogleMap.setMyLocationEnabled(true);
                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);

            } else {
                mGoogleMap.setMyLocationEnabled(false);
                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                locationPermissionCheck();
            }

        } catch(SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getDeviceLocation() {

        try {
            if (mLocationPermissionGranted) {

                Task locationResult = mFusedLocClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {

                        if (task.isSuccessful()) {

                            mLastKnownLocation = task.getResult();
                            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastKnownLocation.getLatitude(),
                                    mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));

                        } else {
                            Log.e("Exception %s", "Current location is null");
                            Log.e("Exception %s", "What the fuck");
                        }
                    }
                });
            }

        } catch(SecurityException e) {

            Log.e("Exception: %s", e.getMessage());
        }
    }

    public void onLocationChanged(Location location) {

        LatLng currentLoc = new LatLng(location.getLatitude(), location.getLongitude());
        mGoogleMap.addMarker(new MarkerOptions().position(currentLoc).title("Current Position"));
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLoc, 12));
    }


    @Override
    public void onConnected(Bundle bundle) {}

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_locations, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = mView.findViewById(R.id.map);

        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        updateLocationUI();
        getDeviceLocation();

        //set markers for fake locations
        LatLng loc1 = new LatLng(42.0021862, -87.6622885);
        LatLng loc2 = new LatLng(41.9062128, -87.696963);
        LatLng loc3 = new LatLng(41.8578586, -87.6150899);
        LatLng loc4 = new LatLng(41.847741, -87.6490867);

        googleMap.addMarker(new MarkerOptions().position(loc1).title("location 1"));
        googleMap.addMarker(new MarkerOptions().position(loc2).title("location 2"));
        googleMap.addMarker(new MarkerOptions().position(loc3).title("location 3"));
        googleMap.addMarker(new MarkerOptions().position(loc4).title("location 4"));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locationPermissionCheck();

        mFusedLocClient = LocationServices.getFusedLocationProviderClient(getActivity());
    }
}