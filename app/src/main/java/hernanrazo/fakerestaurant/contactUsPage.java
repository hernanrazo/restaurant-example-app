package hernanrazo.fakerestaurant;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class contactUsPage extends AppCompatActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private boolean firstResume = false;

    private void addDrawerItems() {
        String[] osArray = { "Home", "Menu", "Order now", "Locations", "Reviews", "Social media", "Contact Us" };
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0) {
                    Intent homePageIntent = new Intent(contactUsPage.this, homePage.class);
                    startActivity(homePageIntent);
                }
                else if (position == 1) {
                    Intent menuPageIntent = new Intent(contactUsPage.this, menuPage.class);
                    startActivity(menuPageIntent);
                }
                else if (position == 2) {
                    Intent orderPageIntent = new Intent(contactUsPage.this, orderPage.class);
                    startActivity(orderPageIntent);
                }
                else if (position == 3) {
                    Intent locationsPageIntent = new Intent(contactUsPage.this, locationsPage.class);
                    startActivity(locationsPageIntent);
                }
                else if (position == 4) {
                    Intent reviewsPageIntent = new Intent(contactUsPage.this, reviewsPage.class);
                    startActivity(reviewsPageIntent);
                }
                else if (position == 5) {
                    Intent socialMediaPageIntent = new Intent(contactUsPage.this, socialMediaPage.class);
                    startActivity(socialMediaPageIntent);
                }
                else if (position == 6) {
                    Intent contactUsIntent = new Intent(contactUsPage.this, contactUsPage.class);
                    startActivity(contactUsIntent);
                }
            }
        });
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us_page);

        mDrawerList = findViewById(R.id.navList);
        mDrawerLayout = findViewById(R.id.drawer_layout);

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
