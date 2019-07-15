package id.ac.umn.made_basiliusbias_submission2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import id.ac.umn.made_basiliusbias_submission2.LanguageActivity;
import id.ac.umn.made_basiliusbias_submission2.fragments.MoviesFragment;
import id.ac.umn.made_basiliusbias_submission2.R;
import id.ac.umn.made_basiliusbias_submission2.fragments.TVShowsFragment;

public class MainActivity extends LanguageActivity implements NavigationView.OnNavigationItemSelectedListener {

    // TODO: Mindahin Variabel Global Jadi Local (Kalo Sempet, Udah Di Warn, Wkwk~)

    // Left NavMenu
    private DrawerLayout drawer;
    private NavigationView navigationView;

    // ActionBar
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;

    // Custom Setting For 2x Back To Close App
    private boolean doubleBackToExitPressedOnce = false;

    // UI Object
    private ImageView userImg;
    private TextView userName, userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Show Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Navigation Drawer
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        userImg = navigationView.getHeaderView(0).findViewById(R.id.nav_user_img);
        userName = navigationView.getHeaderView(0).findViewById(R.id.nav_user_name);
        userEmail = navigationView.getHeaderView(0).findViewById(R.id.nav_user_email);

        // Setting Up Navigation Menu
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // Force To Open List Of Product When App Firstly Opened
        navigationView.setCheckedItem(R.id.nav_movie);
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_movie));

        // Load User Login Info Into Nav Menu Profile
        Glide.with(this)
            .load(getResources().getString(R.string.animated_gif))
            .transition(DrawableTransitionOptions.withCrossFade(1234)) // Transition Effect Load Image
            .apply(
                RequestOptions.circleCropTransform().override(100, 100)
            ).into(userImg);
    }

    @Override
    public void onBackPressed() {

        // When NavMenu Opened
        if (drawer.isDrawerOpen(GravityCompat.START)) {

            // Close It First
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (doubleBackToExitPressedOnce) {

            // 2x Back Button Pressed To Close App
            super.onBackPressed();
        }
        else {

            // In Other Menu Except Menu 1
            if (!navigationView.getMenu().getItem(0).isChecked()) {

                // Back To Menu 1 First
                navigationView.setCheckedItem(R.id.nav_movie);
                onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_movie));
            }
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.main_back_pressed, Toast.LENGTH_SHORT).show();

        // Handler Back Button Checker Can Close App Within 1 Second On 2x Press
        new Handler().postDelayed(() -> {

            // Reset Condition
            doubleBackToExitPressedOnce = false;
        }, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent;

        // Noinspection Simplifiable If Statement
        if (id == R.id.action_main_settings) {

            // Go To About Activity
            intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_movie) {

            // Show Movies Fragment
            if (getSupportActionBar() != null) getSupportActionBar().setTitle(R.string.nav_movie);
            fragmentManager.beginTransaction().replace(R.id.frame_main, new MoviesFragment()).commit();
        }
        else if(id == R.id.nav_tv_shows) {

            // Show TV Shows Fragment
            if (getSupportActionBar() != null) getSupportActionBar().setTitle(R.string.nav_tv_shows);
            fragmentManager.beginTransaction().replace(R.id.frame_main, new TVShowsFragment()).commit();
        }
        else if(id == R.id.nav_about) {

            // Go To About Activity
            intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
