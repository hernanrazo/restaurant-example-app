package hernanrazo.fakerestaurant;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class locationsPage extends FragmentActivity implements OnMapReadyCallback {

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

    private boolean geolocationPermission() {

        int locationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (locationPermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
                locationPermissionGranted = true;
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                        GEOLOCATION_PERMISSION_REQUEST);
                locationPermissionGranted = false;
            }
        } else {
            if (locationPermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                        GEOLOCATION_PERMISSION_REQUEST);
                return false;
            }
        }
        return true;
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
                } else {
                    Toast.makeText(this, "Location permission needed to continue", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        updateLocationUI();
    }

    private void addDrawerItems() {
        String[] osArray = {"Home", "Menu", "Order now", "Locations", "Reviews", "Social media", "Contact Us"};
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    Intent homePageIntent = new Intent(locationsPage.this, homePage.class);
                    startActivity(homePageIntent);
                } else if (position == 1) {
                    Intent menuPageIntent = new Intent(locationsPage.this, menuPage.class);
                    startActivity(menuPageIntent);
                } else if (position == 2) {
                    Intent orderPageIntent = new Intent(locationsPage.this, orderPage.class);
                    startActivity(orderPageIntent);
                } else if (position == 3) {
                    Intent locationsPageIntent = new Intent(locationsPage.this, locationsPage.class);
                    startActivity(locationsPageIntent);
                } else if (position == 4) {
                    Intent reviewsPageIntent = new Intent(locationsPage.this, reviewsPage.class);
                    startActivity(reviewsPageIntent);
                } else if (position == 5) {
                    Intent socialMediaPageIntent = new Intent(locationsPage.this, socialMediaPage.class);
                    startActivity(socialMediaPageIntent);
                } else if (position == 6) {
                    Intent contactUsIntent = new Intent(locationsPage.this, contactUsPage.class);
                    startActivity(contactUsIntent);
                }
            }
        });
    }

    private void getDeviceLocation() {

        try {
            if (locationPermissionGranted) {

                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {

                        if (task.isSuccessful()) {

                            mLastKnownLocation = task.getResult();
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()),
                                    15));
                        } else {
                            Log.d(TAG, "Current location is null.");
                            Log.e(TAG, "Exception: %s", task.getException());
                        }
                    }
                });

            } else {
                shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION);
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, GEOLOCATION_PERMISSION_REQUEST);
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void updateLocationUI() {

        try {

            if (locationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);

            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                geolocationPermission();

            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        geolocationPermission();
        updateLocationUI();
        getDeviceLocation();
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

        addDrawerItems();
    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean firstResume = false;
        if(firstResume) {
            mDrawerLayout.openDrawer(Gravity.START);
        }
    }
}