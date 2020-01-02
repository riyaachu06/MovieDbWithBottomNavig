package com.example.moviedbwithbottomnavig.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedbwithbottomnavig.Utility;
import com.example.moviedbwithbottomnavig.databinding.ItemTitlelayoutBinding;
import com.example.moviedbwithbottomnavig.interfaces.OnItemClickListener;
import com.example.moviedbwithbottomnavig.modelclass.Result;
import com.example.moviedbwithbottomnavig.viewholder.Title_UserViewHolder;

import java.util.List;


public class Title_Adapter extends RecyclerView.Adapter {
    private List<Result> dataset ;
    Context context;
    private OnItemClickListener mListener;

    public Title_Adapter(Context context, List<Result> dataset) {
        this.context = context;
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemTitlelayoutBinding titlebinding = ItemTitlelayoutBinding
                .inflate(inflater, parent, false);
        return new Title_UserViewHolder(titlebinding, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Title_UserViewHolder title_userViewHolder;

        if (holder instanceof Title_UserViewHolder) {
            title_userViewHolder = (Title_UserViewHolder) holder;
            settitleuserviewholder(title_userViewHolder, position);
        }
        if (dataset.size() > 1) {
            if (position == dataset.size() - 1) {
                mListener.drawNext();
            }
        }
    }

    private void settitleuserviewholder(Title_UserViewHolder title_userViewHolder, int position) {

        Result titlemovie = dataset.get(position);
        String imageicon = titlemovie.getPosterPath();
        String IMAGE_URL = "https://image.tmdb.org/t/p/w200/" + imageicon;
        if (titlemovie != null) {
            title_userViewHolder.titlebind(titlemovie);
            Utility.loadImageUsingGlide(context, title_userViewHolder.getTitleBinding().imageView, IMAGE_URL);
        }


    }

    @Override
    public int getItemCount() {

        return dataset.size();
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

}
