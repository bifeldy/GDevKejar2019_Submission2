package id.ac.umn.made_basiliusbias_submission2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import id.ac.umn.made_basiliusbias_submission2.R;
import id.ac.umn.made_basiliusbias_submission2.Utility;
import id.ac.umn.made_basiliusbias_submission2.adapters.MovieAdapter;
import id.ac.umn.made_basiliusbias_submission2.pojos.Film;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment {

    // TODO: Mindahin Variabel Global Jadi Local (Kalo Sempet, Udah Di Warn, Wkwk~)

    // For List Of Movies View
    private RecyclerView recyclerView;
    private View v;

    // Movies List
    private List<Film> movies;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_movies, container, false);

        // Determine Size Of Column To View As Grid
        int numOfColumns = Utility.calculateNumOfColumns(v.getContext(), 175);

        // Pre-Populate Data
        movies = new ArrayList<>();
        movies = Utility.populateData(v.getContext(), "Movies");

        // Setting Up RecyclerView
        recyclerView = v.findViewById(R.id.recycler_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(v.getContext(), numOfColumns));
        recyclerView.setAdapter(new MovieAdapter(v.getContext(), R.layout.item_movies, movies));

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
