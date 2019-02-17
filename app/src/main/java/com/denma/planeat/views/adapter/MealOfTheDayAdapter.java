package com.denma.planeat.views.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denma.planeat.R;
import com.denma.planeat.models.local.Meal;
import com.denma.planeat.views.holder.MealOfTheDayViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MealOfTheDayAdapter extends RecyclerView.Adapter<MealOfTheDayViewHolder> {

    private Context context;
    private List<Meal> mealList;

    public deleteButtonListener onDeleteButtonListener;
    public interface deleteButtonListener {
        void deleteButtonOnClick(Meal meal);
    }

    public MealOfTheDayAdapter(deleteButtonListener onDeleteButtonListener) {
        this.mealList = new ArrayList<>();
        this.onDeleteButtonListener = onDeleteButtonListener;
    }

    @NonNull
    @Override
    public MealOfTheDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.meal_of_the_day_recycle_item, parent, false);
        return new MealOfTheDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealOfTheDayViewHolder holder, int position) {
        holder.updateWithMeal(this.mealList.get(position), this.context, this.onDeleteButtonListener);
    }

    @Override
    public int getItemCount() { return this.mealList.size(); }

    public Meal getMeal(int position){ return this.mealList.get(position); }

    public void updateData(List<Meal> mealList){
        this.mealList = mealList;
        this.notifyDataSetChanged();
    }
}
