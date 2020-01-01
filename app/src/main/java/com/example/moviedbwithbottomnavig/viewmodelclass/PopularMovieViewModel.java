package com.example.moviedbwithbottomnavig.viewmodelclass;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.moviedbwithbottomnavig.modelclass.popularmoviemodels.DatumResponse2;
import com.example.moviedbwithbottomnavig.usersrepo.PopularMovieRepo;



public class PopularMovieViewModel extends AndroidViewModel {
    private final PopularMovieRepo popularMovieRepo;


    public PopularMovieViewModel(@NonNull Application application) {
        super(application);
        popularMovieRepo = PopularMovieRepo.getinstance(application);
    }

    public LiveData<DatumResponse2> getPopularMovieDetails() {

        return popularMovieRepo.getPopularMovieDetails();
    }
}
