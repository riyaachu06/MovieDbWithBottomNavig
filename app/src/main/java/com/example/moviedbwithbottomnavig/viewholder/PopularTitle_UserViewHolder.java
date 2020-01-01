package com.example.moviedbwithbottomnavig.viewholder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedbwithbottomnavig.databinding.PopularTitleLayoutBinding;
import com.example.moviedbwithbottomnavig.databinding.TitleLayoutBinding;
import com.example.moviedbwithbottomnavig.interfaces.OnItemClickListener;
import com.example.moviedbwithbottomnavig.modelclass.popularmoviemodels.Result;

public class PopularTitle_UserViewHolder extends RecyclerView.ViewHolder {

    private PopularTitleLayoutBinding popularTitleLayoutBinding;
    private OnItemClickListener mListener;

    public PopularTitle_UserViewHolder(PopularTitleLayoutBinding popularTitleLayoutBinding, OnItemClickListener listener) {
        super(popularTitleLayoutBinding.getRoot());
        this.popularTitleLayoutBinding = popularTitleLayoutBinding;
        mListener = listener;

        popularTitleLayoutBinding.cardview.setOnClickListener(new View.OnClickListener() { // we can handle the click as like we do in normal
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    int position = getAdapterPosition(); // Get the index of the view holder
                    // Makes sure this position is still valid
                    if (position != RecyclerView.NO_POSITION)
                        mListener.onItemClick(position); // we catch the click on the item view then pass it over the interface and then to our activity
                }

            }
        });

    }

    public PopularTitleLayoutBinding getPopularTitleBinding() {

        return popularTitleLayoutBinding;
    }

    public void populartitlebind(Result titleresult2) {
        popularTitleLayoutBinding.setTitle2(titleresult2);
        popularTitleLayoutBinding.executePendingBindings();//is important in order to execute the data binding immediately. Otherwise it can populate incorrect view.
    }
}
