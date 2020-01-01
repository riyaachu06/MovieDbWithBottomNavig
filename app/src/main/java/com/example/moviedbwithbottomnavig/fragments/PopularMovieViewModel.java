package com.example.moviedbwithbottomnavig.fragments;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedbwithbottomnavig.modelclass.DatumResponse;
import com.example.moviedbwithbottomnavig.modelclass.popularmoviemodels.DatumResponse2;

import java.util.List;

public class PopularMovieViewModel extends AndroidViewModel {
    private final PopularMovieRepo popularMovieRepo;
    private MutableLiveData<List<DatumResponse2>> dataset;


    public PopularMovieViewModel(@NonNull Application application) {
        super(application);
        popularMovieRepo = PopularMovieRepo.getinstance(application);
    }

    public LiveData<DatumResponse2> getPopularMovieDetails() {
        if (dataset == null) {
            dataset = new MutableLiveData<List<DatumResponse2>>();
        }

        return popularMovieRepo.getPopularMovieDetails();
    }
}
