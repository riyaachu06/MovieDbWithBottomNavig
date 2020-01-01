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

import com.example.moviedbwithbottomnavig.activity.MovieDetailsActvity;
import com.example.moviedbwithbottomnavig.R;
import com.example.moviedbwithbottomnavig.adapter.Title_Adapter;
import com.example.moviedbwithbottomnavig.interfaces.OnItemClickListener;
import com.example.moviedbwithbottomnavig.modelclass.DatumResponse;
import com.example.moviedbwithbottomnavig.modelclass.Result;
import com.example.moviedbwithbottomnavig.usersrepo.MovieRepo;
import com.example.moviedbwithbottomnavig.viewmodelclass.MovieViewModel;

import java.util.ArrayList;
import java.util.List;


public class FragmentMovie extends Fragment {
    RecyclerView recyclerView;
    private List<Result> dataset = new ArrayList<>();
    Title_Adapter title_adapter;
    public static final String ITEM = "item";
    private int page = 1;
    private MovieViewModel movieViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        initviews();
        itemClick();
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        fetchMovieLists();
    }

    private void  itemClick (){
        title_adapter.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), MovieDetailsActvity.class);
                intent.putExtra(ITEM, dataset.get(position));
                startActivity(intent);
            }
        });

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
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);
        title_adapter = new Title_Adapter(getActivity(), dataset);
        title_adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(title_adapter);
    }

    public void drawnext() {
        ++page;
        fetchMovieLists();
    }

}
