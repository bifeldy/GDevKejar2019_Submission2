package id.ac.umn.made_basiliusbias_submission2.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v14.preference.SwitchPreference;
import android.support.v4.os.ConfigurationCompat;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import id.ac.umn.made_basiliusbias_submission2.R;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    // TODO: Mindahin Variabel Global Jadi Local (Kalo Sempet, Udah Di Warn, Wkwk~)

    // Shared Preferences
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    // Language
    String language;
    String[] language_label, language_value;
    ListPreference languageList;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        // Set Preference To Show
        setPreferencesFromResource(R.xml.preferences, rootKey);

        // Get Shared Preference
        sharedPreferences = getPreferenceScreen().getSharedPreferences();

        // Find UI
        languageList = (ListPreference) findPreference(getResources().getString(R.string.pref_language_list_key));

        // Get Language Preferences
        language = sharedPreferences.getString(
                getResources().getString(R.string.pref_language_list_key),
                ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).toLanguageTags().split("-")[0]
        );

        // Get Language Label & Value
        language_label = getResources().getStringArray(R.array.pref_language_label);
        language_value = getResources().getStringArray(R.array.pref_language_value);

        // Set Summary
        for (int i=0; i<language_value.length; i++) {
            if (language_value[i].equals(language)) {
                languageList.setSummary(language_label[i]);
            }
        }

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        // Get Preference
        Preference preference = findPreference(key);

        // Custom Language Switch
        if (key.equals(getResources().getString(R.string.pref_language_switch_key))) {

            // Switch ON
            if(((SwitchPreference) preference).isChecked()) {

                // Refresh Activity
                restartActivity();
            }
            // Switch OFF
            else {

                // Get Android System Language
                language = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).toLanguageTags().split("-")[0];

                // Edit Shared Preference
                editor = sharedPreferences.edit();
                editor.putString(getResources().getString(R.string.pref_language_list_key), language);
                editor.apply();

                // Refresh Activity
                restartActivity();
            }
        }
        // Choose Language List
        else if (key.equals(getResources().getString(R.string.pref_language_list_key))) {

            // Update Summary Of List Language Summary Selected
            preference.setSummary(((ListPreference) preference).getEntry());

            // Refresh Activity
            restartActivity();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        // Register
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        // Unregister
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    private void restartActivity() {
        Intent intent = new Intent(getActivity(), getActivity().getClass());
        startActivity(intent);
        getActivity().finish();
    }
}
