package com.example.moviedbwithbottomnavig.fragments;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.moviedbwithbottomnavig.Apiinterface;
import com.example.moviedbwithbottomnavig.modelclass.DatumResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepo {
    String api_key = "8a940d7709a57a2398b0f39f63ce3f30";
    int page = 1;
    private static MovieRepo repoInstance;
    private final Application application;

    public MovieRepo(Application application) {
        this.application = application;
    }

    public static MovieRepo getInstance(Application application) {
        if (repoInstance == null) {
            repoInstance = new MovieRepo(application);
        }
        return repoInstance;
    }

    public MutableLiveData<DatumResponse> getMovieDetails() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apiinterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apiinterface api = retrofit.create(Apiinterface.class);

        final MutableLiveData<DatumResponse> mutableLiveData = new MutableLiveData<>();
        Call<DatumResponse> call = api.getMovieDetails(api_key,page);
        call.enqueue(new Callback<DatumResponse>() {
            @Override
            public void onResponse(Call<DatumResponse> call, Response<DatumResponse> response) {
                if(response!=null){

                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<DatumResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
}
}
