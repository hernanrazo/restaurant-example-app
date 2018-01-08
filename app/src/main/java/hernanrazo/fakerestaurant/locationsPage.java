package hernanrazo.fakerestaurant;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class locationsPage extends AppCompatActivity {

    private static final int GEOLOCATION_PERMISSION_REQUEST = 1;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private boolean firstResume = false;

    private boolean geolocationPermission() {

        int LocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if(LocationPermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                        GEOLOCATION_PERMISSION_REQUEST);
                return false;
            }
        } else {
            if(LocationPermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if(!listPermissionsNeeded.isEmpty()) {
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

        switch(requestCode) {
            case GEOLOCATION_PERMISSION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //fill shit here
                }else {
                    Toast.makeText(this, "Location permission needed to continue", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void addDrawerItems() {
        String[] osArray = { "Home", "Menu", "Order now", "Locations", "Reviews", "Social media", "Contact Us" };
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0) {
                    Intent homePageIntent = new Intent(locationsPage.this, homePage.class);
                    startActivity(homePageIntent);
                }
                else if (position == 1) {
                    Intent menuPageIntent = new Intent(locationsPage.this, menuPage.class);
                    startActivity(menuPageIntent);
                }
                else if (position == 2) {
                    Intent orderPageIntent = new Intent(locationsPage.this, orderPage.class);
                    startActivity(orderPageIntent);
                }
                else if (position == 3) {
                    Intent locationsPageIntent = new Intent(locationsPage.this, locationsPage.class);
                    startActivity(locationsPageIntent);
                }
                else if (position == 4) {
                    Intent reviewsPageIntent = new Intent(locationsPage.this, reviewsPage.class);
                    startActivity(reviewsPageIntent);
                }
                else if (position == 5) {
                    Intent socialMediaPageIntent = new Intent(locationsPage.this, socialMediaPage.class);
                    startActivity(socialMediaPageIntent);
                }
                else if (position == 6) {
                    Intent contactUsIntent = new Intent(locationsPage.this, contactUsPage.class);
                    startActivity(contactUsIntent);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_page);

        mDrawerList = findViewById(R.id.navList);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        geolocationPermission();
        addDrawerItems();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(firstResume) {
            mDrawerLayout.openDrawer(Gravity.START);
        }
    }
}