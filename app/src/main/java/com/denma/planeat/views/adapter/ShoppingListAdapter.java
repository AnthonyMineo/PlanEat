package com.denma.planeat.views.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.denma.planeat.R;
import com.denma.planeat.views.holder.ShoppingListViewHolder;

public class ShoppingListAdapter extends GenericAdapter<String, ShoppingListViewHolder> {

    public ShoppingListAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ShoppingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        return new ShoppingListViewHolder(inflateView(R.layout.shopping_recycle_item, parent));
    }

}
