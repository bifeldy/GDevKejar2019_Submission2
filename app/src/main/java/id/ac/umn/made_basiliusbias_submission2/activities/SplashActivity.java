package id.ac.umn.made_basiliusbias_submission2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import id.ac.umn.made_basiliusbias_submission2.LanguageActivity;
import id.ac.umn.made_basiliusbias_submission2.R;

public class SplashActivity extends LanguageActivity {

    // TODO: Mindahin Variabel Global Jadi Local (Kalo Sempet, Udah Di Warn, Wkwk~)

    // Custom Animation
    private Animation animation;

    // UI Elements
    private ImageView splashPicture;
    private TextView splashText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Load Animasi Fade Out HandMade Hehe
        animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Find UI
        splashPicture = findViewById(R.id.splash_picture);
        splashText = findViewById(R.id.splash_text);

        // Run Animate
        splashPicture.startAnimation(animation);
        splashText.startAnimation(animation);

        // Multi-Thread For Delay Animating Splash Screen
        new Thread(() -> {
            try {

                // Delay 2.5 Second Showing Logo
                Thread.sleep(2500);

                // Go To Main Apps
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            catch(InterruptedException e) {

                // Print Error
                e.printStackTrace();
            }
        }).start();
    }
}