package com.example.moviedbwithbottomnavig.viewmodelclass;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.moviedbwithbottomnavig.modelclass.DatumResponse;
import com.example.moviedbwithbottomnavig.usersrepo.MovieRepo;


public class MovieViewModel extends AndroidViewModel {
    private final MovieRepo movieRepo;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepo = MovieRepo.getInstance(application);
    }
    public LiveData<DatumResponse> getMovieDetails(int page) {
        return movieRepo.getMovieDetails(page);
    }

}
