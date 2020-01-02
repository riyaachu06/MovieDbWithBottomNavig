package com.example.moviedbwithbottomnavig.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.moviedbwithbottomnavig.R;
import com.example.moviedbwithbottomnavig.databinding.ActivityMoviedetailsBinding;
import com.example.moviedbwithbottomnavig.modelclass.Result;

import static com.example.moviedbwithbottomnavig.fragments.FragmentMovie.ITEM;


public class MovieDetailsActvity extends AppCompatActivity {
    ActivityMoviedetailsBinding activityMoviedetailsBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMoviedetailsBinding= DataBindingUtil.setContentView(this, R.layout.activity_moviedetails);
        getMovieDetails();
    }
    private void getMovieDetails() {
        Intent intent = getIntent();
        Result clickedItem = intent.getParcelableExtra(ITEM);
        activityMoviedetailsBinding.setDetails(clickedItem);
        activityMoviedetailsBinding.include.ratingBar.setRating(Float.parseFloat(Double.toString(clickedItem.getVoteAverage())));
    }
}
