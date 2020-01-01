package com.example.moviedbwithbottomnavig.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.moviedbwithbottomnavig.R;
import com.example.moviedbwithbottomnavig.databinding.ActivityMain3Binding;
import com.example.moviedbwithbottomnavig.modelclass.popularmoviemodels.Result;

import static com.example.moviedbwithbottomnavig.fragments.FragmentMovie.ITEM;

public class PopularMovieActivity extends AppCompatActivity {
    ActivityMain3Binding activityMain3Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMain3Binding= DataBindingUtil.setContentView(this, R.layout.activity_main3);
        Intent intent = getIntent();
        Result clickedItem = intent.getParcelableExtra(ITEM);
        activityMain3Binding.setDetails2(clickedItem);

        activityMain3Binding.ratingBar.setRating(Float.parseFloat(Double.toString(clickedItem.getVoteAverage())));
    }
}
