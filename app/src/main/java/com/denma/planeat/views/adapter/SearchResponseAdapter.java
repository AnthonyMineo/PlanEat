package com.denma.planeat.views.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denma.planeat.R;
import com.denma.planeat.models.remote.Recipe;
import com.denma.planeat.views.holder.SearchResponseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SearchResponseAdapter extends RecyclerView.Adapter<SearchResponseViewHolder> {

    private Context context;
    private List<Recipe> recipeList;

    public SearchResponseAdapter(){ this.recipeList = new ArrayList<>(); }

    @NonNull
    @Override
    public SearchResponseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.search_recycle_item, parent, false);
        return new SearchResponseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResponseViewHolder holder, int position) {
        holder.updateWithRecipe(this.recipeList.get(position), this.context);
    }

    @Override
    public int getItemCount() {
        return this.recipeList.size();
    }

    public void updateData(List<Recipe> recipeList){
        this.recipeList = recipeList;
        this.notifyDataSetChanged();
    }
}
