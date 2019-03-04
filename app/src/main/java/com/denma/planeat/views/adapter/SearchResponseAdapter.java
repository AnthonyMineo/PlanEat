package com.denma.planeat.views.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.denma.planeat.R;
import com.denma.planeat.models.remote.Recipe;
import com.denma.planeat.views.holder.SearchResponseViewHolder;

public class SearchResponseAdapter extends GenericAdapter<Recipe, SearchResponseViewHolder> {

    public SearchResponseAdapter(Context context){
        super(context);
    }

    @NonNull
    @Override
    public SearchResponseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        return new SearchResponseViewHolder(inflateView(R.layout.search_recycle_item, parent));
    }

}
