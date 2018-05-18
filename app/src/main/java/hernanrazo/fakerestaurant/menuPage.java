package hernanrazo.fakerestaurant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

public class menuPage extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView navView;
    private boolean firstResume = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.navView);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {

                    case R.id.homePage:
                        Intent homePageIntent = new Intent(menuPage.this, homePage.class);
                        startActivity(homePageIntent);
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();

                    case R.id.menuPage:
                        Intent menuPageIntent = new Intent(menuPage.this, menuPage.class);
                        startActivity(menuPageIntent);
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();

                    case R.id.orderPage:
                        Intent orderPageIntent = new Intent(menuPage.this, orderPage.class);
                        startActivity(orderPageIntent);
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();

                    case R.id.locationsPage:
                        Intent locationsPageIntent = new Intent(menuPage.this, locationsPage.class);
                        startActivity(locationsPageIntent);
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();

                    case R.id.reviewsPage:
                        Intent reviewsPageIntent = new Intent(menuPage.this, reviewsPage.class);
                        startActivity(reviewsPageIntent);
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();

                    case R.id.socialMediaPage:
                        Intent socialMediaPageIntent = new Intent(menuPage.this, socialMediaPage.class);
                        startActivity(socialMediaPageIntent);
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();

                    case R.id.contactUsPage:
                        Intent contactUsPageIntent = new Intent(menuPage.this, contactUsPage.class);
                        startActivity(contactUsPageIntent);
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();

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