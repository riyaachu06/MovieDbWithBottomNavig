package com.example.moviedbwithbottomnavig.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedbwithbottomnavig.Utility;
import com.example.moviedbwithbottomnavig.databinding.PopularTitleLayoutBinding;
import com.example.moviedbwithbottomnavig.fragments.FragmentPopularMovies;
import com.example.moviedbwithbottomnavig.interfaces.OnItemClickListener;
import com.example.moviedbwithbottomnavig.modelclass.popularmoviemodels.Result;
import com.example.moviedbwithbottomnavig.viewholder.PopularTitle_UserViewHolder;


import java.util.ArrayList;
import java.util.List;

public class PopularMovieTitleAdapter extends RecyclerView.Adapter {
    private List<Result> dataset = new ArrayList<>();
    Context context;
    private OnItemClickListener mListener;
    private FragmentPopularMovies listener2;

    public PopularMovieTitleAdapter(FragmentActivity context, List<Result> dataset) {
        this.context = context;
        this.dataset = dataset;    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PopularTitleLayoutBinding popularTitleLayoutBinding = PopularTitleLayoutBinding
                .inflate(inflater, parent, false);
        return new PopularTitle_UserViewHolder(popularTitleLayoutBinding, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PopularTitle_UserViewHolder popularTitle_userViewHolder;

        if (holder instanceof PopularTitle_UserViewHolder) {
            popularTitle_userViewHolder = (PopularTitle_UserViewHolder) holder;
            settitleuserviewholder(popularTitle_userViewHolder, position);
        }

//        if (dataset.size() > 1) {
//            if (position == dataset.size() - 1) {
//                listener2.drawnext();
//            }
//        }
    }

    private void settitleuserviewholder(PopularTitle_UserViewHolder popularTitle_userViewHolder, int position) {

        Result titlemovie = dataset.get(position);
        String imageicon = titlemovie.getPosterPath();
        String IMAGE_URL = "https://image.tmdb.org/t/p/w200/" + imageicon;
        if (titlemovie != null) {
            popularTitle_userViewHolder.populartitlebind(titlemovie);
            Utility.loadImageUsingGlide(context, popularTitle_userViewHolder.getPopularTitleBinding().imageView, IMAGE_URL);
        }


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mListener=onItemClickListener;
    }

    public void setOnItemClickListener(FragmentPopularMovies listener) {
        listener2=listener;
    }
}
