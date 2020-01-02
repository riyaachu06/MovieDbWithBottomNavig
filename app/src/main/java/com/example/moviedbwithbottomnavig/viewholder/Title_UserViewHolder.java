package com.example.moviedbwithbottomnavig.viewholder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedbwithbottomnavig.databinding.ItemTitlelayoutBinding;
import com.example.moviedbwithbottomnavig.interfaces.OnItemClickListener;
import com.example.moviedbwithbottomnavig.modelclass.Result;

public class Title_UserViewHolder extends RecyclerView.ViewHolder {

    private ItemTitlelayoutBinding titlebinding;
    private OnItemClickListener mListener;

    public Title_UserViewHolder(ItemTitlelayoutBinding titlebinding, OnItemClickListener listener) {
        super(titlebinding.getRoot());
        this.titlebinding = titlebinding;
        mListener = listener;


        titlebinding.cardview.setOnClickListener(new View.OnClickListener() { // we can handle the click as like we do in normal
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION)
                        mListener.onItemClick(position);
                }

            }
        });

    }

    public ItemTitlelayoutBinding getTitleBinding() {

        return titlebinding;
    }

    public void titlebind(Result titleresult) {
        titlebinding.setTitle(titleresult);
        titlebinding.executePendingBindings();
    }
}
