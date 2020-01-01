package com.example.moviedbwithbottomnavig.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.moviedbwithbottomnavig.R;
import com.example.moviedbwithbottomnavig.databinding.ActivityMainBinding;
import com.example.moviedbwithbottomnavig.fragments.FragmentHome;
import com.example.moviedbwithbottomnavig.fragments.FragmentMovie;
import com.example.moviedbwithbottomnavig.fragments.FragmentPopularMovies;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    Fragment fragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        loadFragment(new FragmentHome());
        activityMainBinding.navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        fragment = new FragmentHome();
                        break;
                    case R.id.navigation_movies:
                        fragment = new FragmentMovie();
                        break;
                    case R.id.navigation_popularmovies:
                        fragment = new FragmentPopularMovies();
                        break;
                }
                return loadFragment(fragment);
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    }


