package hernanrazo.fakerestaurant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

public class homePage extends AppCompatActivity {

    //TODO: Change colors of nav drawer

    private DrawerLayout mDrawerLayout;
    private boolean firstResume = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        NavigationView navigationView = findViewById(R.id.navView);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        Fragment fragment;
                        int id = menuItem.getItemId();

                        if (id == R.id.homePage) {
                            Intent homePageIntent = new Intent(homePage.this, homePage.class);
                            startActivity(homePageIntent);
                            menuItem.setChecked(true);
                            mDrawerLayout.closeDrawers();

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