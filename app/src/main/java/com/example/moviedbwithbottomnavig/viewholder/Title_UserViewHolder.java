package com.example.moviedbwithbottomnavig.viewholder;

import android.view.View;
import android.widget.AdapterView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedbwithbottomnavig.databinding.TitleLayoutBinding;
import com.example.moviedbwithbottomnavig.fragments.FragmentMovie;
import com.example.moviedbwithbottomnavig.interfaces.OnItemClickListener;
import com.example.moviedbwithbottomnavig.modelclass.Result;

public class Title_UserViewHolder extends RecyclerView.ViewHolder {

    private TitleLayoutBinding titlebinding;
    private OnItemClickListener mListener;

    public Title_UserViewHolder(TitleLayoutBinding titlebinding, OnItemClickListener listener) {
        super(titlebinding.getRoot());
        this.titlebinding = titlebinding;
        mListener=listener;


        titlebinding.cardview.setOnClickListener(new View.OnClickListener() { // we can handle the click as like we do in normal
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

    public TitleLayoutBinding getTitleBinding() {

        return titlebinding;
    }

    public void titlebind(Result titleresult) {
        titlebinding.setTitle(titleresult);
        titlebinding.executePendingBindings();//is important in order to execute the data binding immediately. Otherwise it can populate incorrect view.
    }
}
