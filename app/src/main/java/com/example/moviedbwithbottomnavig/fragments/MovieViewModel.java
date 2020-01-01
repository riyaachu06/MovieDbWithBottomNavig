package com.example.moviedbwithbottomnavig.fragments;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedbwithbottomnavig.modelclass.DatumResponse;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private final MovieRepo movieRepo;
    private MutableLiveData<List<DatumResponse>> dataset;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepo = MovieRepo.getInstance(application);
    }


    public LiveData<DatumResponse> getMovieDetails() {
        if (dataset == null) {
            dataset = new MutableLiveData<List<DatumResponse>>();
        }

        return movieRepo.getMovieDetails();
    }

}
