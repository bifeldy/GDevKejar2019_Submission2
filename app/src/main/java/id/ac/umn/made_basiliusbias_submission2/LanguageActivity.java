package id.ac.umn.made_basiliusbias_submission2;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.os.ConfigurationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;

public class LanguageActivity extends AppCompatActivity {

    // TODO: Mindahin Variabel Global Jadi Local (Kalo Sempet, Udah Di Warn, Wkwk~)

    // Shared Preferences
    SharedPreferences sharedPreferences;
    String languageCode;

    @Override
    protected void attachBaseContext(Context newBase) {

        // Get Language Shared Preference
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        languageCode = sharedPreferences.getString(
                newBase.getResources().getString(R.string.pref_language_list_key),
                ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).toLanguageTags().split("-")[0]
        );

        // Set Language
        Context context = Utility.changeLanguage(newBase, languageCode);
        super.attachBaseContext(context);
    }
}
