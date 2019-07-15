package id.ac.umn.made_basiliusbias_submission2.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import id.ac.umn.made_basiliusbias_submission2.LanguageActivity;
import id.ac.umn.made_basiliusbias_submission2.R;
import id.ac.umn.made_basiliusbias_submission2.pojos.Film;

import java.text.DecimalFormat;

public class DetailsActivity extends LanguageActivity {

    // TODO: Mindahin Variabel Global Jadi Local (Kalo Sempet, Udah Di Warn, Wkwk~)

    // For Parcelable
    public static final String EXTRA_DETAIL = "extra_detail";

    // UI Object
    private TextView txtTitle, txtDescription, txtScore, txtType;
    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Change Activity Page UI Toolbar
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find UI
        imgPhoto = findViewById(R.id.detail_image);
        txtTitle = findViewById(R.id.detail_title);
        txtDescription = findViewById(R.id.detail_description);
        txtScore = findViewById(R.id.detail_score);
        txtType = findViewById(R.id.detail_type);

        // Get Data From Parcelable
        Film film = getIntent().getParcelableExtra(EXTRA_DETAIL);

        // Set Data
        txtTitle.setText(film.getTitle());
        txtDescription.setText(film.getDescription());
        imgPhoto.setImageDrawable(getDrawable(film.getPhoto()));

        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        txtScore.setText(decimalFormat.format(film.getScore()));

        txtType.setText(film.getType());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Back To Parent Activity
        finish();
        return super.onOptionsItemSelected(item);
    }
}