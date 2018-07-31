package hernanrazo.fakerestaurant;

import android.Manifest;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class locationsFragment extends Fragment implements OnMapReadyCallback,
                                                           GoogleApiClient.ConnectionCallbacks,
                                                           GoogleApiClient.OnConnectionFailedListener,
                                                           LocationListener {

    private static final int LOCATION_REQUEST = 1;
    private static final int CHECK_SETTINGS_REQUEST = 0x1;
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mGoogleMap;
    private FusedLocationProviderClient mFusedLocClient;
    MapView mapView;
    View mView;
    Location mLastLocation;
    Marker marker;

    public locationsFragment() {}

    //function for asking for permission
    public boolean locationPermissionCheck() {

        if(ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            //show explanation for allowing permission
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                //request permission without explanation
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_REQUEST);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

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
                return;
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

    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (marker != null) {
            marker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        marker = mGoogleMap.addMarker(markerOptions);

        //arrange the camera
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,11));
    }

    protected void createLocationRequest() {
        LocationRequest mLocRequest = new LocationRequest();
        mLocRequest.setInterval(10000);
        mLocRequest.setFastestInterval(5000);
        mLocRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocRequest);

        SettingsClient client = LocationServices.getSettingsClient(getActivity());
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

                //put location request here
            }
        });

        task.addOnFailureListener(getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                if(e instanceof ResolvableApiException) {

                    try {

                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(getActivity(),
                                CHECK_SETTINGS_REQUEST);

                    } catch (IntentSender.SendIntentException sendEx) {
                        //ignore
                    }
                }
            }
        });
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

        //recheck for permission
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                //Permission already granted, continue normally
                buildGoogleApiClient();
                //mGoogleMap.setMyLocationEnabled(true);

                mFusedLocClient.getLastLocation().addOnSuccessListener(getActivity(),
                        new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {

                            //get location logic here
                        }
                    }
                });

            } else {

                //ask for permission again
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_REQUEST);
            }
        }

        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

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