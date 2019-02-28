package com.denma.planeat.views.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denma.planeat.views.holder.GenericViewHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericAdapter<T, VH extends GenericViewHolder<T>> extends RecyclerView.Adapter<VH> {

    private List<T> itemList;
    private LayoutInflater layoutInflater;

    public GenericAdapter(Context context){
        this.itemList = new ArrayList();
        this.layoutInflater = LayoutInflater.from(context);
    }

    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    @NonNull
    protected View inflateView(@LayoutRes final int layout, final @Nullable ViewGroup parent) {
        return layoutInflater.inflate(layout, parent, false);
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        T item = itemList.get(position);
        holder.updateWithItem(item);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public T getItem(int position){ return this.itemList.get(position); }

    public void updateData(List<T> items){
        this.itemList = items;
        this.notifyDataSetChanged();
    }

}
