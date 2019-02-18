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
        this.mealList = orderList(mealList);
        this.notifyDataSetChanged();
    }

    private List<Meal> orderList(List<Meal> mealList){

        List<Meal> orderedList = new ArrayList<>();
        Meal m1 = null, m2 = null, m3 = null, m4 = null, m5 = null;
        for(Meal meal : mealList){
            switch (meal.getDayTiming()){
                case 1:
                    m1 = meal;
                    break;
                case 2:
                    m2 = meal;
                    break;
                case 3:
                    m3 = meal;
                    break;
                case 4:
                    m4 = meal;
                    break;
                case 5:
                    m5 = meal;
                    break;
            }
        }
        if(m1 != null)
            orderedList.add(m1);
        if(m2 != null)
            orderedList.add(m2);
        if(m3 != null)
            orderedList.add(m3);
        if(m4 != null)
            orderedList.add(m4);
        if(m5 != null)
            orderedList.add(m5);

        return orderedList;
    }
}
