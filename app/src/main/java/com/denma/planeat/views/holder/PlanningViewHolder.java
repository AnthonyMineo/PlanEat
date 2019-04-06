package com.denma.planeat.views.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.denma.planeat.R;
import com.denma.planeat.models.local.FoodMenu;
import com.denma.planeat.models.local.Meal;

import butterknife.BindView;

public class PlanningViewHolder extends GenericViewHolder<FoodMenu>{

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
    }

    @Override
    public void updateWithItem(FoodMenu item) {
        this.day_name.setText(item.getEatingDateString().substring(0,3).toUpperCase());
        this.day_number.setText(item.getEatingDateString().substring(4));

        this.breakfast.setImageDrawable(null);
        this.after_morning.setImageDrawable(null);
        this.lunch.setImageDrawable(null);
        this.afternoon.setImageDrawable(null);
        this.dinner.setImageDrawable(null);

        if(item.getMealList().size() == 0){
            // show a message to the user
        } else {
            for(Meal meal : item.getMealList()){
                String url = meal.getRecipe().getPicture();
                switch (meal.getDayTiming()){
                    case 1:
                        Glide.with(getContext()).load(url).into(this.breakfast);
                        break;
                    case 2:
                        Glide.with(getContext()).load(url).into(this.after_morning);
                        break;
                    case 3:
                        Glide.with(getContext()).load(url).into(this.lunch);
                        break;
                    case 4:
                        Glide.with(getContext()).load(url).into(this.afternoon);
                        break;
                    case 5:
                        Glide.with(getContext()).load(url).into(this.dinner);
                        break;
                }
            }
        }
    }
}
