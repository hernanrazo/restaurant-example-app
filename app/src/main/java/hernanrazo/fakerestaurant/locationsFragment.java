package hernanrazo.fakerestaurant;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class locationsFragment extends Fragment implements OnMapReadyCallback {

    private static final int GEOLOCATION_PERMISSION_REQUEST = 1;
    private boolean locationPermissionGranted = false;
    GoogleMap mGoogleMap;
    MapView mapView;
    View mView;

    public locationsFragment() {}

    public boolean checkPermission() {

        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Prompt the user once explanation has been shown.
                requestPermissions(new String[] {
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, GEOLOCATION_PERMISSION_REQUEST );
            } else {
                // No explanation needed
                requestPermissions(new String[] {
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, GEOLOCATION_PERMISSION_REQUEST );
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {

        locationPermissionGranted = false;

        switch (requestCode) {
            case GEOLOCATION_PERMISSION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    locationPermissionGranted = true;

                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {
                    Toast.makeText(this, "Location permission needed to continue", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_locations, container, false);
        return mView;
        checkPermission();

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

        LatLng loc1 = new LatLng(42.0021862, -87.6622885);
        LatLng loc2 = new LatLng(41.9062128, -87.696963);
        LatLng loc3 = new LatLng(41.8578586, -87.6150899);
        LatLng loc4 = new LatLng(41.847741, -87.6490867);

        googleMap.addMarker(new MarkerOptions().position(loc1).title("location 1"));
        googleMap.addMarker(new MarkerOptions().position(loc2).title("location 2"));
        googleMap.addMarker(new MarkerOptions().position(loc3).title("location 3"));
        googleMap.addMarker(new MarkerOptions().position(loc4).title("location 4"));

        //TODO: move camera to user's current location

    }
}