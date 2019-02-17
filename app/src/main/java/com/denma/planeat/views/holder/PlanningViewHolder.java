package com.denma.planeat.views.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.denma.planeat.R;
import com.denma.planeat.models.local.Meal;
import com.denma.planeat.models.local.FoodMenu;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlanningViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.planning_icon_day_name)
    TextView day_name;
    @BindView(R.id.planning_icon_day_number)
    TextView day_number;
    @BindView(R.id.planning_icon_breakfast)
    ImageView breakfast;
    @BindView(R.id.planning_icon_after_morning)
    ImageView after_morning;
    @BindView(R.id.planning_icon_lunch)
    ImageView lunch;
    @BindView(R.id.planning_icon_afternoon)
    ImageView afternoon;
    @BindView(R.id.planning_icon_dinner)
    ImageView dinner;

    public PlanningViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithMenu(FoodMenu foodMenu, Context context){
        this.day_name.setText(foodMenu.getEatingDateString().substring(0,3).toUpperCase());
        this.day_number.setText(foodMenu.getEatingDateString().substring(4));

        this.breakfast.setImageDrawable(null);
        this.after_morning.setImageDrawable(null);
        this.lunch.setImageDrawable(null);
        this.afternoon.setImageDrawable(null);
        this.dinner.setImageDrawable(null);

        if(foodMenu.getMealList().size() == 0){
            // show a message to the user
        } else {
            for(Meal meal : foodMenu.getMealList()){
                String url = meal.getRecipe().getImage();
                switch (meal.getDayTiming()){
                    case 1:
                        Glide.with(context).load(url).into(this.breakfast);
                        break;
                    case 2:
                        Glide.with(context).load(url).into(this.after_morning);
                        break;
                    case 3:
                        Glide.with(context).load(url).into(this.lunch);
                        break;
                    case 4:
                        Glide.with(context).load(url).into(this.afternoon);
                        break;
                    case 5:
                        Glide.with(context).load(url).into(this.dinner);
                        break;
                }
            }
        }

    }
}
