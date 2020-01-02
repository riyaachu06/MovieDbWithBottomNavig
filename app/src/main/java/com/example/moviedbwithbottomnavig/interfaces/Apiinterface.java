package com.example.moviedbwithbottomnavig.interfaces;


import com.example.moviedbwithbottomnavig.modelclass.DatumResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apiinterface {

    @GET("3/movie/now_playing")
     Call<DatumResponse> getMovieDetails(@Query("api_key") String api_key, @Query("page") int page);

    @GET("3/movie/popular")
    Call<DatumResponse> getPopoularMovieDetails(@Query("api_key") String api_key, @Query("page") int page);

    public static final String BASE_URL = "https://api.themoviedb.org/";


}
