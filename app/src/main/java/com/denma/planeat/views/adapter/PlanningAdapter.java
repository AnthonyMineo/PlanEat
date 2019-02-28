package com.denma.planeat.views.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denma.planeat.R;
import com.denma.planeat.models.local.FoodMenu;
import com.denma.planeat.views.holder.PlanningViewHolder;

import java.util.List;

public class PlanningAdapter extends GenericAdapter<FoodMenu, PlanningViewHolder> {

    public PlanningAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public PlanningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        return new PlanningViewHolder(inflateView(R.layout.plannig_recycle_item, parent));
    }
}
