package hernanrazo.fakerestaurant;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import java.util.ArrayList;
import java.util.List;

public class mainActivity extends AppCompatActivity {

    //TODO: Change colors of nav drawer
    private DrawerLayout mDrawerLayout;
    private orderFragment orderFragment;

    private boolean firstResume = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set homeFragment into view upon launching app
        FragmentTransaction firstTime = getSupportFragmentManager().beginTransaction();
        firstTime.replace(R.id.mainContent, new homeFragment());
        firstTime.commit();

        //Set logic for navigation drawer and fragment transitions
        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        Fragment fragment;
                        int id = menuItem.getItemId();

                        if (id == R.id.homeFragment) {

                            fragment = new homeFragment();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            ft.replace(R.id.mainContent, fragment);
                            ft.commit();

                        } else if (id == R.id.menuFragment) {

                            fragment = new menuFragment();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            ft.replace(R.id.mainContent, fragment);
                            ft.commit();

                        } else if (id == R.id.orderFragment) {

                            fragment = new orderFragment();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            ft.replace(R.id.mainContent, fragment);
                            ft.commit();

                        } else if (id == R.id.locationsFragment) {

                            fragment = new locationsFragment();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            ft.replace(R.id.mainContent, fragment);
                            ft.commit();

                        } else if (id == R.id.reviewsFragment) {

                            fragment = new reviewsFragment();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            ft.replace(R.id.mainContent, fragment);
                            ft.commit();

                        } else if (id == R.id.socialMediaFragment) {

                            fragment = new socialMediaFragment();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            ft.replace(R.id.mainContent, fragment);
                            ft.commit();
                        }

                        mDrawerLayout = findViewById(R.id.drawer_layout);
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    }
                });
    }
    @Override
    protected void onResume() {
        super.onResume();

        if(firstResume) {
            mDrawerLayout.openDrawer(Gravity.START);
        }
    }
}