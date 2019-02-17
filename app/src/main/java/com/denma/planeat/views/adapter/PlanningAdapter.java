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

import java.util.ArrayList;
import java.util.List;

public class PlanningAdapter extends RecyclerView.Adapter<PlanningViewHolder>{

    private Context context;
    private List<FoodMenu> mFoodMenuList;

    public PlanningAdapter() {
        this.mFoodMenuList = new ArrayList<>();
    }

    @NonNull
    @Override
    public PlanningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.plannig_recycle_item, parent, false);
        return new PlanningViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanningViewHolder holder, int position) {
        holder.updateWithMenu(this.mFoodMenuList.get(position), this.context);
    }

    @Override
    public int getItemCount() {
        return this.mFoodMenuList.size();
    }

    public FoodMenu getMenu(int position){ return this.mFoodMenuList.get(position); }

    public void updateData(List<FoodMenu> foodMenuList){
        this.mFoodMenuList = foodMenuList;
        this.notifyDataSetChanged();
    }
}
