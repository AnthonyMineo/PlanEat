package com.denma.planeat.views.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import butterknife.ButterKnife;

public abstract class GenericViewHolder<T> extends RecyclerView.ViewHolder {

    private Context context;

    public GenericViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = itemView.getContext();
    }

    public abstract void updateWithItem(T item);

    public Context getContext() {
        return context;
    }
}
