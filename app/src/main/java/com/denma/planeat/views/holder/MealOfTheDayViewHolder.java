package com.denma.planeat.views.holder;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.denma.planeat.R;
import com.denma.planeat.models.local.Meal;
import com.denma.planeat.views.adapter.MealOfTheDayAdapter;

import butterknife.BindView;


public class MealOfTheDayViewHolder extends GenericViewHolder<Meal> {

    private MealOfTheDayAdapter.deleteButtonListener onDeleteButtonListener;

    @BindView(R.id.meal_of_the_day_image)
    ImageView mainImage;
    @BindView(R.id.meal_label_text)
    TextView mealLabel;
    @BindView(R.id.delete_meal_button)
    FloatingActionButton deleteMeal;

    public MealOfTheDayViewHolder(View itemView, MealOfTheDayAdapter.deleteButtonListener onDeleteButtonListener) {
        super(itemView);
        this.onDeleteButtonListener = onDeleteButtonListener;
    }

    @Override
    public void updateWithItem(Meal item) {
        try{
            Glide.with(getContext()).load(item.getRecipe().getImage()).into(this.mainImage);
            this.mealLabel.setText(item.getRecipe().getLabel());
            deleteMeal.setOnClickListener(view -> onDeleteButtonListener.deleteButtonOnClick(item));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
