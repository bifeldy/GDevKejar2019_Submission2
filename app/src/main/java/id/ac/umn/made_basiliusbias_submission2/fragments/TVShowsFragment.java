package id.ac.umn.made_basiliusbias_submission2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import id.ac.umn.made_basiliusbias_submission2.R;
import id.ac.umn.made_basiliusbias_submission2.Utility;
import id.ac.umn.made_basiliusbias_submission2.adapters.TVShowAdapter;
import id.ac.umn.made_basiliusbias_submission2.pojos.Film;

import java.util.ArrayList;
import java.util.List;

public class TVShowsFragment extends Fragment {

    // TODO: Mindahin Variabel Global Jadi Local (Kalo Sempet, Udah Di Warn, Wkwk~)

    // For List Of Movies View
    private RecyclerView recyclerView;
    private View v;

    // TV Shows List
    private List<Film> tv_shows;

    public TVShowsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_tv_shows, container, false);

        // Pre-Populate Data
        tv_shows = new ArrayList<>();
        tv_shows = Utility.populateData(v.getContext(), "TV_Show");

        // Setting Up RecyclerView
        recyclerView = v.findViewById(R.id.recycler_tv_shows);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(new TVShowAdapter(v.getContext(), R.layout.item_tv_show, tv_shows));

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
