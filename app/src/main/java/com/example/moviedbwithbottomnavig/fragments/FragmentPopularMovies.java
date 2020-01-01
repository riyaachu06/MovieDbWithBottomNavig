package com.example.moviedbwithbottomnavig.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.moviedbwithbottomnavig.activity.PopularMovieActivity;
import com.example.moviedbwithbottomnavig.R;
import com.example.moviedbwithbottomnavig.adapter.PopularMovieTitleAdapter;
import com.example.moviedbwithbottomnavig.interfaces.OnItemClickListener;
import com.example.moviedbwithbottomnavig.modelclass.popularmoviemodels.DatumResponse2;
import com.example.moviedbwithbottomnavig.modelclass.popularmoviemodels.Result;
import com.example.moviedbwithbottomnavig.viewmodelclass.PopularMovieViewModel;

import java.util.ArrayList;
import java.util.List;


public class FragmentPopularMovies extends Fragment {
    RecyclerView recyclerView;
    List<Result> dataset = new ArrayList<>();
    PopularMovieTitleAdapter popularMovieTitleAdapter;
    public static final String ITEM = "item";
    int page = 1;
    PopularMovieViewModel popularmovieViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_fragment_popular_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        initviews();
        itemClick();
        popularmovieViewModel = ViewModelProviders.of(this).get(PopularMovieViewModel.class);
        fetchMovieLists();

    }

    private void fetchMovieLists() {
        popularmovieViewModel.getPopularMovieDetails().observe(getViewLifecycleOwner(), new Observer<DatumResponse2>() {
            @Override
            public void onChanged(DatumResponse2 datumResponse2) {
                if (datumResponse2.getResults() != null) {
                    int previousSize = dataset.size();
                    dataset.addAll(datumResponse2.getResults());
                    popularMovieTitleAdapter.notifyItemRangeChanged(previousSize, datumResponse2.getResults().size());
                }


            }
        });
    }

    private void itemClick() {
        popularMovieTitleAdapter.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), PopularMovieActivity.class);
                intent.putExtra(ITEM, dataset.get(position));
                startActivity(intent);
            }
        });
    }

    private void initviews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        popularMovieTitleAdapter = new PopularMovieTitleAdapter(getActivity(), dataset);
        popularMovieTitleAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(popularMovieTitleAdapter);
    }


    public void drawnext() {
        ++page;
        fetchMovieLists();
    }
}
