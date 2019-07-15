package id.ac.umn.made_basiliusbias_submission2.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import id.ac.umn.made_basiliusbias_submission2.LanguageActivity;
import id.ac.umn.made_basiliusbias_submission2.R;
import id.ac.umn.made_basiliusbias_submission2.fragments.SettingsFragment;

public class SettingsActivity extends LanguageActivity {

    // TODO: Mindahin Variabel Global Jadi Local (Kalo Sempet, Udah Di Warn, Wkwk~)

    // For Settings Fragment
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Change Activity Page UI Toolbar
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Change Toolbar Title
        if (getSupportActionBar() != null) getSupportActionBar().setTitle(R.string.title_activity_settings);

        // Inflate Fragment Settings
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_settings, new SettingsFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        reLaunchMainActivity();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        reLaunchMainActivity();
    }

    private void reLaunchMainActivity() {

        // Show Toast
        Toast.makeText(this, R.string.settings_saved, Toast.LENGTH_SHORT).show();

        // Back To Parent Activity
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}