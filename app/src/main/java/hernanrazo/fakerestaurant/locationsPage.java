package hernanrazo.fakerestaurant;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

//TODO: make it so the screen automatically zooms to current locations
public class locationsPage extends AppCompatActivity implements GoogleMap.OnMyLocationButtonClickListener,
                                                                GoogleMap.OnMyLocationClickListener,
                                                                OnMapReadyCallback,
                                                                ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int GEOLOCATION_PERMISSION_REQUEST = 1;
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    private static final String TAG = locationsPage.class.getSimpleName();
    private CameraPosition mCameraPosition;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Location mLastKnownLocation;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private boolean locationPermissionGranted = false;




    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
//        geolocationPermission();

        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);

        LatLng loc1 = new LatLng(42.0021862, -87.6622885);
        LatLng loc2 = new LatLng(41.9062128, -87.696963);
        LatLng loc3 = new LatLng(41.8578586, -87.6150899);
        LatLng loc4 = new LatLng(41.847741, -87.6490867);

        googleMap.addMarker(new MarkerOptions().position(loc1).title("location 1"));
        googleMap.addMarker(new MarkerOptions().position(loc2).title("location 2"));
        googleMap.addMarker(new MarkerOptions().position(loc3).title("location 3"));
        googleMap.addMarker(new MarkerOptions().position(loc4).title("location 4"));
    }

    //TODO: remove toasts after finished
    @Override
    public void onMyLocationClick(@NonNull Location location) {

        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_page);

        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        mDrawerList = findViewById(R.id.navList);
        mDrawerLayout = findViewById(R.id.drawer_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean firstResume = false;
        if (firstResume) {
            mDrawerLayout.openDrawer(Gravity.START);
        }
    }
}