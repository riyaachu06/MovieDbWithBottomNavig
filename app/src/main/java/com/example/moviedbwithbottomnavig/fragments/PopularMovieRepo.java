package com.example.moviedbwithbottomnavig.fragments;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedbwithbottomnavig.Apiinterface;
import com.example.moviedbwithbottomnavig.modelclass.DatumResponse;
import com.example.moviedbwithbottomnavig.modelclass.popularmoviemodels.DatumResponse2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PopularMovieRepo {
    String api_key = "8a940d7709a57a2398b0f39f63ce3f30";
    int page = 1;
    private static PopularMovieRepo repoInstance;
    private final Application application;

    public PopularMovieRepo(Application application) {
        this.application = application;
    }

    public static PopularMovieRepo getinstance(Application application) {
        if (repoInstance == null) {
            repoInstance = new PopularMovieRepo(application);
        }
        return repoInstance;
    }

    public LiveData<DatumResponse2> getPopularMovieDetails() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apiinterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apiinterface api = retrofit.create(Apiinterface.class);
        final MutableLiveData<DatumResponse2> mutableLiveData = new MutableLiveData<>();
        Call<DatumResponse2> call = api.getPopoularMovieDetails(api_key,page);
        call.enqueue(new Callback<DatumResponse2>() {
            @Override
            public void onResponse(Call<DatumResponse2> call, Response<DatumResponse2> response) {
                if(response!=null){

                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<DatumResponse2> call, Throwable t) {

            }
        });
        return mutableLiveData;

    }
}
