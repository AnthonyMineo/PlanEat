package com.denma.planeat.views.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.denma.planeat.R;
import com.denma.planeat.models.local.Meal;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MealOfTheDayViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.meal_of_the_day_image)
    ImageView mainImage;

    public MealOfTheDayViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithMeal(Meal meal, Context context) {
        Glide.with(context).load(meal.getRecipe().getImage()).into(this.mainImage);
    }
}
