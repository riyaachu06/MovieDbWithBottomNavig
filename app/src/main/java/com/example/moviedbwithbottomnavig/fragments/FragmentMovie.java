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

import com.example.moviedbwithbottomnavig.Main2Activity;
import com.example.moviedbwithbottomnavig.R;
import com.example.moviedbwithbottomnavig.adapter.Title_Adapter;
import com.example.moviedbwithbottomnavig.interfaces.OnItemClickListener;
import com.example.moviedbwithbottomnavig.modelclass.DatumResponse;
import com.example.moviedbwithbottomnavig.modelclass.Result;

import java.util.ArrayList;
import java.util.List;



public class FragmentMovie extends Fragment {
    RecyclerView recyclerView;
    List<Result> dataset = new ArrayList<>();
    Title_Adapter title_adapter;
    public static final String ITEM = "item";


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

        MovieViewModel movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovieDetails().observe(this, new Observer<DatumResponse>() {
            @Override
            public void onChanged(DatumResponse datumResponse) {
                if (datumResponse.getResults() != null) {
                        dataset.addAll(datumResponse.getResults());
//                        title_adapter.notifyItemRangeChanged(page, dataset.size());
                    }
                title_adapter = new Title_Adapter(getActivity(), dataset);
                recyclerView.setAdapter(title_adapter);

                title_adapter.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getContext(), Main2Activity.class);
                        intent.putExtra(ITEM, dataset.get(position));
                        startActivity(intent);
                    }

                });
            }

        });

    }

    private void initviews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

    }


//    public void drawnext() {
//        ++page;
//        loadJSON(page);
//    }

}
