package com.example.moviedbwithbottomnavig.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedbwithbottomnavig.activity.MovieDetailsActvity;
import com.example.moviedbwithbottomnavig.R;
import com.example.moviedbwithbottomnavig.adapter.Title_Adapter;
import com.example.moviedbwithbottomnavig.databinding.FragmentFragmentMovieBinding;
import com.example.moviedbwithbottomnavig.interfaces.OnItemClickListener;
import com.example.moviedbwithbottomnavig.modelclass.DatumResponse;
import com.example.moviedbwithbottomnavig.modelclass.Result;
import com.example.moviedbwithbottomnavig.viewmodelclass.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentMovie extends Fragment implements OnItemClickListener {
    private List<Result> dataset = new ArrayList<>();
    Title_Adapter title_adapter;
    public static final String ITEM = "item";
    private int page = 1;
    private MovieViewModel movieViewModel;
    FragmentFragmentMovieBinding fragmentFragmentMovieBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentFragmentMovieBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_fragment_movie, container, false);
        return fragmentFragmentMovieBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initviews();
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        fetchMovieLists();
    }

    private void fetchMovieLists() {
        movieViewModel.getMovieDetails(page).observe(getViewLifecycleOwner(), new Observer<DatumResponse>() {
            @Override
            public void onChanged(DatumResponse datumResponse) {
                if (datumResponse.getResults() != null) {
                    int previousSize = dataset.size();
                    dataset.addAll(datumResponse.getResults());
                    title_adapter.notifyItemRangeChanged(previousSize, datumResponse.getResults().size());
                }
            }

        });
    }

    private void initviews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        fragmentFragmentMovieBinding.recyclerView.setLayoutManager(layoutManager);
        fragmentFragmentMovieBinding.recyclerView.setHasFixedSize(false);
        title_adapter = new Title_Adapter(getActivity(), dataset);
        title_adapter.setOnItemClickListener(this);
        fragmentFragmentMovieBinding.recyclerView.setAdapter(title_adapter);
    }

    public void drawnext() {
        ++page;
        fetchMovieLists();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), MovieDetailsActvity.class);
        intent.putExtra(ITEM, dataset.get(position));
        startActivity(intent);
    }

    @Override
    public void drawNext() {
        this.drawnext();
    }
}
