package com.example.moviedbwithbottomnavig.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.moviedbwithbottomnavig.APIclient;
import com.example.moviedbwithbottomnavig.Apiinterface;
import com.example.moviedbwithbottomnavig.Main2Activity;
import com.example.moviedbwithbottomnavig.R;
import com.example.moviedbwithbottomnavig.adapter.Title_Adapter;
import com.example.moviedbwithbottomnavig.interfaces.OnItemClickListener;
import com.example.moviedbwithbottomnavig.modelclass.DatumResponse;
import com.example.moviedbwithbottomnavig.modelclass.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentMovie extends Fragment {
    String api_key = "8a940d7709a57a2398b0f39f63ce3f30";
    RecyclerView recyclerView;
    Apiinterface apiService;
    List<Result> dataset = new ArrayList<>();
    Title_Adapter title_adapter;
     int page = 1;
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
        title_adapter.setOnItemClickListener(new OnItemClickListener(){

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

    private void initviews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        title_adapter = new Title_Adapter(getActivity(), dataset);
        title_adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(title_adapter);
        loadJSON(page);
    }

    private void loadJSON(final int page) {
        apiService = APIclient.getClient().create(Apiinterface.class);
        Call<DatumResponse> call = apiService.getMovieDetails(api_key, page);
        call.enqueue(new Callback<DatumResponse>() {
            @Override
            public void onResponse(Call<DatumResponse> call, Response<DatumResponse> response) {
                if (response != null) {

                    DatumResponse item = response.body();

                    if (item.getResults() != null) {
                        dataset.addAll(item.getResults());
                        title_adapter.notifyItemRangeChanged(page, dataset.size());
                    }

                }
            }

            @Override
            public void onFailure(Call<DatumResponse> call, Throwable t) {

            }
        });
    }

    public void drawnext() {
        ++page;
        loadJSON(page);
    }

}
