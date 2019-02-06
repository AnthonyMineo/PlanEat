package com.denma.planeat.views.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.denma.planeat.R;
import com.denma.planeat.models.local.Meal;

import butterknife.BindView;


public class MealOfTheDayViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.meal_of_the_day_main_image)
    ImageView mainImage;

    public MealOfTheDayViewHolder(View itemView) {
        super(itemView);
    }

    public void updateWithMeal(Meal meal, Context context) {

    }
}
