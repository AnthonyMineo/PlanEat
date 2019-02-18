package com.denma.planeat.views.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denma.planeat.R;
import com.denma.planeat.views.holder.ShoppingListViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListViewHolder> {

    private List<String> fileNameList;
    private Context context;

    public ShoppingListAdapter() {
        this.fileNameList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ShoppingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.shopping_recycle_item, parent, false);
        return new ShoppingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListViewHolder holder, int position) {
        holder.updateWithFileName(this.fileNameList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.fileNameList.size();
    }

    public String getFileName(int position){ return this.fileNameList.get(position); }

    public void updateData(List<String> fileNameList){
        this.fileNameList = fileNameList;
        this.notifyDataSetChanged();
    }
}
