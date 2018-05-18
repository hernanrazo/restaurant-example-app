package hernanrazo.fakerestaurant;

import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

public class homePage extends AppCompatActivity {


    //TODO: Replace all other activities with fragments
    //TODO: Change colors of nav drawer
    //TODO: include contact us stuff into the homepage to reduce number of fragments
    

    private DrawerLayout mDrawerLayout;
    private boolean firstResume = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navView);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        int id = menuItem.getItemId();

                        switch(id) {

                            case R.id.homePage:
                                menuItem.setChecked(true);
                                mDrawerLayout.closeDrawers();
                                //Todo: add fragment transitions here

                            case R.id.menuPage:

                                menuItem.setChecked(true);
                                mDrawerLayout.closeDrawers();
                                //Todo: add fragment transitions here

                            case R.id.orderPage:
                                menuItem.setChecked(true);
                                mDrawerLayout.closeDrawers();
                                //Todo: add fragment transitions here

                            case R.id.locationsPage:

                                menuItem.setChecked(true);
                                mDrawerLayout.closeDrawers();
                                //Todo: add fragment transitions here

                            case R.id.reviewsPage:

                                menuItem.setChecked(true);
                                mDrawerLayout.closeDrawers();
                                //Todo: add fragment transitions here

                            case R.id.socialMediaPage:

                                menuItem.setChecked(true);
                                mDrawerLayout.closeDrawers();
                                //Todo: add fragment transitions here

                            case R.id.contactUsPage:

                                menuItem.setChecked(true);
                                mDrawerLayout.closeDrawers();
                                //Todo: add fragment transitions here

                            default: return true;
                        }
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