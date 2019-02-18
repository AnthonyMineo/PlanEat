package com.denma.planeat.views.holder;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.denma.planeat.R;
import com.denma.planeat.models.local.Meal;
import com.denma.planeat.views.adapter.MealOfTheDayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MealOfTheDayViewHolder extends RecyclerView.ViewHolder  {

    @BindView(R.id.meal_of_the_day_image)
    ImageView mainImage;
    @BindView(R.id.meal_label_text)
    TextView mealLabel;
    @BindView(R.id.delete_meal_button)
    FloatingActionButton deleteMeal;

    public MealOfTheDayViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    public void updateWithMeal(Meal meal, Context context, MealOfTheDayAdapter.deleteButtonListener onDeleteButtonListener) {
        Glide.with(context).load(meal.getRecipe().getImage()).into(this.mainImage);
        this.mealLabel.setText(meal.getRecipe().getLabel());
        deleteMeal.setOnClickListener(view -> onDeleteButtonListener.deleteButtonOnClick(meal));
    }

}
