package id.ac.umn.made_basiliusbias_submission2;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.DisplayMetrics;
import id.ac.umn.made_basiliusbias_submission2.pojos.Film;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Utility {

    public static int calculateNumOfColumns(Context context, float columnWidthDp) {

        // For example column Width dp = 180
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;

        int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
        return noOfColumns;
    }

    public static List<Film> populateData(Context context, String type) {

        // Load Data From `list_films.xml`
        String[] film_titles = context.getResources().getStringArray(R.array.film_titles);
        String[] film_descriptions = context.getResources().getStringArray(R.array.film_descriptions);
        TypedArray film_photos = context.getResources().obtainTypedArray(R.array.film_photos);
        String[] film_scores = context.getResources().getStringArray(R.array.film_scores);
        String[] film_type = context.getResources().getStringArray(R.array.film_type);

        // Make List to ArrayList
        List<Film> films = new ArrayList<>();

        // Adding Data To The List
        for (int i=0; i<film_titles.length; i++) {

            // Get Data Base On Type Requested
            if(film_type[i].equals(type)) {

                // Create new object
                Film film = new Film();
                film.setPhoto(film_photos.getResourceId(i, -1));
                film.setTitle(film_titles[i]);
                film.setDescription(film_descriptions[i]);
                film.setScore(Double.parseDouble(film_scores[i]));
                film.setType(film_type[i]);

                // Push To List
                films.add(film);
            }
        }

        // Recycle for future use
        film_photos.recycle();

        return films;
    }

    // https://stackoverflow.com/questions/39705739/android-n-change-language-programmatically
    public static ContextWrapper changeLanguage(Context context, String lang_code){
        Locale sysLocale;

        Resources rs = context.getResources();
        Configuration config = rs.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sysLocale = config.getLocales().get(0);
        }
        else {
            sysLocale = config.locale;
        }

        if (!lang_code.equals("")) {
            Locale locale = new Locale(lang_code);
            Locale.setDefault(locale);

            // Android N ++
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.setLocale(locale);
            }
            else {
                config.locale = locale;
            }

            // Android Jelly Bean ++
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                context = context.createConfigurationContext(config);
            }
            else {
                context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
            }
        }

        return new ContextWrapper(context);
    }

}